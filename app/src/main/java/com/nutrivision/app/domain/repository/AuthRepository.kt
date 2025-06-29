package com.nutrivision.app.domain.repository

import android.net.Uri
import com.nutrivision.app.domain.model.User
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun getAuthState(): Flow<Boolean>
    suspend fun login(email: String, password: String): Flow<AuthResult>
    suspend fun signup(name: String, email: String, password: String): Flow<AuthResult>
    fun logout()
    suspend fun getUserProfile(): User?
    suspend fun uploadProfileImage(uri: Uri): Result<String>
    suspend fun updateUserProfile(user: User): Result<Unit>
}

sealed class AuthResult {
    data class Success(val user: User) : AuthResult()
    data class Error(val message: String) : AuthResult()
    object Loading : AuthResult()
}