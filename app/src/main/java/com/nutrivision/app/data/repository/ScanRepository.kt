package com.nutrivision.app.data.repository

import com.nutrivision.app.data.local.ScanHistoryDao
import com.nutrivision.app.data.local.entity.ScanHistoryItem
import com.nutrivision.app.data.remote.ApiService
import com.nutrivision.app.data.remote.response.ProductResponse
import com.nutrivision.app.domain.mapper.toDomain
import com.nutrivision.app.domain.mapper.toEntity
import com.nutrivision.app.domain.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ScanRepository @Inject constructor(
    private val apiService: ApiService,
    private val scanHistoryDao: ScanHistoryDao
) {
    suspend fun fetchProductByBarcode(barcode: String): Product {
        val response = apiService.getProductByBarcode(barcode)
        return response.toDomain()
    }

    suspend fun insertHistoryItem(product: Product) {
        scanHistoryDao.insertHistoryItem(product.toEntity())
    }

    fun getAllHistoryItems(): Flow<List<Product>> {
        return scanHistoryDao.getAllHistoryItems().map { historyItems ->
            historyItems.map { it.toDomain() }
        }
    }

    suspend fun getHistoryItemByCode(productCode: String): Product? {
        return scanHistoryDao.getHistoryItemByCode(productCode)?.toDomain()
    }

    suspend fun deleteHistoryItemById(itemId: Int) {
        scanHistoryDao.deleteHistoryItemById(itemId)
    }

    suspend fun clearAllHistory() {
        scanHistoryDao.clearAllHistory()
    }
}