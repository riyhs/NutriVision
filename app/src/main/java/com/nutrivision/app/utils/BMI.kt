package com.nutrivision.app.utils

import android.util.Log
import java.text.DecimalFormat

enum class Gender {
    MALE, FEMALE, NODATA
}

enum class BmiCategory(val description: String) {
    UNDERWEIGHT("Underweight (Kurus)"),
    NORMAL("Normal weight (Normal)"),
    OVERWEIGHT("Overweight (Kelebihan berat badan)"),
    OBESITY("Obesity (Obesitas)"),
    NULL("Tidak ada data")
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

        val df: DecimalFormat = DecimalFormat("#.#")
        val finalBMI = df.format(bmi).toFloat()

        return finalBMI
    }

    fun getBmiCategory(bmi: Float, gender: Gender): String {
        return when (gender) {
            Gender.FEMALE -> {
                // Standar Asia-Pasifik (lebih ketat untuk overweight) untuk para ciwi
                when {
                    bmi < 18.5f -> BmiCategory.UNDERWEIGHT.description
                    bmi < 23.0f -> BmiCategory.NORMAL.description
                    bmi < 25.0f -> BmiCategory.OVERWEIGHT.description
                    else -> BmiCategory.OBESITY.description
                }
            }

            Gender.MALE -> {
                // Standar Internasional WHO untuk lanang
                when {
                    bmi < 18.5f -> BmiCategory.UNDERWEIGHT.description
                    bmi < 25.0f -> BmiCategory.NORMAL.description
                    bmi < 30.0f -> BmiCategory.OVERWEIGHT.description
                    else -> BmiCategory.OBESITY.description
                }
            }

            Gender.NODATA -> {
                Log.d("BMI", "Gender data not available")
                when {
                    else -> BmiCategory.NULL.description
                }
            }
        }
    }
}