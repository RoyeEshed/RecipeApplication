package com.eshed.fork.Recipe.view.ViewHolders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.eshed.fork.R;
import com.eshed.fork.Recipe.view.FooterCallback;
import com.eshed.fork.Recipe.vm.component.Footer.DirectionFooterViewModel;
import com.eshed.fork.Recipe.vm.component.RecipeComponentViewModel;

public class DirectionFooterViewHolder extends RecipeViewHolder {
    public FooterCallback callback;
    ImageView addButton;
    TextView addHintTextView;

    public DirectionFooterViewHolder(@NonNull View itemView) {
        super(itemView);
        addButton = itemView.findViewById(R.id.add_button);
        addHintTextView = itemView.findViewById(R.id.add_hint);
    }

    @Override
    public void bind(RecipeComponentViewModel vm) {
        DirectionFooterViewModel footerViewModel = (DirectionFooterViewModel) vm;
        addButton.setImageResource(footerViewModel.imageResource);
        if (footerViewModel.isEditable()) {
            addButton.setVisibility(View.VISIBLE);
            addHintTextView.setVisibility(View.VISIBLE);
            addHintTextView.setText("Add Directions");
        } else {

            addButton.setVisibility(View.GONE);
            addHintTextView.setVisibility(View.GONE);
        }
        addButton.setOnClickListener(v -> {
            if (callback != null) {
                callback.addButtonTapped(vm.getType());
            }
        });
    }
}
