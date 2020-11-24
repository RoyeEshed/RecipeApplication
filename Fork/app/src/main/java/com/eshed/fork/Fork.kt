package com.eshed.fork

import android.app.Application
import com.eshed.fork.Data.RecipeRepository
import com.eshed.fork.Data.service.EdamamService
import com.eshed.fork.Data.service.EdamamServiceCreator
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.database.FirebaseDatabase

class Fork : Application() {
   // val repository: RecipeRepository = instance
    val edamamService: EdamamService = EdamamServiceCreator.create()
}