package com.eshed.fork.Recipe.view.ViewHolders;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.eshed.fork.Recipe.vm.component.RecipeComponentViewModel;

public abstract class RecipeViewHolder extends RecyclerView.ViewHolder {
    public RecipeViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public abstract void bind(RecipeComponentViewModel vm);
}