package com.noveogroup.task4;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewFragment;

public class RightBottomFragment extends WebViewFragment {
    private WebView mWebView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mWebView = getWebView();
        if(mWebView != null) {
            mWebView.loadUrl("http://developer.android.com/");
        }
        return mWebView;
    }
}
