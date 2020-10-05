package com.eshed.fork.Browse.view;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.eshed.fork.Browse.vm.RecipeViewModel;
import com.eshed.fork.R;

public class RecipeViewHolder extends RecyclerView.ViewHolder {
    public interface RecipeCardCallback {
        void cardTappedOn(RecipeViewModel vm);
    }

    private TextView recipeName;
    private TextView modifications;
    private ImageView recipeImage;
    private LinearLayout layout;
    private RecipeViewModel vm;

    public RecipeViewHolder.RecipeCardCallback callback;

    public RecipeViewHolder(@NonNull View itemView) {
        super(itemView);

        recipeName = itemView.findViewById(R.id.recipe_name);
        recipeImage = itemView.findViewById(R.id.recipe_image);
        layout = itemView.findViewById(R.id.parent_layout);

        layout.setOnClickListener(v -> {
            if (callback != null && vm != null) {
                callback.cardTappedOn(vm);
            }
        });

    }

    public void setViewModel(RecipeViewModel vm) {
        this.vm = vm;
        recipeName.setText(vm.getRecipe().getName());
        recipeImage.setImageResource(vm.getRecipe().getImageResource());
    }
}