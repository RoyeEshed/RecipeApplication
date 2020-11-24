package com.eshed.fork.Data.model

import com.google.firebase.database.Exclude
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Recipe
@JvmOverloads constructor(
    var recipeID: Int = -1,
    var name: String = "",
    var imageURL: String = "",
    var contributor: String = "",
    var ingredients: MutableList<Ingredient> = mutableListOf(),
    var directions: MutableList<Direction> = mutableListOf(),
    var tags: MutableList<String> = mutableListOf<String>(),
    var parentRecipeID: Int = -1
) {
    fun deepCopy(
        recipeID: Int,
        name: String,
        parentRecipeID: Int
    ): Recipe = copy(
            recipeID = recipeID,
            name = name,
            parentRecipeID = parentRecipeID,
            ingredients = ingredients.map { it.copy() }.toMutableList(),
            directions = directions.map { it.copy() }.toMutableList(),
            tags = tags.map {  it.capitalize() }.toMutableList()
        )

    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "recipeID" to recipeID,
            "name" to name,
            "imageURL" to imageURL,
            "contributor" to contributor,
            "ingredients" to ingredients,
            "directions" to directions,
            "tags" to tags,
            "parentRecipeID" to parentRecipeID

        )
    }
}