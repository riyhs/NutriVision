package com.nutrivision.app.data.repository

import com.nutrivision.app.data.local.ScanHistoryDao
import com.nutrivision.app.data.remote.ApiService
import com.nutrivision.app.domain.mapper.toDomain
import com.nutrivision.app.domain.mapper.toEntity
import com.nutrivision.app.domain.model.Product
import com.nutrivision.app.domain.repository.ScanRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ScanRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val scanHistoryDao: ScanHistoryDao
) : ScanRepository {
    override suspend fun fetchProductByBarcode(barcode: String): Product {
        val response = apiService.getProductByBarcode(barcode)
        return response.toDomain()
    }

    override suspend fun insertHistoryItem(product: Product) {
        scanHistoryDao.insertHistoryItem(product.toEntity())
    }

    override fun getAllHistoryItems(): Flow<List<Product>> {
        return scanHistoryDao.getAllHistoryItems().map { historyItems ->
            historyItems.map { it.toDomain() }
        }
    }

    override suspend fun getHistoryItemByCode(productCode: String): Product? {
        return scanHistoryDao.getHistoryItemByCode(productCode)?.toDomain()
    }

    override suspend fun deleteHistoryItemById(itemId: Int) {
        scanHistoryDao.deleteHistoryItemById(itemId)
    }

    override suspend fun clearAllHistory() {
        scanHistoryDao.clearAllHistory()
    }
}