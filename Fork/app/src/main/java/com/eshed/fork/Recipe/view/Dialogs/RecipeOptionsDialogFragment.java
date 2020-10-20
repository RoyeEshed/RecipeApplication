package com.eshed.fork.Recipe.view.Dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;

import com.eshed.fork.R;

public class RecipeOptionsDialogFragment {
    public interface AddDialogListener {
        void onDialogPositiveClick(DialogFragment dialog, String itemName, String itemQuantity, String itemType);
        void onDialogNegativeClick(DialogFragment dialog);
    }

    AddDialogListener listener;
    Activity activity;
    TextView createModification;
    TextView viewModifications;

//
//    @Override
//    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        activity = getActivity();
//        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
//        LayoutInflater inflater = activity.getLayoutInflater();
//        View view = inflater.inflate(R.layout.recipe_options_dialog, null);
//
//        createModification = view.findViewById(R.id.create_modification);
//        viewModifications = view.findViewById(R.id.view_modifications);
//
//        return builder.create();
//    }
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        try {
//            listener = (AddDialogListener) context;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(activity.toString()
//                    + " must implement NoticeDialogListener");
//        }
//    }
}
