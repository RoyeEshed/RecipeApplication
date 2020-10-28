package com.eshed.fork.Recipe.vm.component;

import com.eshed.fork.data.model.Nutrients.TotalNutrients;

public class NutritionViewModel implements RecipeComponentViewModel{
    private double servings;
    private String totalCalories;
    private String caloriesPerServing;
    private String carbohydrates;
    private String protein;
    private String cholesterol;
    private String fat;
    private String transFat;
    private String saturatedFat;
    private String sugar;
    private String fiber;
    private String sodium;

    public NutritionViewModel(int calories, TotalNutrients nutrients, double servings) {
        this.servings = servings;
        this.totalCalories = (calories) + " kcal";
        this.caloriesPerServing = ((int) (calories / servings)) + " kcal";
        this.carbohydrates = nutrients.getCHOCDF().getQuantity().intValue() + nutrients.getCHOCDF().getUnit();
        this.protein = nutrients.getPROCNT().getQuantity().intValue() + nutrients.getPROCNT().getUnit();
        this.cholesterol = nutrients.getCHOLE().getQuantity().intValue() + nutrients.getCHOLE().getUnit();
        this.fat = nutrients.getFAT().getQuantity().intValue() + nutrients.getFAT().getUnit();
        if (nutrients.getFATRN() != null) {
            this.transFat = nutrients.getFATRN().getQuantity().intValue() + nutrients.getFATRN().getUnit();
        } else {
            this.transFat = "--";
        }
        this.saturatedFat = nutrients.getFASAT().getQuantity().intValue() + nutrients.getFASAT().getUnit();
        this.sugar = nutrients.getSUGAR().getQuantity().intValue() + nutrients.getSUGAR().getUnit();
        this.fiber = nutrients.getFIBTG().getQuantity().intValue() + nutrients.getFIBTG().getUnit();
        this.sodium = nutrients.getNA().getQuantity().intValue() + nutrients.getNA().getUnit();
    }

    public String getTotalCalories() {
        return totalCalories;
    }

    public String getCaloriesPerServing() {
        return caloriesPerServing;
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

    public double getServings() { return servings; }

    public String getCholesterol() { return cholesterol; }

    public String getTransFat() { return transFat; }

    public String getSaturatedFat() { return saturatedFat; }

    public String getSugar() { return sugar; }

    public String getFiber() { return fiber; }

    public String getSodium() { return sodium; }

    @Override
    public Type getType() {
        return Type.Nutrition;
    }
}
