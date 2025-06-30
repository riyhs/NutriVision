package com.nutrivision.app.domain.mapper

import android.util.Log
import com.nutrivision.app.data.firebase.UserProfile
import com.nutrivision.app.domain.model.User
import com.nutrivision.app.utils.Gender

// Fungsi untuk mengubah UserProfile (dari Firebase) menjadi User (Domain Model)
fun UserProfile.toDomain(): User {
    return User(
        uid = this.uid,
        displayName = this.displayName,
        email = this.email,
        age = this.age ?: 0,
        gender = try {
            this.gender?.let { Gender.valueOf(it.uppercase()) }
        } catch (e: IllegalArgumentException) {
            Log.d("User", "Invalid gender: $e")
            null
        },
        height = this.height ?: 0.toFloat(),
        weight = this.weight ?: 0.toFloat(),
        photoUrl = this.photoUrl
    )
}