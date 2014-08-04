package com.noveogroup.task4.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewFragment;

import com.noveogroup.task4.R;

public class SimpleWebViewFragment extends WebViewFragment {

    public final static String URL = "com.noveogroup.task4.URL";

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

        WebView view = (WebView) inflater.inflate(R.layout.fragment_webview, container, false);

        Bundle bundle = savedInstanceState != null ? savedInstanceState : getArguments();
        if(bundle != null) {
            view.loadUrl(bundle.getString(URL));
        }

        return view;
    }
}
