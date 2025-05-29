package com.nutrivision.app.data.remote

import com.nutrivision.app.data.remote.response.ProductResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("product/{barcode}")
    suspend fun getProductByBarcode(
        @Path("barcode") barcode: String,
        @Query("fields") fields: String = "product_name,nutriscore_data,nutriments,nutrition_grades,images,brands_tags,categories_tags,ecoscore_tags,packaging_tags",
        @Query("cc") cc: String = "id",
        @Query("lc") lc: String = "id",
    ): ProductResponse

}