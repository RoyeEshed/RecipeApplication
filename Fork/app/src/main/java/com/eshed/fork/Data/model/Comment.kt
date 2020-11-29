package com.eshed.fork.Data.model

import com.google.firebase.database.Exclude

data class Comment
@JvmOverloads constructor(
    var content: String = "",
    var uid: String = "",
    var uimg: String = "",
    var timestamp: String = "",
    var recipeID: Int = -1
){

    @Exclude
    override fun toString(): String{
        return "$uid - $uimg"
    }

    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "content" to content,
            "uid" to uid,
            "uimg" to uimg,
            "timestamp" to timestamp
        )
    }
}