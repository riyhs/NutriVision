package com.nutrivision.app.data.repository

import android.content.Context
import android.net.Uri
import com.cloudinary.Cloudinary
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.nutrivision.app.data.model.UserProfile
import com.nutrivision.app.domain.mapper.toDomain
import com.nutrivision.app.domain.model.User
import com.nutrivision.app.domain.repository.AuthRepository
import com.nutrivision.app.domain.repository.AuthResult
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore,
    private val cloudinary: Cloudinary
) : AuthRepository {

    override fun getAuthState(): Flow<Boolean> = flow {
        emit(auth.currentUser != null)
    }

    override suspend fun login(email: String, password: String): Flow<AuthResult> = flow {
        emit(AuthResult.Loading)
        try {
            val result = auth.signInWithEmailAndPassword(email, password).await()
            val user = getUserProfile()
            if (user != null) {
                emit(AuthResult.Success(user))
            } else {
                emit(AuthResult.Error("Gagal mengambil profil pengguna"))
            }
        } catch (e: Exception) {
            emit(AuthResult.Error(e.message ?: "Login gagal"))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun signup(name: String, email: String, password: String): Flow<AuthResult> = flow {
        emit(AuthResult.Loading)
        try {
            val result = auth.createUserWithEmailAndPassword(email, password).await()
            val firebaseUser = result.user!!
            val newUserProfile = UserProfile(
                uid = firebaseUser.uid,
                displayName = name,
                email = firebaseUser.email ?: ""
            )
            firestore.collection("users").document(firebaseUser.uid).set(newUserProfile).await()
            emit(AuthResult.Success(newUserProfile.toDomain()))
        } catch (e: Exception) {
            emit(AuthResult.Error(e.message ?: "Sign up gagal"))
        }
    }.flowOn(Dispatchers.IO)

    override fun logout() {
        auth.signOut()
    }

    override suspend fun getUserProfile(): User? {
        return try {
            val uid = auth.currentUser?.uid ?: return null
            val document = firestore.collection("users").document(uid).get().await()
            document.toObject(UserProfile::class.java)?.toDomain()
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun uploadProfileImage(uri: Uri): Result<String> {
        return try {
            val uid = auth.currentUser?.uid ?: return Result.failure(Exception("User belum login"))
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
                } ?: throw Exception("Tidak dapat membuka URI")
            }

            firestore.collection("users").document(uid)
                .update("photoUrl", cloudinaryUrl).await()

            Result.success(cloudinaryUrl)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}