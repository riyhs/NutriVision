package com.nutrivision.app.data.repository

import com.nutrivision.app.data.local.ScanHistoryDao
import com.nutrivision.app.data.local.entity.ScanHistoryItem
import com.nutrivision.app.data.remote.ApiService
import com.nutrivision.app.data.remote.response.ProductResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ScanRepository @Inject constructor(
    private val apiService: ApiService,
    private val scanHistoryDao: ScanHistoryDao
) {
    suspend fun fetchProductByBarcode(barcode: String): ProductResponse {
        return apiService.getProductByBarcode(barcode)
    }

    suspend fun insertHistoryItem(item: ScanHistoryItem) {
        scanHistoryDao.insertHistoryItem(item)
    }

    fun getAllHistoryItems(): Flow<List<ScanHistoryItem>> {
        return scanHistoryDao.getAllHistoryItems()
    }

    suspend fun getHistoryItemByCode(productCode: String): ScanHistoryItem? {
        return scanHistoryDao.getHistoryItemByCode(productCode)
    }

    suspend fun deleteHistoryItemById(itemId: Int) {
        scanHistoryDao.deleteHistoryItemById(itemId)
    }

    suspend fun clearAllHistory() {
        scanHistoryDao.clearAllHistory()
    }
}