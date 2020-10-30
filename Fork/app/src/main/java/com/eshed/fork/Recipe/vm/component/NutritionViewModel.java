package com.eshed.fork.Recipe.vm.component;

import com.eshed.fork.Data.model.Nutrients.TotalNutrients;

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
        this.carbohydrates = nutrients.getCarbohydrates().getQuantity().intValue() + nutrients.getCarbohydrates().getUnit();
        this.protein = nutrients.getProtein().getQuantity().intValue() + nutrients.getProtein().getUnit();
        this.cholesterol = nutrients.getCholesterol().getQuantity().intValue() + nutrients.getCholesterol().getUnit();
        this.fat = nutrients.getFat().getQuantity().intValue() + nutrients.getFat().getUnit();
        if (nutrients.getTransFat() != null) {
            this.transFat = nutrients.getTransFat().getQuantity().intValue() + nutrients.getTransFat().getUnit();
        } else {
            this.transFat = "--";
        }
        this.saturatedFat = nutrients.getSaturatedFat().getQuantity().intValue() + nutrients.getSaturatedFat().getUnit();
        this.sugar = nutrients.getSugar().getQuantity().intValue() + nutrients.getSugar().getUnit();
        this.fiber = nutrients.getFiber().getQuantity().intValue() + nutrients.getFiber().getUnit();
        this.sodium = nutrients.getSodium().getQuantity().intValue() + nutrients.getSodium().getUnit();
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
