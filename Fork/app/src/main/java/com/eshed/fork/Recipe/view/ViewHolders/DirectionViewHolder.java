package com.eshed.fork.Recipe.view.ViewHolders;

import android.annotation.SuppressLint;
import android.graphics.PorterDuff;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.eshed.fork.R;
import com.eshed.fork.Recipe.vm.component.DirectionViewModel;
import com.eshed.fork.Recipe.vm.component.RecipeComponentViewModel;

public class DirectionViewHolder extends RecipeViewHolder {
    TextView directionNumber;
    EditText directionText;

    private DirectionViewModel directionViewModel;

    public DirectionViewHolder(@NonNull View itemView) {
        super(itemView);
        directionNumber = itemView.findViewById(R.id.direction_number);
        directionText = itemView.findViewById(R.id.direction_text);

        directionText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                directionViewModel.changeDirectionText(directionText.getText().toString());
            }
        });
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void bind(RecipeComponentViewModel vm) {
        directionViewModel = (DirectionViewModel) vm;
        directionNumber.setText("" + directionViewModel.direction.getDirectionNumber());
        directionText.setText("" + directionViewModel.direction.getDirectionText());
        if (!directionViewModel.isEditable()) {
            directionText.setBackgroundTintMode(PorterDuff.Mode.CLEAR);
        } else {
            directionText.setBackgroundTintMode(PorterDuff.Mode.MULTIPLY);
        }
        directionText.setEnabled(directionViewModel.isEditable());
    }
}
