package com.nutrivision.app.ui.viewmodel

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cloudinary.Cloudinary
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.nutrivision.app.data.model.UserProfile
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore,
    private val cloudinary: Cloudinary
) : ViewModel() {
    private val _authState = MutableStateFlow<AuthState>(AuthState.Unauthenticated)
    val authState: MutableStateFlow<AuthState> = _authState

    private val _userProfile = MutableStateFlow<UserProfile?>(null)
    val userProfile: StateFlow<UserProfile?> = _userProfile

    init {
        checkAuthStatus()
    }

    fun checkAuthStatus() {
        val firebaseUser = auth.currentUser
        if(firebaseUser != null){
            _authState.value = AuthState.Authenticated
            fetchUserProfile(firebaseUser.uid)
        }else{
            _authState.value = AuthState.Unauthenticated
        }
    }

    fun login(email: String, password: String) {
        if(email.isEmpty() || password.isEmpty()){
            _authState.value = AuthState.Error("Email atau Password tidak boleh kosong")
            return
        }

        _authState.value = AuthState.Loading
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val uid = task.result?.user?.uid
                    if (uid != null) {
                        _authState.value = AuthState.Authenticated
                        fetchUserProfile(uid)
                    } else {
                        _authState.value = AuthState.Error("Could not retrieve user ID.")
                    }
                } else {
                    _authState.value = AuthState.Error(task.exception?.message ?: "Login failed")
                }
            }
    }

    fun signup(name: String, email: String, password: String) {
        if (name.isBlank() || email.isBlank() || password.isBlank()) {
            _authState.value = AuthState.Error("All fields are required.")
            return
        }

        _authState.value = AuthState.Loading
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val firebaseUser = task.result?.user
                    firebaseUser?.let { user ->
                        val newUserProfile = UserProfile(
                            uid = user.uid,
                            displayName = name,
                            email = user.email ?: ""
                        )
                        firestore.collection("users").document(user.uid)
                            .set(newUserProfile)
                            .addOnSuccessListener {
                                _authState.value = AuthState.Authenticated
                                fetchUserProfile(user.uid)
                            }
                            .addOnFailureListener { e ->
                                _authState.value = AuthState.Error(e.message ?: "Failed to save profile.")
                            }
                    }
                } else {
                    _authState.value = AuthState.Error(task.exception?.message ?: "Sign Up failed")
                }
            }
    }

    fun logout() {
        auth.signOut()
        _authState.value = AuthState.Unauthenticated
    }

    fun fetchUserProfile(uid: String) {
        viewModelScope.launch {
            try {
                val document = firestore.collection("users").document(uid).get().await()
                val profile = document.toObject(UserProfile::class.java)
                _userProfile.value = profile
            } catch (e: Exception) {
                Log.d("AuthViewModel", e.message.toString())
            }
        }
    }

    fun uploadProfileImage(uri: Uri) {
        viewModelScope.launch {
            val uid = auth.currentUser?.uid ?: return@launch

            try {
                val cloudinaryUrl = withContext(Dispatchers.IO) {
                    context.contentResolver.openInputStream(uri)?.let { inputStream ->
                        cloudinary.uploader()
                            .upload(
                                inputStream,
                                mapOf(
                                    "public_id" to uid,
                                    "folder" to "profile_pictures",
                                    "overwrite" to true
                                )
                            )
                            .get("secure_url") as String
                    } ?: throw Exception("Could not open input stream from URI")
                }

                firestore.collection("users").document(uid)
                    .update("photoUrl", cloudinaryUrl).await()

                fetchUserProfile(uid)

            } catch (e: Exception) {
                _authState.value = AuthState.Error(e.message ?: "Image upload failed.")
            }
        }
    }
}

sealed class AuthState {
    object Authenticated : AuthState()
    object Unauthenticated : AuthState()
    object Loading : AuthState()
    data class Error(val message: String) : AuthState()
}