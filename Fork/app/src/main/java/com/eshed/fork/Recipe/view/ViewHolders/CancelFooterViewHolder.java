package com.eshed.fork.Recipe.view.ViewHolders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.eshed.fork.R;
import com.eshed.fork.Recipe.view.FooterCallback;
import com.eshed.fork.Recipe.vm.component.Footer.CancelFooterViewModel;
import com.eshed.fork.Recipe.vm.component.RecipeComponentViewModel;

public class CancelFooterViewHolder extends RecipeViewHolder {
    public FooterCallback callback;
    TextView cancelButton;
    ImageView addButton;

    public CancelFooterViewHolder(@NonNull View itemView) {
        super(itemView);
        cancelButton = itemView.findViewById(R.id.cancel_button);
        addButton = itemView.findViewById(R.id.add_button);
    }

    @Override
    public void bind(RecipeComponentViewModel vm) {
        CancelFooterViewModel footerViewModel = (CancelFooterViewModel) vm;
        addButton.setVisibility(View.GONE);

        if (footerViewModel.isEditable()) {
            cancelButton.setVisibility(View.VISIBLE);
        } else {
            cancelButton.setVisibility(View.GONE);
        }

        cancelButton.setOnClickListener(v -> {
            if (callback != null) {
                callback.cancelButtonTapped();
            }
        });
    }
}
