package com.nutrivision.app.domain.model

data class Product(
    val code: String,
    val name: String,
    val brand: String?,
    val imageUrl: String?,
    val nutriscore: String?,
    val energyKcal: Double?,
    val fat: Double?,
    val saturatedFat: Double?,
    val carbohydrates: Double?,
    val sugars: Double?,
    val proteins: Double?,
    val fiber: Double?,
    val salt: Double?
)
