package com.eshed.fork.Recipe.view.ViewHolders;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.eshed.fork.R;
import com.eshed.fork.Recipe.vm.component.NutritionViewModel;
import com.eshed.fork.Recipe.vm.component.RecipeComponentViewModel;

public class NutritionViewHolder extends RecipeViewHolder {
    TextView totalCalories;
    TextView caloriesPerServing;
    TextView servings;
    TextView carbohydrates;
    TextView protein;
    TextView fat;
    TextView transFat;
    TextView saturatedFat;
    TextView cholesterol;
    TextView sodium;
    TextView sugar;
    TextView fiber;

    public NutritionViewHolder(@NonNull View itemView) {
        super(itemView);
        totalCalories = itemView.findViewById(R.id.calories_total);
        caloriesPerServing = itemView.findViewById(R.id.calories_serving);
        servings = itemView.findViewById(R.id.servings);
        carbohydrates = itemView.findViewById(R.id.carbs);
        protein = itemView.findViewById(R.id.protein);
        fat = itemView.findViewById(R.id.fat);
        transFat = itemView.findViewById(R.id.trans_fat);
        saturatedFat = itemView.findViewById(R.id.saturated_fat);
        cholesterol = itemView.findViewById(R.id.cholesterol);
        sodium = itemView.findViewById(R.id.sodium);
        sugar = itemView.findViewById(R.id.sugar);
        fiber = itemView.findViewById(R.id.fiber);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void bind(RecipeComponentViewModel vm) {
        NutritionViewModel nutritionViewModel = (NutritionViewModel) vm;
        totalCalories.setText(nutritionViewModel.getTotalCalories());
        caloriesPerServing.setText(nutritionViewModel.getCaloriesPerServing());
        servings.setText("" + nutritionViewModel.getServings());
        carbohydrates.setText(nutritionViewModel.getCarbohydrates());
        protein.setText(nutritionViewModel.getProtein());
        fat.setText(nutritionViewModel.getFat());
        transFat.setText(nutritionViewModel.getTransFat());
        saturatedFat.setText(nutritionViewModel.getSaturatedFat());
        cholesterol.setText(nutritionViewModel.getCholesterol());
        sodium.setText(nutritionViewModel.getSodium());
        sugar.setText(nutritionViewModel.getSugar());
        fiber.setText(nutritionViewModel.getFiber());
    }
}
