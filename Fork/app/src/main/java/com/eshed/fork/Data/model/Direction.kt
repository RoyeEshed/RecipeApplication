package com.eshed.fork.Data.model

import com.google.firebase.database.Exclude

data class Direction
@JvmOverloads constructor(
    var directionNumber: Int = -1,
    var directionText: String = "",
    var recipeID: Int = -1
    ) {

    @Exclude
    override fun toString(): String {
        return "$directionNumber . $directionText"
    }

    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "directionNumber" to directionNumber,
            "directionText" to directionText,
            "recipeID" to recipeID
        )
    }
}

