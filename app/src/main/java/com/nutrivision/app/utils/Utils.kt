package com.nutrivision.app.utils

import android.util.Log


object Utils {
    fun getImageUrl(barcode: String, resolution: String = "400"): String? {
        var folderName = barcode
        if (folderName.length > 8) {
            folderName = folderName.replace(Regex("(...)(...)(...)(.*)"), "$1/$2/$3/$4")
        }

        val filename = "1.$resolution.jpg"

        val baseUrl = "https://images.openfoodfacts.org/images/products"

        return "$baseUrl/$folderName/$filename"
    }
}