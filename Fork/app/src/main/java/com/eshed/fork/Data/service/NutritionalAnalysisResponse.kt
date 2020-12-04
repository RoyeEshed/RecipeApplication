package com.eshed.fork.Data.service

import com.eshed.fork.Data.model.Nutrients.TotalNutrients
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class NutritionalAnalysisResponse {
    @SerializedName("uri")
    @Expose
    private var uri: String? = null

    @SerializedName("yield")
    @Expose
    private var yield: Double? = null

    @SerializedName("calories")
    @Expose
    private var calories: Int? = null

    @SerializedName("dietLabels")
    @Expose
    private var dietLabels: Array<String>? = null

    @SerializedName("healthLabels")
    @Expose
    private var healthLabels: Array<String>? = null

    @SerializedName("totalNutrients")
    @Expose
    private var totalNutrients: TotalNutrients? = null

    fun getYield(): Double? { return `yield` }

    fun getCalories(): Int? { return calories }

    fun getTotalNutrients(): TotalNutrients? { return totalNutrients }
}