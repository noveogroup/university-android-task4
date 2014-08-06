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
		// Inflate the layout for this fragment

		//View v = inflater.inflate(R.layout.fragment_lower_left, container, false);
		//View v = super.onCreateView(inflater, container, savedInstanceState);
		View v;
		if (getArguments().getBoolean("IS_DIALOG", false)) {
			v = super.onCreateView(inflater, container, savedInstanceState);
		} else {
			v = inflater.inflate(R.layout.fragment_lower_left, container, false);
			v.setOnClickListener((MainActivity) getActivity());
			v.setTag(TAG);
		}
		return v;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		return new AlertDialog.Builder(getActivity())
				.setTitle(R.string.alert_dialog_text)
				.setPositiveButton(R.string.alert_dialog_yes, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Toast.makeText(getActivity(), R.string.positive_answer, Toast.LENGTH_SHORT).show();
					}
				})
				.setNegativeButton(R.string.alert_dialog_no, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Toast.makeText(getActivity(), R.string.negative_answer, Toast.LENGTH_SHORT).show();
					}
				})
				.create();
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
