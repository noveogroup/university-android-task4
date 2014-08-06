package com.noveogroup.task4.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewFragment;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        WebView webView = getWebView();

        Bundle bundle = getArguments();
        if(bundle != null) {
            webView.loadUrl(bundle.getString(URL));
        }

        return webView;
    }
}
