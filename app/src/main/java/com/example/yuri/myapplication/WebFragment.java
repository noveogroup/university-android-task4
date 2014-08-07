package com.example.yuri.myapplication;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebViewFragment;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class WebFragment extends WebViewFragment {

    private WebView webView;
    private String url = "http://trifons.com/assets/Images/pizza(content).jpg";

    public WebFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        webView = getWebView();
        webView.loadUrl(url);
        webView.setInitialScale(100);
        if(webView != null) {
            webView.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    view.loadUrl(url);
                    return true;
                }
            });
        }

        return webView;
    }


}
