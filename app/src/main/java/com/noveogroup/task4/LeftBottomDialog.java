package com.noveogroup.task4;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class LeftBottomDialog extends DialogFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.left_bottom_dialog, container, false);
        getDialog().setTitle(R.string.dialog_title);

        getChildFragmentManager().beginTransaction()
                .replace(root.getId(), new LeftBottomFragment())
                .commit();

        return root;
    }

}
