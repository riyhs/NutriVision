package com.nutrivision.app.di

import android.content.Context
import androidx.room.Room
import com.cloudinary.Cloudinary
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.nutrivision.app.BuildConfig
import com.nutrivision.app.data.local.AppDatabase
import com.nutrivision.app.data.local.ScanHistoryDao
import com.nutrivision.app.data.remote.ApiService
import com.nutrivision.app.data.repository.AuthRepositoryImpl
import com.nutrivision.app.data.repository.ScanRepositoryImpl
import com.nutrivision.app.domain.repository.AuthRepository
import com.nutrivision.app.domain.repository.ScanRepository
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

    // Repository
    @Provides
    @Singleton
    fun provideScanRepository(
        apiService: ApiService,
        scanHistoryDao: ScanHistoryDao
    ): ScanRepository {
        return ScanRepositoryImpl(apiService, scanHistoryDao)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(
        @ApplicationContext context: Context,
        auth: FirebaseAuth,
        firestore: FirebaseFirestore,
        cloudinary: Cloudinary
    ): AuthRepository {
        return AuthRepositoryImpl(context, auth, firestore, cloudinary)
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

    // FIREBASE
    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideFirebaseFirestore(): FirebaseFirestore = FirebaseFirestore.getInstance()

    // CLOUDINARY
    @Provides
    @Singleton
    fun provideCloudinary(): Cloudinary {
        val config = mapOf(
            "cloud_name" to BuildConfig.CLOUDINARY_CLOUD_NAME,
            "api_key" to BuildConfig.CLOUDINARY_API_KEY,
            "api_secret" to BuildConfig.CLOUDINARY_API_SECRET,
            "secure" to true
        )
        return Cloudinary(config)
    }
}