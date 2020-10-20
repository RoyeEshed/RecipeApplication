package com.eshed.fork.Recipe.view.ViewHolders;

import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.eshed.fork.R;
import com.eshed.fork.Recipe.vm.component.IngredientViewModel;
import com.eshed.fork.Recipe.vm.component.RecipeComponentViewModel;

public class IngredientViewHolder extends RecipeViewHolder {
    EditText amount;
    EditText ingredientText;

    public IngredientViewHolder(@NonNull View itemView) {
        super(itemView);
        amount = itemView.findViewById(R.id.ingredient_amount);
        ingredientText = itemView.findViewById(R.id.ingredient_text);
    }

    @Override
    public void bind(RecipeComponentViewModel vm) {
        IngredientViewModel ingredientViewModel = (IngredientViewModel)vm;
        amount.setText(ingredientViewModel.ingredient.getAmount());
        ingredientText.setText(ingredientViewModel.ingredient.getIngredientName());

        amount.setEnabled(ingredientViewModel.isEditable());
        ingredientText.setEnabled(ingredientViewModel.isEditable());
    }
}