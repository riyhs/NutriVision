package com.nutrivision.app.utils

enum class Gender {
    MALE, FEMALE
}

object BMI {
    fun calculateBMI(weight: Float, height: Float): Float {
        val weightValue = weight
        val heightValue = height
        var bmi = 0.toFloat()

        if (weightValue > 0 && heightValue > 0) {
            val heightInMeters = heightValue / 100
            bmi = weightValue.toFloat() / (heightInMeters * heightInMeters).toFloat()
        }

        return bmi
    }

    fun getBmiCategory(bmi: Float, gender: Gender): String {
        return when (gender) {
            Gender.FEMALE -> {
                // Standar Asia-Pasifik (lebih ketat untuk overweight) untuk para ciwi
                when {
                    bmi < 18.5f -> "Underweight (Kurus)"
                    bmi < 23.0f -> "Normal weight (Normal)"
                    bmi < 25.0f -> "Overweight (Kelebihan berat badan)"
                    else -> "Obesity (Obesitas)"
                }
            }

            Gender.MALE -> {
                // Standar Internasional WHO untuk lanang
                when {
                    bmi < 18.5f -> "Underweight (Kurus)"
                    bmi < 25.0f -> "Normal weight (Normal)"
                    bmi < 30.0f -> "Overweight (Kelebihan berat badan)"
                    else -> "Obesity (Obesitas)"
                }
            }
        }
    }
}