package com.eshed.fork.Recipe.view.ViewHolders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.eshed.fork.R;
import com.eshed.fork.Recipe.vm.component.HeaderViewModel;
import com.eshed.fork.Recipe.vm.component.RecipeComponentViewModel;

public class HeaderViewHolder extends RecipeViewHolder {
    TextView header;

    public HeaderViewHolder(@NonNull View itemView) {
        super(itemView);
        header = itemView.findViewById(R.id.section_header);
    }

    @Override
    public void bind(RecipeComponentViewModel vm) {
        HeaderViewModel headerViewModel = (HeaderViewModel)vm;
        header.setText(headerViewModel.header);
    }
}