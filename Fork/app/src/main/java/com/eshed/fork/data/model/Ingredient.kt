package com.eshed.fork.data.model

data class Ingredient
@JvmOverloads constructor(
    var amount: String,
    var ingredientName: String,
    var recipeID: Int = -1
) {

    override fun toString(): String {
        return "$amount – $ingredientName"
    }
}