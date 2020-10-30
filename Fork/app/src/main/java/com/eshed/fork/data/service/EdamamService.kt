package com.eshed.fork.data.service

import com.eshed.fork.data.model.NutritionalAnalysisRequest
import com.eshed.fork.data.model.NutritionalAnalysisResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface EdamamService {
    companion object {
        const val APP_KEY = "b37f517ab7e31660fbfee27c3d12e77c"
        const val APP_ID = "3b9b7d5c"
    }

    @Headers("Accept: application/json")
    @POST("/api/nutrition-details?app_id=3b9b7d5c&app_key=b37f517ab7e31660fbfee27c3d12e77c")
    fun getNutritionAnalysis(@Body recipeRequest: NutritionalAnalysisRequest): Call<NutritionalAnalysisResponse>
}