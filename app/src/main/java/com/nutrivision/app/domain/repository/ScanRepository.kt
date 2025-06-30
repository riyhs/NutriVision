package com.nutrivision.app.domain.repository

import com.nutrivision.app.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ScanRepository {
    suspend fun fetchProductByBarcode(barcode: String): Product
    suspend fun insertHistoryItem(product: Product)
    fun getAllHistoryItems(): Flow<List<Product>>
    suspend fun getHistoryItemByCode(productCode: String): Product?
    suspend fun deleteHistoryItemById(itemId: Int)
    suspend fun clearAllHistory()
}