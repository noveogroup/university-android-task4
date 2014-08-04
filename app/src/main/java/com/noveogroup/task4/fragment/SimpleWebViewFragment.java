package com.noveogroup.task4.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebViewFragment;

public class SimpleWebViewFragment extends WebViewFragment {

    public final static String URL = "com.noveogroup.task4.URL";
    public final static String SCROLL_X = "com.noveogroup.task4.SCROLL_X";
    public final static String SCROLL_Y = "com.noveogroup.task4.SCROLL_Y";

    public static SimpleWebViewFragment newInstance(final String url) {
        SimpleWebViewFragment fragment = new SimpleWebViewFragment();
        Bundle bundle = new Bundle();
        bundle.putString(URL, url);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        Bundle bundle = getArguments();
        if(bundle != null) {
            getWebView().loadUrl(bundle.getString(URL));
        }
        if(savedInstanceState != null) {
            getWebView().scrollTo(savedInstanceState.getInt(SCROLL_X), savedInstanceState.getInt(SCROLL_Y));
        }

        return getWebView();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SCROLL_X, getWebView().getScrollX());
        outState.putInt(SCROLL_Y, getWebView().getScrollY());
    }
}
