package com.eshed.fork.Recipe.view.Dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Spinner;

import androidx.appcompat.app.AlertDialog;

import com.eshed.fork.R;

import java.util.ArrayList;
import java.util.List;

public class RecipeOptionsDialogFragment extends DialogFragment {

    public interface RecipeOptionsDialogListener {
        void onDialogPositiveClick(DialogFragment dialog, String optionSelected);
        void onDialogNegativeClick(DialogFragment dialog);
    }

    RecipeOptionsDialogFragment.RecipeOptionsDialogListener listener;
    Activity activity;
    Spinner spinner;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        activity = getActivity();
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_recipe_options, null);

        // TODO: Fix this
        List<String> options = new ArrayList<>();
        options.add("Create modification");
        options.add("View modification history");
//
//        spinner = (Spinner)view.findViewById(R.id.spinner);
//        ArrayAdapter adapter = new ArrayAdapter<>(activity, android.R.layout.simple_spinner_item, options);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(adapter);


        builder.setMessage("Edit Recipe Name")
                .setView(view)
                .setPositiveButton("OK", (dialog, id) -> listener.onDialogPositiveClick(RecipeOptionsDialogFragment.this, spinner.getSelectedItem().toString()))
                .setNegativeButton("Cancel", (dialog, id) -> listener.onDialogNegativeClick(RecipeOptionsDialogFragment.this));
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (RecipeOptionsDialogFragment.RecipeOptionsDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }
    }
}
