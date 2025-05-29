package com.nutrivision.app.di

import android.content.Context
import androidx.room.Room
import com.nutrivision.app.data.local.AppDatabase
import com.nutrivision.app.data.local.ScanHistoryDao
import com.nutrivision.app.data.remote.ApiService
import com.nutrivision.app.data.repository.ScanRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // DATA REMOTE
    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return Retrofit.Builder()
            .baseUrl("https://world.openfoodfacts.net/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideScanRepository(
        apiService: ApiService,
        scanHistoryDao: ScanHistoryDao
    ): ScanRepository {
        return ScanRepository(apiService, scanHistoryDao)
    }

    // DATA LOCAL
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "scan_history_database"
        )
            .fallbackToDestructiveMigration(true)
            .build()
    }

    @Provides
    @Singleton
    fun provideScanHistoryDao(appDatabase: AppDatabase): ScanHistoryDao {
        return appDatabase.scanHistoryDao()
    }
}