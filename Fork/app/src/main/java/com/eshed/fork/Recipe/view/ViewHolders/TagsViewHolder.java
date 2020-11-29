package com.eshed.fork.Recipe.view.ViewHolders;

import android.graphics.PorterDuff;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.eshed.fork.R;
import com.eshed.fork.Recipe.vm.component.RecipeComponentViewModel;
import com.eshed.fork.Recipe.vm.component.TagViewModel;

public class TagsViewHolder extends RecipeViewHolder {
    private EditText tagsText;
    private TagViewModel tagViewModel;

    public TagsViewHolder(@NonNull View itemView) {
        super(itemView);
        tagsText = itemView.findViewById(R.id.tags_text);

        tagsText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                tagViewModel.changeTags(tagsText.getText().toString());
            }
        });
    }

    @Override
    public void bind(RecipeComponentViewModel vm) {
        tagViewModel = (TagViewModel) vm;

        String temp = "";
        int size =  tagViewModel.tags.size();
        for (int i = 0; i < size - 1; i++) {
            temp += tagViewModel.tags.get(i);
            temp += ", ";
        }
        if (!tagViewModel.tags.isEmpty()) {
            temp += tagViewModel.tags.get(size - 1);
        }

        if (!tagViewModel.isEditable()) {
            tagsText.setBackgroundTintMode(PorterDuff.Mode.CLEAR);
        } else {
            tagsText.setBackgroundTintMode(PorterDuff.Mode.MULTIPLY);
        }

        tagsText.setText(temp);
        tagsText.setEnabled(tagViewModel.isEditable());
    }
}