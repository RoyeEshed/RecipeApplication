package com.eshed.fork.Recipe.view.ViewHolders;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.eshed.fork.R;
import com.eshed.fork.Recipe.view.FooterCallback;
import com.eshed.fork.Recipe.vm.component.Footer.CommentFooterViewModel;
import com.eshed.fork.Recipe.vm.component.RecipeComponentViewModel;

public class CommentFooterViewHolder extends RecipeViewHolder {
    public FooterCallback callback;
    ImageView addButton;

    public CommentFooterViewHolder(@NonNull View itemview ) {
        super(itemview);
        addButton = itemView.findViewById(R.id.add_button);
    }

    @Override
    public void bind(RecipeComponentViewModel vm) {
        CommentFooterViewModel footerViewModel = (CommentFooterViewModel) vm;
        addButton.setImageResource(footerViewModel.getImageResource());
        if (footerViewModel.isEditable()) {
            addButton.setVisibility(View.VISIBLE);
        } else {
            addButton.setVisibility(View.GONE);
        }
        addButton.setOnClickListener(v -> {
            if (callback != null) {
                callback.addButtonTapped(vm.getType());
            }
        });
    }
}
