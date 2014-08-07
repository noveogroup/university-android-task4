package com.noveogroup.task4;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


public class LowerLeftFragment extends DialogFragment {

	public static final String TAG = "ShowDialog";
	public static final String IS_DIALOG_ARG = "IS_DIALOG";


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View v;
		v = inflater.inflate(R.layout.fragment_lower_left, container, false);
		v.setOnClickListener((MainActivity) getActivity());
		v.setTag(TAG);
		return v;
	}

	void showDialog() {
		DialogFragment newFragment = LowerLeftFragment.newInstance(true);
		newFragment.show(getActivity().getFragmentManager(), "dialog");
	}

	public static DialogFragment newInstance() {
		return newInstance(false);
	}

	public static DialogFragment newInstance(boolean dialog) {

		DialogFragment fragment = new LowerLeftFragment();
		Bundle args = new Bundle();
		args.putBoolean(IS_DIALOG_ARG, dialog);
		fragment.setArguments(args);
		return fragment;
	}
}
