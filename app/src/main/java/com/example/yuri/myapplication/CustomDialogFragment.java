package com.example.yuri.myapplication;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class CustomDialogFragment extends DialogFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_custom_dialig, container, false);

        return view;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);

        FragmentManager fm = getFragmentManager();

        if (fm != null) {
            fm.executePendingTransactions();
            fm.beginTransaction().add(R.id.bottom_left_block, this).commit();
        }
    }
}
