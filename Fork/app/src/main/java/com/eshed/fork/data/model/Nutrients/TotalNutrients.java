package com.eshed.fork.data.model.Nutrients;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TotalNutrients {

    @SerializedName("ENERC_KCAL")
    @Expose
    private Energy energy;
    @SerializedName("FAT")
    @Expose
    private Fat fat;
    @SerializedName("FASAT")
    @Expose
    private SaturatedFat saturatedFat;
    @SerializedName("FATRN")
    @Expose
    private TransFat transFat;
    @SerializedName("CHOCDF")
    @Expose
    private Carbohydrates carbohydrates;
    @SerializedName("FIBTG")
    @Expose
    private Fiber fiber;
    @SerializedName("SUGAR")
    @Expose
    private Sugar sugar;
    @SerializedName("PROCNT")
    @Expose
    private Protein protein;
    @SerializedName("CHOLE")
    @Expose
    private Cholesterol cholesterol;
    @SerializedName("NA")
    @Expose
    private Sodium sodium;

    public Energy getEnergy() {
        return energy;
    }

    public Fat getFat() {
        return fat;
    }

    public TransFat getTransFat() {
        return transFat;
    }

    public SaturatedFat getSaturatedFat() {
        return saturatedFat;
    }

    public Carbohydrates getCarbohydrates() {
        return carbohydrates;
    }

    public Fiber getFiber() {
        return fiber;
    }

    public Sugar getSugar() {
        return sugar;
    }

    public Protein getProtein() {
        return protein;
    }

    public Cholesterol getCholesterol() {
        return cholesterol;
    }

    public Sodium getSodium() {
        return sodium;
    }
}