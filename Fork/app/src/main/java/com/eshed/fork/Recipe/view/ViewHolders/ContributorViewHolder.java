package com.eshed.fork.Recipe.view.ViewHolders;

import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.eshed.fork.R;
import com.eshed.fork.Recipe.vm.component.ContributorViewModel;
import com.eshed.fork.Recipe.vm.component.RecipeComponentViewModel;

public class ContributorViewHolder extends RecipeViewHolder {
    EditText contributor;

    public ContributorViewHolder(@NonNull View itemView) {
        super(itemView);
        contributor = itemView.findViewById(R.id.contributor_name);
    }

    @Override
    public void bind(RecipeComponentViewModel vm) {
        ContributorViewModel contributorViewModel = (ContributorViewModel) vm;
        contributor.setText(contributorViewModel.contributor);
        contributor.setEnabled(contributorViewModel.isEditable());
    }
}