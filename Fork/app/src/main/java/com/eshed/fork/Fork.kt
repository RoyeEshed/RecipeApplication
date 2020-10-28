package com.eshed.fork

import android.app.Application
import android.util.Log
import com.eshed.fork.data.DebugRecipeRepository.Companion.instance
import com.eshed.fork.data.RecipeRepository
import com.eshed.fork.data.model.NutritionalAnalysisRequest
import com.eshed.fork.data.model.NutritionalAnalysisResponse
import com.eshed.fork.data.service.EdamamService
import com.eshed.fork.data.service.EdamamServiceCreator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Fork : Application() {
    val repository: RecipeRepository = instance
    val edamamService: EdamamService = EdamamServiceCreator.create()
}