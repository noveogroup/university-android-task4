package com.noveogroup.task4;


import android.app.DialogFragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class LowerLeftFragment extends DialogFragment implements View.OnClickListener {


	public static final String FRAGMENT_TAG = "DialogFragment";
	public static final String IS_DIALOG_BOOL_KEY = "IS_DIALOG";
	boolean isDialog = false;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		if (savedInstanceState != null) {
			isDialog = savedInstanceState.getBoolean(IS_DIALOG_BOOL_KEY);
		}
		View v;
		v = inflater.inflate(R.layout.fragment_lower_left, container, false);
		v.setOnClickListener(this);
		return v;
	}

	@Override
	public void onClick(View v) {
		FragmentTransaction transaction = getActivity().getFragmentManager().beginTransaction();

		if (isDialog) {
			dismiss();
			getFragmentManager().executePendingTransactions();
			transaction.add(R.id.lower_left, this, LowerLeftFragment.FRAGMENT_TAG);
			transaction.commit();
		} else {
			transaction.remove(this);
			show(transaction, LowerLeftFragment.FRAGMENT_TAG);
		}
		isDialog = !isDialog;
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		outState.putBoolean(IS_DIALOG_BOOL_KEY, isDialog);
		super.onSaveInstanceState(outState);
	}
}
