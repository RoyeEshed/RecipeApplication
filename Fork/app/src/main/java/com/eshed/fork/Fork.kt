package com.eshed.fork

import android.app.Application
import com.eshed.fork.data.DebugRecipeRepository.Companion.instance
import com.eshed.fork.data.RecipeRepository
import com.eshed.fork.data.service.EdamamService
import com.eshed.fork.data.service.EdamamServiceCreator

class Fork : Application() {
    val repository: RecipeRepository = instance
    val edamamService: EdamamService = EdamamServiceCreator.create()
}