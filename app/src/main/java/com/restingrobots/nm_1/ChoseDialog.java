package com.restingrobots.nm_1;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

/**
 * Created by Andriy on 15.02.2016.
 */
public class ChoseDialog extends DialogFragment implements OnClickListener {

    // Use this instance of the interface to deliver action events
    ChoseDialogListener mListener;

    @Override
    public void onClick(DialogInterface dialog, int which) {
        mListener.onChose(which);
    }

    public interface ChoseDialogListener {
        public void onChose(int id);
    }
    public ChoseDialog() {
        // Empty constructor required for DialogFragment
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.dialog_title)
                .setItems(R.array.types, this);
        return builder.create();
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (ChoseDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }
    }

}