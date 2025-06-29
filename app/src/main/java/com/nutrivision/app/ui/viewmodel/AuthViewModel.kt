package com.nutrivision.app.ui.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nutrivision.app.domain.model.User
import com.nutrivision.app.domain.repository.AuthRepository
import com.nutrivision.app.domain.repository.AuthResult
import com.nutrivision.app.utils.Gender
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {
    private val _authState = MutableStateFlow<AuthState>(AuthState.Unauthenticated)
    val authState: MutableStateFlow<AuthState> = _authState

    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> = _user

    init {
        checkAuthStatus()
    }

    fun checkAuthStatus() {
        viewModelScope.launch {
            repository.getAuthState().collect { isAuthenticated ->
                if (isAuthenticated) {
                    _authState.value = AuthState.Authenticated
                    fetchUserProfile()
                } else {
                    _authState.value = AuthState.Unauthenticated
                    _user.value = null
                }
            }
        }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            repository.login(email, password).collect { result ->
                handleAuthResult(result)
            }
        }
    }

    fun signup(name: String, email: String, password: String) {
        viewModelScope.launch {
            repository.signup(name, email, password).collect { result ->
                handleAuthResult(result)
            }
        }
    }

    private fun handleAuthResult(result: AuthResult) {
        when (result) {
            is AuthResult.Success -> {
                _authState.value = AuthState.Authenticated
                _user.value = result.user
            }
            is AuthResult.Error -> _authState.value = AuthState.Error(result.message)
            AuthResult.Loading -> _authState.value = AuthState.Loading
        }
    }


    fun logout() {
        repository.logout()
        _authState.value = AuthState.Unauthenticated
        _user.value = null
    }

    fun fetchUserProfile() {
        viewModelScope.launch {
            val userProfile = repository.getUserProfile()
            _user.value = userProfile
        }
    }

    fun uploadProfileImage(uri: Uri) {
        viewModelScope.launch {
            val result = repository.uploadProfileImage(uri)
            if (result.isSuccess) {
                fetchUserProfile()
            } else {
                _authState.value = AuthState.Error(result.exceptionOrNull()?.message ?: "Upload gambar profil gagal")
            }
        }
    }

    fun saveUserProfile(displayName: String, age: String, gender: String, height: String, weight: String) {
        viewModelScope.launch {
            val currentUser = _user.value ?: return@launch

            val updatedUser = currentUser.copy(
                displayName = displayName,
                age = age.toIntOrNull() ?: currentUser.age,
                gender = Gender.valueOf(gender.uppercase()),
                height = height.toFloatOrNull() ?: currentUser.height,
                weight = weight.toFloatOrNull() ?: currentUser.weight
            )

            val result = repository.updateUserProfile(updatedUser)

            if (result.isSuccess) {
                _user.value = updatedUser
            } else {
                _authState.value = AuthState.Error(result.exceptionOrNull()?.message ?: "Update gagal")
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