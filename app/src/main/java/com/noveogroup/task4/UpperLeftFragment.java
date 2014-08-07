package com.noveogroup.task4;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class UpperLeftFragment extends Fragment implements View.OnClickListener {

	public static final String SWITCHED_BOOL_KEY = "SWITCHED_BOOL";
	boolean switched = false;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		if (savedInstanceState != null) {
			switched = savedInstanceState.getBoolean(SWITCHED_BOOL_KEY);
		}
		View v = inflater.inflate(R.layout.fragment_upper_left, container, false);
		v.setOnClickListener(this);
		return v;

	}

	@Override
	public void onClick(View v) {
		if (!switched) {
			Fragment fragmentUp = getFragmentManager().findFragmentById(R.id.upper_right);
			Fragment fragmentDown = getFragmentManager().findFragmentById(R.id.lower_right);
			getFragmentManager().beginTransaction()
					.remove(fragmentUp)
					.remove(fragmentDown).commit();
			getFragmentManager().executePendingTransactions();
			getFragmentManager().beginTransaction()
					.replace(R.id.lower_right, fragmentUp)
					.replace(R.id.upper_right, fragmentDown)
					.commit();
			switched = true;
		}
	}


	@Override
	public void onSaveInstanceState(Bundle outState) {
		outState.putBoolean(SWITCHED_BOOL_KEY, switched);
		super.onSaveInstanceState(outState);
	}
}
