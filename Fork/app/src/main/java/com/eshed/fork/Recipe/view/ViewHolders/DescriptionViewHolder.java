package com.eshed.fork.Recipe.view.ViewHolders;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.eshed.fork.R;
import com.eshed.fork.Recipe.vm.component.DescriptionViewModel;
import com.eshed.fork.Recipe.vm.component.RecipeComponentViewModel;

public class DescriptionViewHolder extends RecipeViewHolder {
    private EditText description;
    private DescriptionViewModel descriptionViewModel;

    public DescriptionViewHolder(@NonNull View itemView) {
        super(itemView);
        description = itemView.findViewById(R.id.description);

        description.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                descriptionViewModel.changeDescription(description.getText().toString());
            }
        });
    }

    @Override
    public void bind(RecipeComponentViewModel vm) {
        descriptionViewModel = (DescriptionViewModel)vm;
        description.setEnabled(descriptionViewModel.isEditable());

        if (descriptionViewModel.isEditable()) {
            description.setVisibility(View.VISIBLE);
        } else {
            description.setVisibility(View.GONE);
        }
    }
}