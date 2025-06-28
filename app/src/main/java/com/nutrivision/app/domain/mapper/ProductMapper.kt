package com.nutrivision.app.domain.mapper

import com.nutrivision.app.data.local.entity.ScanHistoryItem
import com.nutrivision.app.data.remote.response.ProductResponse
import com.nutrivision.app.domain.model.Product
import com.nutrivision.app.utils.Utils
import com.nutrivision.app.utils.Utils.capitalizeWords

// Fungsi untuk mengubah ProductResponse (dari API) menjadi Product (Domain Model)
fun ProductResponse.toDomain(): Product {
    val nutriments = this.product.nutriments
    return Product(
        code = this.code,
        name = this.product.productName,
        brand = this.product.brandsTags.firstOrNull()
            ?.split(":")?.getOrNull(1)?.replace("-", " ")?.capitalizeWords()
            ?: this.product.brandsTags.firstOrNull()?.capitalizeWords(),
        imageUrl = Utils.getImageUrl(this.code),
        nutriscore = this.product.nutritionGrades.takeIf { it.isNotBlank() && it != "?" },
        energyKcal = nutriments.energyKcalServing.toString().toDoubleOrNull(),
        fat = nutriments.fatServing.toString().toDoubleOrNull(),
        saturatedFat = nutriments.saturatedFatServing,
        carbohydrates = nutriments.carbohydratesServing.toString().toDoubleOrNull(),
        sugars = nutriments.sugarsServing.toString().toDoubleOrNull(),
        proteins = nutriments.proteinsServing.toString().toDoubleOrNull(),
        fiber = nutriments.fiberServing.toString().toDoubleOrNull(),
        salt = nutriments.saltServing
    )
}

// Fungsi untuk mengubah ScanHistoryItem (dari Room DB) menjadi Product (Domain Model)
fun ScanHistoryItem.toDomain(): Product {
    return Product(
        code = this.productCode,
        name = this.productName,
        brand = this.productBrand,
        imageUrl = this.imageUrl,
        // Properti lain bisa null karena tidak disimpan di history
        nutriscore = null,
        energyKcal = null,
        fat = null,
        saturatedFat = null,
        carbohydrates = null,
        sugars = null,
        proteins = null,
        fiber = null,
        salt = null
    )
}

// Fungsi untuk mengubah Product (Domain Model) menjadi ScanHistoryItem (untuk disimpan ke Room DB)
fun Product.toEntity(): ScanHistoryItem {
    return ScanHistoryItem(
        productCode = this.code,
        productName = this.name,
        productBrand = this.brand,
        imageUrl = this.imageUrl
    )
}