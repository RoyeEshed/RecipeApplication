package com.eshed.fork.data.model

data class Recipe @JvmOverloads constructor(
    val recipeID: Int = -1,
    val name: String = "",
    val imageURL: String,
    val contributor: String = "",
    val ingredients: MutableList<Ingredient> = mutableListOf(),
    val directions: MutableList<Direction> = mutableListOf(),
    val tags: MutableList<String> = mutableListOf(),
    var parentRecipeID: Int = -1,
    val comments: MutableList<Comment> = mutableListOf()
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
            tags = tags.map { String(it.toCharArray()) }.toMutableList(),
            comments = comments.map { it.copy() }.toMutableList()
        )
}