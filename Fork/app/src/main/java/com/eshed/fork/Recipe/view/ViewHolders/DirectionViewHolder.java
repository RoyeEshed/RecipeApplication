package com.eshed.fork.Recipe.view.ViewHolders;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.eshed.fork.R;
import com.eshed.fork.Recipe.vm.component.DirectionViewModel;
import com.eshed.fork.Recipe.vm.component.RecipeComponentViewModel;

public class DirectionViewHolder extends RecipeViewHolder {
    TextView directionNumber;
    EditText directionText;

    public DirectionViewHolder(@NonNull View itemView) {
        super(itemView);
        directionNumber = itemView.findViewById(R.id.direction_number);
        directionText = itemView.findViewById(R.id.direction_text);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void bind(RecipeComponentViewModel vm) {
        DirectionViewModel directionViewModel = (DirectionViewModel)vm;
        directionNumber.setText("" + directionViewModel.direction.getDirectionNumber());
        directionText.setText("" + directionViewModel.direction.getDirectionText());
        directionText.setEnabled(directionViewModel.isEditable());
    }
}
