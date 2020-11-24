package com.eshed.fork.Recipe.view.ViewHolders;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.annotation.GlideModule;
import com.eshed.fork.R;
import com.eshed.fork.Recipe.view.FooterCallback;
import com.eshed.fork.Recipe.view.RecipeImageCallback;
import com.eshed.fork.Recipe.vm.component.ImageViewModel;
import com.eshed.fork.Recipe.vm.component.RecipeComponentViewModel;
import com.eshed.fork.Util.GlideApp;
import com.eshed.fork.Util.GlideAppModule;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class ImageViewHolder extends RecipeViewHolder {
    public RecipeImageCallback callback;
    ImageView imageView;

    public ImageViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.recipe_image);
    }

    @Override
    public void bind(RecipeComponentViewModel vm) {
        ImageViewModel imageViewModel = (ImageViewModel)vm;
        FirebaseStorage storage = FirebaseStorage.getInstance();
        Log.d("IMAGE URL", "bind: imageURL" + imageViewModel.imageURL);
        StorageReference gsReference = storage.getReferenceFromUrl(imageViewModel.imageURL);
        GlideApp.with(itemView).load(gsReference).centerCrop().into(imageView);

        if (imageViewModel.isEditable()) {
            imageView.setOnClickListener(v -> {
                if (callback != null) {
                    callback.recipeImageTapped();
                }
            });
        }
    }
}