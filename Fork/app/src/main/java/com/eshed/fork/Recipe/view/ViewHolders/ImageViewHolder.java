package com.eshed.fork.Recipe.view.ViewHolders;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.eshed.fork.R;
import com.eshed.fork.Recipe.vm.component.ImageViewModel;
import com.eshed.fork.Recipe.vm.component.RecipeComponentViewModel;

public class ImageViewHolder extends RecipeViewHolder {
    ImageView imageView;

    public ImageViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.recipe_image);
    }

    @Override
    public void bind(RecipeComponentViewModel vm) {
        ImageViewModel imageViewModel = (ImageViewModel)vm;
        Glide.with(itemView).load(imageViewModel.imageURL).centerCrop().into(imageView);
    }
}