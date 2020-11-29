package com.eshed.fork.Recipe.ViewOptions.view;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.eshed.fork.R;
import com.eshed.fork.Recipe.ViewOptions.vm.AncestorCardViewModel;
import com.eshed.fork.Util.GlideApp;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class AncestorViewHolder extends RecyclerView.ViewHolder {
    public interface AncestorCardCallback {
        void cardTappedOn(AncestorCardViewModel vm);
    }

    private TextView recipeName;
    private ImageView recipeImage;
    private TextView contributor;
    private ImageView circle;
    private ConstraintLayout layout;
    private AncestorCardViewModel vm;

    public AncestorViewHolder.AncestorCardCallback callback;

    public AncestorViewHolder(@NonNull View itemView) {
        super(itemView);

        recipeName = itemView.findViewById(R.id.recipe_name);
        recipeImage = itemView.findViewById(R.id.recipe_image);
        contributor = itemView.findViewById(R.id.contributor_name);
        circle = itemView.findViewById(R.id.circle_imageview);

        layout = itemView.findViewById(R.id.parent_layout);

        layout.setOnClickListener(v -> {
            if (callback != null && vm != null) {
                callback.cardTappedOn(vm);
            }
        });
    }

    public void setViewModel(AncestorCardViewModel vm) {
        this.vm = vm;
        recipeName.setText(vm.getRecipe().getName());
        contributor.setText(vm.getRecipe().getContributor());
        if (vm.hasEmphasis()) {
            Log.d("TAG", "setViewModel: " + vm.getRecipe().getName() + " has emphasis " + vm.hasEmphasis());
            circle.setImageResource(R.drawable.ic_circle_white);
        }
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference gsReference = storage.getReferenceFromUrl(vm.getRecipe().getImageURL());
        GlideApp.with(itemView).load(gsReference).centerCrop().into(recipeImage);
    }
}