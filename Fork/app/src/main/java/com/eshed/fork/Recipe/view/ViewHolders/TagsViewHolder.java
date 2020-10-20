package com.eshed.fork.Recipe.view.ViewHolders;

import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.eshed.fork.R;
import com.eshed.fork.Recipe.vm.component.RecipeComponentViewModel;
import com.eshed.fork.Recipe.vm.component.TagViewModel;

public class TagsViewHolder extends RecipeViewHolder {
    EditText tags;

    public TagsViewHolder(@NonNull View itemView) {
        super(itemView);
        tags = itemView.findViewById(R.id.tags_text);
    }

    @Override
    public void bind(RecipeComponentViewModel vm) {
        TagViewModel tagViewModel = (TagViewModel)vm;
        tags.setText(tagViewModel.tag);

        tags.setEnabled(tagViewModel.isEditable());
    }
}