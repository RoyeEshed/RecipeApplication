package com.eshed.fork.Recipe.vm.component;

import com.eshed.fork.data.model.Nutrients.TotalNutrients;

public class NutritionViewModel implements RecipeComponentViewModel{
    private int calories;
    private String carbohydrates;
    private String protein;
    private String fat;

    public NutritionViewModel(int calories, TotalNutrients nutrients) {
        this.calories = calories;
        this.carbohydrates = "" + nutrients.getCHOCDF().getQuantity().intValue() + nutrients.getCHOCDF().getUnit();
        this.protein = "" + nutrients.getPROCNT().getQuantity().intValue() + nutrients.getPROCNT().getUnit();
        this.fat = "" + nutrients.getFAT().getQuantity().intValue() + nutrients.getFAT().getUnit();
    }

    public int getCalories() {
        return calories;
    }

    public String getCarbohydrates() {
        return carbohydrates;
    }

    public String getProtein() {
        return protein;
    }

    public String getFat() {
        return fat;
    }

    @Override
    public Type getType() {
        return Type.Nutrition;
    }
}
