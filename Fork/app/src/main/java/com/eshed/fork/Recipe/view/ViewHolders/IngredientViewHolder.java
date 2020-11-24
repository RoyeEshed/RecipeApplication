package com.eshed.fork.Recipe.view.ViewHolders;

import android.graphics.PorterDuff;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.eshed.fork.R;
import com.eshed.fork.Recipe.vm.component.IngredientViewModel;
import com.eshed.fork.Recipe.vm.component.RecipeComponentViewModel;

public class IngredientViewHolder extends RecipeViewHolder {
    private EditText amount;
    private EditText ingredientText;
    private IngredientViewModel ingredientViewModel;

    public IngredientViewHolder(@NonNull View itemView) {
        super(itemView);
        amount = itemView.findViewById(R.id.ingredient_amount);
        ingredientText = itemView.findViewById(R.id.ingredient_text);

        amount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                ingredientViewModel.changeAmount(amount.getText().toString());
            }
        });

        ingredientText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                ingredientViewModel.changeIngredientName(ingredientText.getText().toString());
            }
        });
    }

    @Override
    public void bind(RecipeComponentViewModel vm) {
        ingredientViewModel = (IngredientViewModel) vm;
        amount.setText(ingredientViewModel.ingredient.getAmount());
        ingredientText.setText(ingredientViewModel.ingredient.getIngredientName());

        if (!ingredientViewModel.isEditable()) {
            amount.setBackgroundTintMode(PorterDuff.Mode.CLEAR);
            ingredientText.setBackgroundTintMode(PorterDuff.Mode.CLEAR);
        } else {
            amount.setBackgroundTintMode(PorterDuff.Mode.MULTIPLY);
            ingredientText.setBackgroundTintMode(PorterDuff.Mode.MULTIPLY);
        }

        amount.setEnabled(ingredientViewModel.isEditable());
        ingredientText.setEnabled(ingredientViewModel.isEditable());
    }
}