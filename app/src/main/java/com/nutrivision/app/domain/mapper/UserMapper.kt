package com.nutrivision.app.domain.mapper

import com.nutrivision.app.data.firebase.UserProfile
import com.nutrivision.app.domain.model.User

// Fungsi untuk mengubah UserProfile (dari Firebase) menjadi User (Domain Model)
fun UserProfile.toDomain(): User {
    return User(
        uid = this.uid,
        displayName = this.displayName,
        email = this.email,
        age = this.age ?: 0,
        gender = this.gender ?: "",
        height = this.height ?: 0,
        weight = this.weight ?: 0,
        photoUrl = this.photoUrl
    )
}