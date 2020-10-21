package com.eshed.fork.Recipe.view.ViewHolders;

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
    private EditText tags;
    private TagViewModel tagViewModel;

    public TagsViewHolder(@NonNull View itemView) {
        super(itemView);
        tags = itemView.findViewById(R.id.tags_text);

        tags.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                tagViewModel.changeTags(tags.getText().toString());
            }
        });
    }

    @Override
    public void bind(RecipeComponentViewModel vm) {
        tagViewModel = (TagViewModel) vm;
        tags.setText(tagViewModel.tag);
        tags.setEnabled(tagViewModel.isEditable());
    }
}