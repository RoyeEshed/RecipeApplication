package com.eshed.fork.Recipe.view.Dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;

import com.eshed.fork.R;

public class NewRecipeDialogFragment extends DialogFragment {

    public interface NewRecipeDialogListener {
        void onDialogPositiveClick(DialogFragment dialog, String recipeName);
        void onDialogNegativeClick(DialogFragment dialog);
    }

    NewRecipeDialogListener listener;
    Activity activity;
    EditText recipeName;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        activity = getActivity();
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.new_recipe_dialog, null);

        recipeName = (EditText)view.findViewById(R.id.edit_recipe_name);

        builder.setMessage("Edit Recipe Name")
                .setView(view)
                .setPositiveButton("Save", (dialog, id) -> listener.onDialogPositiveClick(NewRecipeDialogFragment.this, recipeName.getText().toString()))
                .setNegativeButton("Cancel", (dialog, id) -> listener.onDialogNegativeClick(NewRecipeDialogFragment.this));
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (NewRecipeDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }
    }
}