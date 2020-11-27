package com.eshed.fork.Data.model

import com.google.firebase.database.Exclude
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserAccount
@JvmOverloads constructor(
    var email: String = "",
    var uid: String = "",
    var userInfo: String = "",
    var username: String = "",
    var imageURL: String = "gs://fork-15014.appspot.com/images/users/user.svg",
    var favoritedRecipes: MutableList<String> = mutableListOf(),
    var submittedRecipes: MutableList<Int> = mutableListOf(),
    var tasks: MutableList<String> = mutableListOf()
) {

    @Exclude
    fun addFavoritedRecipe(recipeID: String) {
        favoritedRecipes.add(recipeID)
    }

    @Exclude
    fun addSubmittedRecipe(recipeID: String?) {}

    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "email" to email,
            "uid" to uid,
            "userInfo" to userInfo,
            "username" to username,
            "imageURL" to imageURL,
            "favoritedRecipes" to favoritedRecipes,
            "submittedRecipes" to submittedRecipes,
            "tasks" to tasks
        )
    }
}