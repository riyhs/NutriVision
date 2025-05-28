package com.nutrivision.app.data.repository

import com.nutrivision.app.data.remote.ApiService
import com.nutrivision.app.data.remote.response.ProductResponse
import javax.inject.Inject

class ScanRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun fetchProductByBarcode(barcode: String): ProductResponse {
        return apiService.getProductByBarcode(barcode)
    }
}