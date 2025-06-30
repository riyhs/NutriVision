package com.nutrivision.app.domain.model

import com.nutrivision.app.utils.Gender

data class User (
    val uid: String,
    val displayName: String,
    val email: String,
    val age: Int,
    val gender: Gender? = null,
    val height: Float,
    val weight: Float,
    val photoUrl: String? = null
)