package com.nutrivision.app.data.remote.response


import com.google.gson.annotations.SerializedName

data class ProductResponse(
    @SerializedName("code")
    val code: String,
    @SerializedName("product")
    val product: Product,
    @SerializedName("status")
    val status: Int,
    @SerializedName("status_verbose")
    val statusVerbose: String
) {
    data class Product(
        @SerializedName("nutriments")
        val nutriments: Nutriments,
        @SerializedName("nutriscore_data")
        val nutriscoreData: NutriscoreData,
        @SerializedName("nutrition_grades")
        val nutritionGrades: String,
        @SerializedName("product_name")
        val productName: String,
        @SerializedName("schema_version")
        val schemaVersion: Int
    ) {
        data class Nutriments(
            @SerializedName("carbohydrates")
            val carbohydrates: Double,
            @SerializedName("carbohydrates_100g")
            val carbohydrates100g: Double,
            @SerializedName("carbohydrates_unit")
            val carbohydratesUnit: String,
            @SerializedName("carbohydrates_value")
            val carbohydratesValue: Double,
            @SerializedName("energy")
            val energy: Int,
            @SerializedName("energy_100g")
            val energy100g: Int,
            @SerializedName("energy-kcal")
            val energyKcal: Int,
            @SerializedName("energy-kcal_100g")
            val energyKcal100g: Int,
            @SerializedName("energy-kcal_unit")
            val energyKcalUnit: String,
            @SerializedName("energy-kcal_value")
            val energyKcalValue: Int,
            @SerializedName("energy-kcal_value_computed")
            val energyKcalValueComputed: Double,
            @SerializedName("energy_unit")
            val energyUnit: String,
            @SerializedName("energy_value")
            val energyValue: Int,
            @SerializedName("fat")
            val fat: Double,
            @SerializedName("fat_100g")
            val fat100g: Double,
            @SerializedName("fat_unit")
            val fatUnit: String,
            @SerializedName("fat_value")
            val fatValue: Double,
            @SerializedName("fruits-vegetables-legumes-estimate-from-ingredients_100g")
            val fruitsVegetablesLegumesEstimateFromIngredients100g: Int,
            @SerializedName("fruits-vegetables-legumes-estimate-from-ingredients_serving")
            val fruitsVegetablesLegumesEstimateFromIngredientsServing: Int,
            @SerializedName("fruits-vegetables-nuts-estimate-from-ingredients_100g")
            val fruitsVegetablesNutsEstimateFromIngredients100g: Double,
            @SerializedName("fruits-vegetables-nuts-estimate-from-ingredients_serving")
            val fruitsVegetablesNutsEstimateFromIngredientsServing: Double,
            @SerializedName("nova-group")
            val novaGroup: Int,
            @SerializedName("nova-group_100g")
            val novaGroup100g: Int,
            @SerializedName("nova-group_serving")
            val novaGroupServing: Int,
            @SerializedName("nutrition-score-fr")
            val nutritionScoreFr: Int,
            @SerializedName("nutrition-score-fr_100g")
            val nutritionScoreFr100g: Int,
            @SerializedName("proteins")
            val proteins: Double,
            @SerializedName("proteins_100g")
            val proteins100g: Double,
            @SerializedName("proteins_unit")
            val proteinsUnit: String,
            @SerializedName("proteins_value")
            val proteinsValue: Double,
            @SerializedName("salt")
            val salt: Double,
            @SerializedName("salt_100g")
            val salt100g: Double,
            @SerializedName("salt_unit")
            val saltUnit: String,
            @SerializedName("salt_value")
            val saltValue: Double,
            @SerializedName("saturated-fat")
            val saturatedFat: Double,
            @SerializedName("saturated-fat_100g")
            val saturatedFat100g: Double,
            @SerializedName("saturated-fat_unit")
            val saturatedFatUnit: String,
            @SerializedName("saturated-fat_value")
            val saturatedFatValue: Double,
            @SerializedName("sodium")
            val sodium: Double,
            @SerializedName("sodium_100g")
            val sodium100g: Double,
            @SerializedName("sodium_unit")
            val sodiumUnit: String,
            @SerializedName("sodium_value")
            val sodiumValue: Double,
            @SerializedName("sugars")
            val sugars: Double,
            @SerializedName("sugars_100g")
            val sugars100g: Double,
            @SerializedName("sugars_unit")
            val sugarsUnit: String,
            @SerializedName("sugars_value")
            val sugarsValue: Double
        )

        data class NutriscoreData(
            @SerializedName("components")
            val components: Components,
            @SerializedName("count_proteins")
            val countProteins: Int,
            @SerializedName("count_proteins_reason")
            val countProteinsReason: String,
            @SerializedName("grade")
            val grade: String,
            @SerializedName("is_beverage")
            val isBeverage: Int,
            @SerializedName("is_cheese")
            val isCheese: Int,
            @SerializedName("is_fat_oil_nuts_seeds")
            val isFatOilNutsSeeds: Int,
            @SerializedName("is_red_meat_product")
            val isRedMeatProduct: Int,
            @SerializedName("is_water")
            val isWater: Int,
            @SerializedName("negative_points")
            val negativePoints: Int,
            @SerializedName("negative_points_max")
            val negativePointsMax: Int,
            @SerializedName("positive_nutrients")
            val positiveNutrients: List<String>,
            @SerializedName("positive_points")
            val positivePoints: Int,
            @SerializedName("positive_points_max")
            val positivePointsMax: Int,
            @SerializedName("score")
            val score: Int
        ) {
            data class Components(
                @SerializedName("negative")
                val negative: List<Negative>,
                @SerializedName("positive")
                val positive: List<Positive>
            ) {
                data class Negative(
                    @SerializedName("id")
                    val id: String,
                    @SerializedName("points")
                    val points: Int,
                    @SerializedName("points_max")
                    val pointsMax: Int,
                    @SerializedName("unit")
                    val unit: String,
                    @SerializedName("value")
                    val value: Double
                )

                data class Positive(
                    @SerializedName("id")
                    val id: String,
                    @SerializedName("points")
                    val points: Int,
                    @SerializedName("points_max")
                    val pointsMax: Int,
                    @SerializedName("unit")
                    val unit: String,
                    @SerializedName("value")
                    val value: Int?
                )
            }
        }
    }
}