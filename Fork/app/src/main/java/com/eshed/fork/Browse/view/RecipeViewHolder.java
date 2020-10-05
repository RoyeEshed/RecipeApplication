package com.eshed.fork.Browse.view;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.eshed.fork.Browse.vm.RecipeViewModel;
import com.eshed.fork.R;

public class RecipeViewHolder extends RecyclerView.ViewHolder {

    private TextView recipeName;
    private TextView modifications;
    private ImageView recipeImage;

    public RecipeViewHolder(@NonNull View itemView) {
        super(itemView);

        recipeName = itemView.findViewById(R.id.recipe_name);
        recipeImage = itemView.findViewById(R.id.recipe_image);


    }

    public void setViewModel(RecipeViewModel vm) {
        recipeName.setText(vm.getRecipe().getName());
        recipeImage.setImageResource(vm.getRecipe().getImageResource());
    }
}