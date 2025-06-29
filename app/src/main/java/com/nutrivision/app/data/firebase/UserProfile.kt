package com.nutrivision.app.data.firebase

import com.google.firebase.Timestamp

data class UserProfile(
    val uid: String = "",
    val displayName: String = "",
    val email: String = "",
    val photoUrl: String? = null,
    val age: Int? = null,
    val gender: String? = null,
    val height: Float? = null,
    val weight: Float? = null,
    val createdAt: Timestamp = Timestamp.now()
)
