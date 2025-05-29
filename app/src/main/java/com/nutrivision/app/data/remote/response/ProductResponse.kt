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
        @SerializedName("brands_tags")
        val brandsTags: List<String>,
        @SerializedName("categories_tags")
        val categoriesTags: List<String>,
        @SerializedName("environmental_score_tags")
        val environmentalScoreTags: List<String>,
        @SerializedName("images")
        val images: Images,
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
        data class Images(
            @SerializedName("front_ar")
            val frontAr: FrontAr,
            @SerializedName("front_en")
            val frontEn: FrontEn,
            @SerializedName("ingredients_en")
            val ingredientsEn: IngredientsEn,
            @SerializedName("ingredients_fr")
            val ingredientsFr: IngredientsFr,
            @SerializedName("nutrition_th")
            val nutritionTh: NutritionTh,
            @SerializedName("1")
            val x1: X1,
            @SerializedName("2")
            val x2: X2,
            @SerializedName("3")
            val x3: X3,
            @SerializedName("4")
            val x4: X4,
            @SerializedName("5")
            val x5: X5,
            @SerializedName("6")
            val x6: X6,
            @SerializedName("7")
            val x7: X7
        ) {
            data class FrontAr(
                @SerializedName("angle")
                val angle: Int,
                @SerializedName("geometry")
                val geometry: String,
                @SerializedName("imgid")
                val imgid: String,
                @SerializedName("normalize")
                val normalize: Any?,
                @SerializedName("rev")
                val rev: String,
                @SerializedName("sizes")
                val sizes: Sizes,
                @SerializedName("white_magic")
                val whiteMagic: Any?,
                @SerializedName("x1")
                val x1: String,
                @SerializedName("x2")
                val x2: String,
                @SerializedName("y1")
                val y1: String,
                @SerializedName("y2")
                val y2: String
            ) {
                data class Sizes(
                    @SerializedName("full")
                    val full: Full,
                    @SerializedName("100")
                    val x100: X100,
                    @SerializedName("200")
                    val x200: X200,
                    @SerializedName("400")
                    val x400: X400
                ) {
                    data class Full(
                        @SerializedName("h")
                        val h: Int,
                        @SerializedName("w")
                        val w: Int
                    )

                    data class X100(
                        @SerializedName("h")
                        val h: Int,
                        @SerializedName("w")
                        val w: Int
                    )

                    data class X200(
                        @SerializedName("h")
                        val h: Int,
                        @SerializedName("w")
                        val w: Int
                    )

                    data class X400(
                        @SerializedName("h")
                        val h: Int,
                        @SerializedName("w")
                        val w: Int
                    )
                }
            }

            data class FrontEn(
                @SerializedName("angle")
                val angle: Any?,
                @SerializedName("geometry")
                val geometry: String,
                @SerializedName("imgid")
                val imgid: String,
                @SerializedName("normalize")
                val normalize: Any?,
                @SerializedName("rev")
                val rev: String,
                @SerializedName("sizes")
                val sizes: Sizes,
                @SerializedName("white_magic")
                val whiteMagic: Any?,
                @SerializedName("x1")
                val x1: Any?,
                @SerializedName("x2")
                val x2: Any?,
                @SerializedName("y1")
                val y1: Any?,
                @SerializedName("y2")
                val y2: Any?
            ) {
                data class Sizes(
                    @SerializedName("full")
                    val full: Full,
                    @SerializedName("100")
                    val x100: X100,
                    @SerializedName("200")
                    val x200: X200,
                    @SerializedName("400")
                    val x400: X400
                ) {
                    data class Full(
                        @SerializedName("h")
                        val h: Int,
                        @SerializedName("w")
                        val w: Int
                    )

                    data class X100(
                        @SerializedName("h")
                        val h: Int,
                        @SerializedName("w")
                        val w: Int
                    )

                    data class X200(
                        @SerializedName("h")
                        val h: Int,
                        @SerializedName("w")
                        val w: Int
                    )

                    data class X400(
                        @SerializedName("h")
                        val h: Int,
                        @SerializedName("w")
                        val w: Int
                    )
                }
            }

            data class IngredientsEn(
                @SerializedName("angle")
                val angle: Any?,
                @SerializedName("coordinates_image_size")
                val coordinatesImageSize: String,
                @SerializedName("geometry")
                val geometry: String,
                @SerializedName("imgid")
                val imgid: String,
                @SerializedName("normalize")
                val normalize: Any?,
                @SerializedName("rev")
                val rev: String,
                @SerializedName("sizes")
                val sizes: Sizes,
                @SerializedName("white_magic")
                val whiteMagic: Any?,
                @SerializedName("x1")
                val x1: Any?,
                @SerializedName("x2")
                val x2: Any?,
                @SerializedName("y1")
                val y1: Any?,
                @SerializedName("y2")
                val y2: Any?
            ) {
                data class Sizes(
                    @SerializedName("full")
                    val full: Full,
                    @SerializedName("100")
                    val x100: X100,
                    @SerializedName("200")
                    val x200: X200,
                    @SerializedName("400")
                    val x400: X400
                ) {
                    data class Full(
                        @SerializedName("h")
                        val h: Int,
                        @SerializedName("w")
                        val w: Int
                    )

                    data class X100(
                        @SerializedName("h")
                        val h: Int,
                        @SerializedName("w")
                        val w: Int
                    )

                    data class X200(
                        @SerializedName("h")
                        val h: Int,
                        @SerializedName("w")
                        val w: Int
                    )

                    data class X400(
                        @SerializedName("h")
                        val h: Int,
                        @SerializedName("w")
                        val w: Int
                    )
                }
            }

            data class IngredientsFr(
                @SerializedName("angle")
                val angle: Any?,
                @SerializedName("geometry")
                val geometry: String,
                @SerializedName("imgid")
                val imgid: String,
                @SerializedName("normalize")
                val normalize: String,
                @SerializedName("ocr")
                val ocr: Int,
                @SerializedName("orientation")
                val orientation: String,
                @SerializedName("rev")
                val rev: String,
                @SerializedName("sizes")
                val sizes: Sizes,
                @SerializedName("white_magic")
                val whiteMagic: String,
                @SerializedName("x1")
                val x1: Any?,
                @SerializedName("x2")
                val x2: Any?,
                @SerializedName("y1")
                val y1: Any?,
                @SerializedName("y2")
                val y2: Any?
            ) {
                data class Sizes(
                    @SerializedName("full")
                    val full: Full,
                    @SerializedName("100")
                    val x100: X100,
                    @SerializedName("200")
                    val x200: X200,
                    @SerializedName("400")
                    val x400: X400
                ) {
                    data class Full(
                        @SerializedName("h")
                        val h: Int,
                        @SerializedName("w")
                        val w: Int
                    )

                    data class X100(
                        @SerializedName("h")
                        val h: Int,
                        @SerializedName("w")
                        val w: Int
                    )

                    data class X200(
                        @SerializedName("h")
                        val h: Int,
                        @SerializedName("w")
                        val w: Int
                    )

                    data class X400(
                        @SerializedName("h")
                        val h: Int,
                        @SerializedName("w")
                        val w: Int
                    )
                }
            }

            data class NutritionTh(
                @SerializedName("angle")
                val angle: Int,
                @SerializedName("coordinates_image_size")
                val coordinatesImageSize: String,
                @SerializedName("geometry")
                val geometry: String,
                @SerializedName("imgid")
                val imgid: String,
                @SerializedName("normalize")
                val normalize: Any?,
                @SerializedName("rev")
                val rev: String,
                @SerializedName("sizes")
                val sizes: Sizes,
                @SerializedName("white_magic")
                val whiteMagic: Any?,
                @SerializedName("x1")
                val x1: String,
                @SerializedName("x2")
                val x2: String,
                @SerializedName("y1")
                val y1: String,
                @SerializedName("y2")
                val y2: String
            ) {
                data class Sizes(
                    @SerializedName("full")
                    val full: Full,
                    @SerializedName("100")
                    val x100: X100,
                    @SerializedName("200")
                    val x200: X200,
                    @SerializedName("400")
                    val x400: X400
                ) {
                    data class Full(
                        @SerializedName("h")
                        val h: Int,
                        @SerializedName("w")
                        val w: Int
                    )

                    data class X100(
                        @SerializedName("h")
                        val h: Int,
                        @SerializedName("w")
                        val w: Int
                    )

                    data class X200(
                        @SerializedName("h")
                        val h: Int,
                        @SerializedName("w")
                        val w: Int
                    )

                    data class X400(
                        @SerializedName("h")
                        val h: Int,
                        @SerializedName("w")
                        val w: Int
                    )
                }
            }

            data class X1(
                @SerializedName("sizes")
                val sizes: Sizes,
                @SerializedName("uploaded_t")
                val uploadedT: String,
                @SerializedName("uploader")
                val uploader: String
            ) {
                data class Sizes(
                    @SerializedName("full")
                    val full: Full,
                    @SerializedName("100")
                    val x100: X100,
                    @SerializedName("400")
                    val x400: X400
                ) {
                    data class Full(
                        @SerializedName("h")
                        val h: Int,
                        @SerializedName("w")
                        val w: Int
                    )

                    data class X100(
                        @SerializedName("h")
                        val h: Int,
                        @SerializedName("w")
                        val w: Int
                    )

                    data class X400(
                        @SerializedName("h")
                        val h: Int,
                        @SerializedName("w")
                        val w: Int
                    )
                }
            }

            data class X2(
                @SerializedName("sizes")
                val sizes: Sizes,
                @SerializedName("uploaded_t")
                val uploadedT: Int,
                @SerializedName("uploader")
                val uploader: String
            ) {
                data class Sizes(
                    @SerializedName("full")
                    val full: Full,
                    @SerializedName("100")
                    val x100: X100,
                    @SerializedName("400")
                    val x400: X400
                ) {
                    data class Full(
                        @SerializedName("h")
                        val h: Int,
                        @SerializedName("w")
                        val w: Int
                    )

                    data class X100(
                        @SerializedName("h")
                        val h: Int,
                        @SerializedName("w")
                        val w: Int
                    )

                    data class X400(
                        @SerializedName("h")
                        val h: Int,
                        @SerializedName("w")
                        val w: Int
                    )
                }
            }

            data class X3(
                @SerializedName("sizes")
                val sizes: Sizes,
                @SerializedName("uploaded_t")
                val uploadedT: Int,
                @SerializedName("uploader")
                val uploader: String
            ) {
                data class Sizes(
                    @SerializedName("full")
                    val full: Full,
                    @SerializedName("100")
                    val x100: X100,
                    @SerializedName("400")
                    val x400: X400
                ) {
                    data class Full(
                        @SerializedName("h")
                        val h: Int,
                        @SerializedName("w")
                        val w: Int
                    )

                    data class X100(
                        @SerializedName("h")
                        val h: Int,
                        @SerializedName("w")
                        val w: Int
                    )

                    data class X400(
                        @SerializedName("h")
                        val h: Int,
                        @SerializedName("w")
                        val w: Int
                    )
                }
            }

            data class X4(
                @SerializedName("sizes")
                val sizes: Sizes,
                @SerializedName("uploaded_t")
                val uploadedT: Int,
                @SerializedName("uploader")
                val uploader: String
            ) {
                data class Sizes(
                    @SerializedName("full")
                    val full: Full,
                    @SerializedName("100")
                    val x100: X100,
                    @SerializedName("400")
                    val x400: X400
                ) {
                    data class Full(
                        @SerializedName("h")
                        val h: Int,
                        @SerializedName("w")
                        val w: Int
                    )

                    data class X100(
                        @SerializedName("h")
                        val h: Int,
                        @SerializedName("w")
                        val w: Int
                    )

                    data class X400(
                        @SerializedName("h")
                        val h: Int,
                        @SerializedName("w")
                        val w: Int
                    )
                }
            }

            data class X5(
                @SerializedName("sizes")
                val sizes: Sizes,
                @SerializedName("uploaded_t")
                val uploadedT: Int,
                @SerializedName("uploader")
                val uploader: String
            ) {
                data class Sizes(
                    @SerializedName("full")
                    val full: Full,
                    @SerializedName("100")
                    val x100: X100,
                    @SerializedName("400")
                    val x400: X400
                ) {
                    data class Full(
                        @SerializedName("h")
                        val h: Int,
                        @SerializedName("w")
                        val w: Int
                    )

                    data class X100(
                        @SerializedName("h")
                        val h: Int,
                        @SerializedName("w")
                        val w: Int
                    )

                    data class X400(
                        @SerializedName("h")
                        val h: Int,
                        @SerializedName("w")
                        val w: Int
                    )
                }
            }

            data class X6(
                @SerializedName("sizes")
                val sizes: Sizes,
                @SerializedName("uploaded_t")
                val uploadedT: Int,
                @SerializedName("uploader")
                val uploader: String
            ) {
                data class Sizes(
                    @SerializedName("full")
                    val full: Full,
                    @SerializedName("100")
                    val x100: X100,
                    @SerializedName("400")
                    val x400: X400
                ) {
                    data class Full(
                        @SerializedName("h")
                        val h: Int,
                        @SerializedName("w")
                        val w: Int
                    )

                    data class X100(
                        @SerializedName("h")
                        val h: Int,
                        @SerializedName("w")
                        val w: Int
                    )

                    data class X400(
                        @SerializedName("h")
                        val h: Int,
                        @SerializedName("w")
                        val w: Int
                    )
                }
            }

            data class X7(
                @SerializedName("sizes")
                val sizes: Sizes,
                @SerializedName("uploaded_t")
                val uploadedT: Int,
                @SerializedName("uploader")
                val uploader: String
            ) {
                data class Sizes(
                    @SerializedName("full")
                    val full: Full,
                    @SerializedName("100")
                    val x100: X100,
                    @SerializedName("400")
                    val x400: X400
                ) {
                    data class Full(
                        @SerializedName("h")
                        val h: Int,
                        @SerializedName("w")
                        val w: Int
                    )

                    data class X100(
                        @SerializedName("h")
                        val h: Int,
                        @SerializedName("w")
                        val w: Int
                    )

                    data class X400(
                        @SerializedName("h")
                        val h: Int,
                        @SerializedName("w")
                        val w: Int
                    )
                }
            }
        }

        data class Nutriments(
            @SerializedName("alpha-linolenic-acid")
            val alphaLinolenicAcid: Int,
            @SerializedName("alpha-linolenic-acid_100g")
            val alphaLinolenicAcid100g: Int,
            @SerializedName("alpha-linolenic-acid_serving")
            val alphaLinolenicAcidServing: Int,
            @SerializedName("alpha-linolenic-acid_unit")
            val alphaLinolenicAcidUnit: String,
            @SerializedName("alpha-linolenic-acid_value")
            val alphaLinolenicAcidValue: Int,
            @SerializedName("arachidic-acid")
            val arachidicAcid: Double,
            @SerializedName("arachidic-acid_100g")
            val arachidicAcid100g: Double,
            @SerializedName("arachidic-acid_serving")
            val arachidicAcidServing: Double,
            @SerializedName("arachidic-acid_unit")
            val arachidicAcidUnit: String,
            @SerializedName("arachidic-acid_value")
            val arachidicAcidValue: Double,
            @SerializedName("arachidonic-acid")
            val arachidonicAcid: Double,
            @SerializedName("arachidonic-acid_100g")
            val arachidonicAcid100g: Int,
            @SerializedName("arachidonic-acid_serving")
            val arachidonicAcidServing: Double,
            @SerializedName("arachidonic-acid_unit")
            val arachidonicAcidUnit: String,
            @SerializedName("arachidonic-acid_value")
            val arachidonicAcidValue: Double,
            @SerializedName("behenic-acid")
            val behenicAcid: Int,
            @SerializedName("behenic-acid_100g")
            val behenicAcid100g: Int,
            @SerializedName("behenic-acid_serving")
            val behenicAcidServing: Int,
            @SerializedName("behenic-acid_unit")
            val behenicAcidUnit: String,
            @SerializedName("behenic-acid_value")
            val behenicAcidValue: Int,
            @SerializedName("carbohydrates")
            val carbohydrates: Int,
            @SerializedName("carbohydrates_100g")
            val carbohydrates100g: Double,
            @SerializedName("carbohydrates_serving")
            val carbohydratesServing: Int,
            @SerializedName("carbohydrates_unit")
            val carbohydratesUnit: String,
            @SerializedName("carbohydrates_value")
            val carbohydratesValue: Int,
            @SerializedName("energy")
            val energy: Int,
            @SerializedName("energy_100g")
            val energy100g: Int,
            @SerializedName("energy-kcal")
            val energyKcal: Int,
            @SerializedName("energy-kcal_100g")
            val energyKcal100g: Int,
            @SerializedName("energy-kcal_serving")
            val energyKcalServing: Int,
            @SerializedName("energy-kcal_unit")
            val energyKcalUnit: String,
            @SerializedName("energy-kcal_value")
            val energyKcalValue: Int,
            @SerializedName("energy-kcal_value_computed")
            val energyKcalValueComputed: Int,
            @SerializedName("energy_serving")
            val energyServing: Int,
            @SerializedName("energy_unit")
            val energyUnit: String,
            @SerializedName("energy_value")
            val energyValue: Int,
            @SerializedName("fat")
            val fat: Int,
            @SerializedName("fat_100g")
            val fat100g: Double,
            @SerializedName("fat_serving")
            val fatServing: Int,
            @SerializedName("fat_unit")
            val fatUnit: String,
            @SerializedName("fat_value")
            val fatValue: Int,
            @SerializedName("fiber")
            val fiber: Int,
            @SerializedName("fiber_100g")
            val fiber100g: Double,
            @SerializedName("fiber_serving")
            val fiberServing: Int,
            @SerializedName("fiber_unit")
            val fiberUnit: String,
            @SerializedName("fiber_value")
            val fiberValue: Int,
            @SerializedName("fruits-vegetables-legumes-estimate-from-ingredients_100g")
            val fruitsVegetablesLegumesEstimateFromIngredients100g: Int,
            @SerializedName("fruits-vegetables-legumes-estimate-from-ingredients_serving")
            val fruitsVegetablesLegumesEstimateFromIngredientsServing: Int,
            @SerializedName("fruits-vegetables-nuts-estimate-from-ingredients_100g")
            val fruitsVegetablesNutsEstimateFromIngredients100g: Int,
            @SerializedName("fruits-vegetables-nuts-estimate-from-ingredients_serving")
            val fruitsVegetablesNutsEstimateFromIngredientsServing: Int,
            @SerializedName("molybdenum")
            val molybdenum: Double,
            @SerializedName("molybdenum_100g")
            val molybdenum100g: Double,
            @SerializedName("molybdenum_serving")
            val molybdenumServing: Double,
            @SerializedName("molybdenum_unit")
            val molybdenumUnit: String,
            @SerializedName("molybdenum_value")
            val molybdenumValue: Double,
            @SerializedName("monounsaturated-fat")
            val monounsaturatedFat: Int,
            @SerializedName("monounsaturated-fat_100g")
            val monounsaturatedFat100g: Double,
            @SerializedName("monounsaturated-fat_serving")
            val monounsaturatedFatServing: Int,
            @SerializedName("monounsaturated-fat_unit")
            val monounsaturatedFatUnit: String,
            @SerializedName("monounsaturated-fat_value")
            val monounsaturatedFatValue: Int,
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
            @SerializedName("ph")
            val ph: Double,
            @SerializedName("ph_100g")
            val ph100g: Double,
            @SerializedName("ph_serving")
            val phServing: Double,
            @SerializedName("ph_unit")
            val phUnit: String,
            @SerializedName("ph_value")
            val phValue: Double,
            @SerializedName("polyunsaturated-fat")
            val polyunsaturatedFat: Int,
            @SerializedName("polyunsaturated-fat_100g")
            val polyunsaturatedFat100g: Double,
            @SerializedName("polyunsaturated-fat_serving")
            val polyunsaturatedFatServing: Int,
            @SerializedName("polyunsaturated-fat_unit")
            val polyunsaturatedFatUnit: String,
            @SerializedName("polyunsaturated-fat_value")
            val polyunsaturatedFatValue: Int,
            @SerializedName("proteins")
            val proteins: Int,
            @SerializedName("proteins_100g")
            val proteins100g: Double,
            @SerializedName("proteins_serving")
            val proteinsServing: Int,
            @SerializedName("proteins_unit")
            val proteinsUnit: String,
            @SerializedName("proteins_value")
            val proteinsValue: Int,
            @SerializedName("salt")
            val salt: Double,
            @SerializedName("salt_100g")
            val salt100g: Double,
            @SerializedName("salt_serving")
            val saltServing: Double,
            @SerializedName("salt_unit")
            val saltUnit: String,
            @SerializedName("salt_value")
            val saltValue: Double,
            @SerializedName("saturated-fat")
            val saturatedFat: Double,
            @SerializedName("saturated-fat_100g")
            val saturatedFat100g: Double,
            @SerializedName("saturated-fat_serving")
            val saturatedFatServing: Double,
            @SerializedName("saturated-fat_unit")
            val saturatedFatUnit: String,
            @SerializedName("saturated-fat_value")
            val saturatedFatValue: Double,
            @SerializedName("sodium")
            val sodium: Double,
            @SerializedName("sodium_100g")
            val sodium100g: Double,
            @SerializedName("sodium_serving")
            val sodiumServing: Double,
            @SerializedName("sodium_unit")
            val sodiumUnit: String,
            @SerializedName("sodium_value")
            val sodiumValue: Double,
            @SerializedName("sugars")
            val sugars: Int,
            @SerializedName("sugars_100g")
            val sugars100g: Double,
            @SerializedName("sugars_serving")
            val sugarsServing: Int,
            @SerializedName("sugars_unit")
            val sugarsUnit: String,
            @SerializedName("sugars_value")
            val sugarsValue: Int,
            @SerializedName("taurine")
            val taurine: Int,
            @SerializedName("taurine_100g")
            val taurine100g: Int,
            @SerializedName("taurine_serving")
            val taurineServing: Int,
            @SerializedName("taurine_unit")
            val taurineUnit: String,
            @SerializedName("taurine_value")
            val taurineValue: Int
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
                    val value: Double
                )
            }
        }
    }
}