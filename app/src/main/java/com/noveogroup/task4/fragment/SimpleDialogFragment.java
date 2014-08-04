package com.noveogroup.task4.fragment;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.noveogroup.task4.R;
import com.noveogroup.task4.listener.OnFragmentClickListener;
import com.noveogroup.task4.util.FragmentHandler;

public class SimpleDialogFragment extends DialogFragment {

    public final static String DIALOG_TEXT = "com.noveogroup.task4.DIALOG_TEXT";
    public final static String IS_EMBEDDED = "com.noveogroup.task4.IS_EMBEDDED";

    private String dialogText;
    private boolean isEmbedded;
    private OnFragmentClickListener onFragmentClickListener;

    public static SimpleDialogFragment newInstance(String dialogText, boolean isEmbedded) {
        SimpleDialogFragment fragment = new SimpleDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString(DIALOG_TEXT, dialogText);
        bundle.putBoolean(IS_EMBEDDED, isEmbedded);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Bundle bundle = savedInstanceState != null ? savedInstanceState : getArguments();
        if(bundle != null) {
            dialogText = bundle.getString(DIALOG_TEXT);
            isEmbedded = bundle.getBoolean(IS_EMBEDDED);
        }
        else {
            dialogText = getResources().getString(R.string.no_message_text);
            isEmbedded = true;
        }

        if(!isEmbedded) {
            return super.onCreateView(inflater, container, savedInstanceState);
        }

        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_dialog, container, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onFragmentClickListener != null) {
                    Bundle bundle = new Bundle();
                    bundle.putString(DIALOG_TEXT, dialogText);
                    bundle.putBoolean(IS_EMBEDDED, isEmbedded);
                    onFragmentClickListener.onFragmentClick(getTag(), bundle);
                }
            }
        });

        return view;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);

        Bundle bundle = savedInstanceState != null ? savedInstanceState : getArguments();
        dialogText = bundle != null ? bundle.getString(DIALOG_TEXT)
                                    : getResources().getString(R.string.no_message_text);
        isEmbedded = false;

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(dialogText);
        builder.setPositiveButton(getResources().getString(R.string.dialog_button_text),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(onFragmentClickListener != null) {
                            Bundle bundle = new Bundle();
                            bundle.putString(DIALOG_TEXT, dialogText);
                            bundle.putBoolean(IS_EMBEDDED, isEmbedded);
                            onFragmentClickListener.onFragmentClick(getTag(), bundle);
                        }
                    }
                });

        AlertDialog dialog = builder.create();

        return dialog;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(DIALOG_TEXT, dialogText);
        outState.putBoolean(IS_EMBEDDED, isEmbedded);
    }

    public void setOnFragmentClickListener(OnFragmentClickListener onFragmentClickListener) {
        this.onFragmentClickListener = onFragmentClickListener;
    }
}
