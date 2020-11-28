package com.eshed.fork

import android.app.Application
import com.eshed.fork.Data.service.EdamamService
import com.eshed.fork.Data.service.EdamamServiceCreator


class Fork : Application() {
    lateinit var uid: String

    // val repository: RecipeRepository = instance
    val edamamService: EdamamService = EdamamServiceCreator.create()
}
