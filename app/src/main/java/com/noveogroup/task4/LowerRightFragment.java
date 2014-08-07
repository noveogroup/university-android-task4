package com.noveogroup.task4;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewFragment;


public class LowerRightFragment extends WebViewFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		WebView view = (WebView) super.onCreateView(inflater, container, savedInstanceState);
		view.loadUrl("file:///android_res/drawable/cat.jpg");
		return view;
	}


}
