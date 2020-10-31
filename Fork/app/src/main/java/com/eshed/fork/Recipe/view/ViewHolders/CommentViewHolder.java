package com.eshed.fork.Recipe.view.ViewHolders;


import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.eshed.fork.R;
import com.eshed.fork.Recipe.vm.component.CommentViewModel;
import com.eshed.fork.Recipe.vm.component.RecipeComponentViewModel;

public class CommentViewHolder extends RecipeViewHolder {

    TextView name;
    EditText commentText;

    private CommentViewModel commentViewModel;

    public CommentViewHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.user_name);
        commentText = itemView.findViewById(R.id.user_comment);

        commentText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                commentViewModel.changeComment(commentText.getText().toString());
            }
        });
    }

    @Override
    public void bind(RecipeComponentViewModel vm) {
        commentViewModel = (CommentViewModel) vm;
        name.setText("" + commentViewModel.comment.getUsername());
        commentText.setText("" + commentViewModel.comment.getComment());
        commentText.setEnabled(commentViewModel.isEditable());
    }
}
