package com.fewlaps.android.donaelpas;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class ThanksFragmentDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View v = inflater.inflate(R.layout.dialog_thanks, null);
        v.findViewById(R.id.button).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), getString(R.string.newsletterSuscribed), Toast.LENGTH_SHORT).show();
                dismiss();
            }
        });

        builder.setTitle(getString(R.string.thanks1));
        builder.setView(v);
        return builder.create();
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
        getActivity().finish();
    }

    public static void open(FragmentActivity fragmentActivity) {
        DialogFragment newFragment = new ThanksFragmentDialog();
        newFragment.show(fragmentActivity.getSupportFragmentManager(), "THANKS_DIALOG_FRAGMENT");
    }
}