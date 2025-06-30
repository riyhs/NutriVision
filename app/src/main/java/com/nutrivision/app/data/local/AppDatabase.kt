package com.nutrivision.app.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nutrivision.app.data.local.entity.ScanHistoryItem

@Database(entities = [ScanHistoryItem::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun scanHistoryDao(): ScanHistoryDao
}