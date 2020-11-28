package com.eshed.fork.Recipe.view.Dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.eshed.fork.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecipeOptionsDialogFragment extends DialogFragment {

    public interface RecipeOptionsDialogListener {
        void onViewModifiedVersionsTapped(DialogFragment dialog);
        void onViewRecipeHistoryTapped(DialogFragment dialog);
    }

    RecipeOptionsDialogFragment.RecipeOptionsDialogListener listener;
    Activity activity;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        activity = getActivity();
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_recipe_options, null);

        builder.setTitle("View Options")
                .setItems(R.array.options_array, (dialog, i) -> {
                    switch(i) {
                        case 0:
                            Toast.makeText(activity, "View modified versions", Toast.LENGTH_SHORT).show();
                            listener.onViewModifiedVersionsTapped(RecipeOptionsDialogFragment.this);
                            break;
                        case 1:
                            Toast.makeText(activity, "View recipe history", Toast.LENGTH_SHORT).show();
                            listener.onViewRecipeHistoryTapped(RecipeOptionsDialogFragment.this);
                            break;
                    }

                });

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (RecipeOptionsDialogFragment.RecipeOptionsDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString());
        }
    }
}
