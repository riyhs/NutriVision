package com.nutrivision.app.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nutrivision.app.data.local.entity.ScanHistoryItem
import kotlinx.coroutines.flow.Flow

@Dao
interface ScanHistoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHistoryItem(item: ScanHistoryItem)

    @Query("SELECT * FROM scan_history ORDER BY scanned_at DESC")
    fun getAllHistoryItems(): Flow<List<ScanHistoryItem>>

    @Query("SELECT * FROM scan_history WHERE product_code = :productCode LIMIT 1")
    suspend fun getHistoryItemByCode(productCode: String): ScanHistoryItem?

    @Query("DELETE FROM scan_history WHERE id = :itemId")
    suspend fun deleteHistoryItemById(itemId: Int)

    @Query("DELETE FROM scan_history")
    suspend fun clearAllHistory()
}