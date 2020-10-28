package com.eshed.fork.data.model

import com.google.gson.annotations.SerializedName

data class NutritionalAnalysisRequest(
    @SerializedName("title") val title: String? = null,
    @SerializedName("prep") val directions: String? = null,
    @SerializedName("yield") val yield: String? = null,
    @SerializedName("ingr") val ingredients: List<String>? = null
) {
    companion object {
        @JvmStatic
        fun fromRecipe(recipe: Recipe) = NutritionalAnalysisRequest(
                title = recipe.name,
                ingredients = recipe.ingredients.map {
                    "${it.amount} ${it.ingredientName}"
                },
                directions = recipe.directions.joinToString(" ") {
                    it.directionText
                }
        )
    }
}