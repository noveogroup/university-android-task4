package com.noveogroup.task4;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.WebViewFragment;


public class LowerLeftFragment extends DialogFragment {

	public static final String TAG = "ShowDialog";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment

		View v = inflater.inflate(R.layout.fragment_lower_left, container, false);

		v.setOnClickListener((MainActivity) getActivity());
		v.setTag(TAG);
		return v;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog dialog = new AlertDialog.Builder(getActivity()).setIcon(R.drawable.ic_launcher).
				setPositiveButton(getString(R.string.alert_dialog_yes), new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						onAgree();
					}
				}).
				setNegativeButton(getString(R.string.alert_dialog_no), new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						onDecline();
					}
				}).create();
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		return dialog;
	}


	private void onAgree() {
		getFragmentManager().findFragmentByTag("UpperLeft").getView().setBackgroundColor(Color.GREEN);
	}

	private void onDecline() {
		getFragmentManager().findFragmentByTag("UpperLeft").getView().setBackgroundColor(Color.RED);

	}

	void showDialog() {
		DialogFragment newFragment = LowerLeftFragment.newInstance();
		newFragment.show(getFragmentManager(), "dialog");
	}

	private static DialogFragment newInstance() {
		return new LowerLeftFragment();
	}
}
