package com.eshed.fork.Recipe.ViewOptions.view;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.eshed.fork.R;
import com.eshed.fork.Recipe.ViewOptions.vm.ModificationCardViewModel;
import com.eshed.fork.Util.GlideApp;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class ModificationViewHolder extends RecyclerView.ViewHolder {
    public interface ModificationCardCallback {
        void cardTappedOn(ModificationCardViewModel vm);
    }

    private TextView recipeName;
    private ImageView recipeImage;
    private ConstraintLayout layout;
    private ModificationCardViewModel vm;

    public ModificationViewHolder.ModificationCardCallback callback;

    public ModificationViewHolder(@NonNull View itemView) {
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

    public void setViewModel(ModificationCardViewModel vm) {
        this.vm = vm;
        recipeName.setText(vm.getRecipe().getName());
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference gsReference = storage.getReferenceFromUrl(vm.getRecipe().getImageURL());
        GlideApp.with(itemView).load(gsReference).centerCrop().into(recipeImage);
    }
}