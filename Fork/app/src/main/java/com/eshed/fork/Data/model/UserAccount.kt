package com.eshed.fork.Data.model

import com.google.firebase.database.Exclude
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserAccount
@JvmOverloads constructor(
    var email: String = "",
    var uid: String = "",
    var username: String = "",
    var imageURL: String = "gs://fork-15014.appspot.com/images/users/user.svg",
    var favoritedRecipes: MutableList<Int> = mutableListOf(),
    var submittedRecipes: MutableList<Int> = mutableListOf()
) {

    @Exclude
    fun addFavoritedRecipe(recipeID: Int) {
        favoritedRecipes.add(recipeID)
    }

    @Exclude
    fun addSubmittedRecipe(recipeID: Int) {
        submittedRecipes.add(recipeID)
    }

    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "email" to email,
            "uid" to uid,
            "username" to username,
            "imageURL" to imageURL,
            "favoritedRecipes" to favoritedRecipes,
            "submittedRecipes" to submittedRecipes
        )
    }
}