package com.eshed.fork

import android.app.Application
import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import com.eshed.fork.Data.service.EdamamService
import com.eshed.fork.Data.service.EdamamServiceCreator
import com.firebase.ui.storage.images.FirebaseImageLoader
import com.google.firebase.storage.StorageReference
import java.io.InputStream


class Fork : Application() {
   // val repository: RecipeRepository = instance
    val edamamService: EdamamService = EdamamServiceCreator.create()

}
