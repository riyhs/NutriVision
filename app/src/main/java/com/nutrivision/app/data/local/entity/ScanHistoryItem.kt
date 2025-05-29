package com.nutrivision.app.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "scan_history",
    indices = [Index(value = ["product_code"], unique = true)]
)
data class ScanHistoryItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "product_code")
    val productCode: String,

    @ColumnInfo(name = "product_name")
    val productName: String,

    @ColumnInfo(name = "product_brand")
    val productBrand: String?,

    @ColumnInfo(name = "image_url")
    val imageUrl: String?,

    @ColumnInfo(name = "scanned_at")
    val scannedAt: Long = System.currentTimeMillis()
)