package com.eshed.fork.Recipe.view.ViewHolders;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.eshed.fork.R;
import com.eshed.fork.Recipe.view.ContributorCallback;
import com.eshed.fork.Recipe.vm.component.ContributorViewModel;
import com.eshed.fork.Recipe.vm.component.RecipeComponentViewModel;

public class ContributorViewHolder extends RecipeViewHolder {
    public ContributorCallback callback;
    TextView contributorLabel;
    EditText contributor;
    ImageView starOutline;
    ImageView star;

    public ContributorViewHolder(@NonNull View itemView) {
        super(itemView);
        contributorLabel = itemView.findViewById(R.id.contributor_label);
        contributor = itemView.findViewById(R.id.contributor_name);
        starOutline = itemView.findViewById(R.id.no_star);
        star = itemView.findViewById(R.id.star);
    }

    @Override
    public void bind(RecipeComponentViewModel vm) {
        ContributorViewModel contributorViewModel = (ContributorViewModel) vm;
        contributor.setText(contributorViewModel.contributor);
        contributor.setEnabled(contributorViewModel.isEditable());

        if (!contributorViewModel.isEditable) {
            contributorLabel.setVisibility(View.VISIBLE);
            contributor.setVisibility(View.VISIBLE);
            if (contributorViewModel.isStarred) {
                starOutline.setVisibility(View.GONE);
                star.setVisibility(View.VISIBLE);
            } else {
                starOutline.setVisibility(View.VISIBLE);
                star.setVisibility(View.GONE);
            }

            starOutline.setOnClickListener(v -> {
                starOutline.setVisibility(View.GONE);
                star.setVisibility(View.VISIBLE);

                if (callback != null) {
                    callback.starTapped();
                }
            });

            star.setOnClickListener(view -> {
                starOutline.setVisibility(View.VISIBLE);
                star.setVisibility(View.GONE);

                if (callback != null) {
                    callback.starTapped();
                }
            });

        } else {
            Log.d("TAG", "bind: isStarrable: " + contributorViewModel.isEditable);
            contributorLabel.setVisibility(View.GONE);
            contributor.setVisibility(View.GONE);
            starOutline.setVisibility(View.GONE);
            star.setVisibility(View.GONE);
        }
    }
}