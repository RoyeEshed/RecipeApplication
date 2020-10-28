package com.eshed.fork.Recipe.view.ViewHolders;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.eshed.fork.R;
import com.eshed.fork.Recipe.vm.component.NutritionViewModel;
import com.eshed.fork.Recipe.vm.component.RecipeComponentViewModel;

public class NutritionViewHolder extends RecipeViewHolder {
    TextView calories;
    TextView carbohydrates;
    TextView protein;
    TextView fat;

    public NutritionViewHolder(@NonNull View itemView) {
        super(itemView);
        calories = itemView.findViewById(R.id.calories);
        carbohydrates = itemView.findViewById(R.id.carbs);
        protein = itemView.findViewById(R.id.protein);
        fat = itemView.findViewById(R.id.fat);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void bind(RecipeComponentViewModel vm) {
        NutritionViewModel nutritionViewModel = (NutritionViewModel) vm;
        calories.setText("" + nutritionViewModel.getCalories());
        carbohydrates.setText(nutritionViewModel.getCarbohydrates());
        protein.setText(nutritionViewModel.getProtein());
        fat.setText(nutritionViewModel.getFat());
    }
}
