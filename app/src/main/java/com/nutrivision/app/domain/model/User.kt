package com.nutrivision.app.domain.model

data class User (
    val uid: String,
    val displayName: String,
    val email: String,
    val age: Int,
    val gender: String,
    val height: Int,
    val weight: Int,
    val photoUrl: String? = null
)