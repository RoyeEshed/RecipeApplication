package com.eshed.fork

import android.app.Application
import com.eshed.fork.Data.DebugRecipeRepository.Companion.instance
import com.eshed.fork.Data.RecipeRepository
import com.eshed.fork.Data.service.EdamamService
import com.eshed.fork.Data.service.EdamamServiceCreator

class Fork : Application() {
    val repository: RecipeRepository = instance
    val edamamService: EdamamService = EdamamServiceCreator.create()
}