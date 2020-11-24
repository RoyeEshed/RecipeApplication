package com.eshed.fork.Data.model

import com.google.firebase.database.Exclude
import java.util.HashMap

data class Ingredient
@JvmOverloads constructor(
    var amount: String = "",
    var ingredientName: String = "",
    var recipeID: Int = -1
) {

    @Exclude
    override fun toString(): String {
        return "$amount – $ingredientName"
    }

    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "amount" to amount,
            "ingredientName" to ingredientName,
            "recipeID" to recipeID
        )
    }
}