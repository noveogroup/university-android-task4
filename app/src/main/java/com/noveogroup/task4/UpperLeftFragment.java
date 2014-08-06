package com.noveogroup.task4;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class UpperLeftFragment extends Fragment {


	public static final String TAG = "Switch";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View v = inflater.inflate(R.layout.fragment_upper_left, container, false);
		v.setOnClickListener((MainActivity) getActivity());
		v.setTag(TAG);
		return v;

	}

}
