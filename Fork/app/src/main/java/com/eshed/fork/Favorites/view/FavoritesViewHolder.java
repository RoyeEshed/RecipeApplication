package com.eshed.fork.Favorites.view;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.eshed.fork.Favorites.vm.FavoritesCardViewModel;
import com.eshed.fork.R;
import com.eshed.fork.Util.GlideApp;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class FavoritesViewHolder extends RecyclerView.ViewHolder {
    public interface FavoritesCardCallback {
        void cardTappedOn(FavoritesCardViewModel vm);
    }

    private TextView recipeName;
    private ImageView recipeImage;
    private ConstraintLayout layout;
    private FavoritesCardViewModel vm;

    public FavoritesViewHolder.FavoritesCardCallback callback;

    public FavoritesViewHolder(@NonNull View itemView) {
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

    public void setViewModel(FavoritesCardViewModel vm) {
        this.vm = vm;
        recipeName.setText(vm.getRecipe().getName());
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference gsReference = storage.getReferenceFromUrl(vm.getRecipe().getImageURL());
        GlideApp.with(itemView).load(gsReference).centerCrop().into(recipeImage);
    }
}