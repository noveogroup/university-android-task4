package com.example.yuri.myapplication;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewFragment;


public class MainActivity extends Activity {

    private static final String TOP_FRAGMENT_1_TAG = "TOP_FRAGMENT_1_TAG";
    private static final String TOP_FRAGMENT_2_TAG = "TOP_FRAGMENT_2_TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TopFragment fragment1;
        TopFragment fragment2;

        if (savedInstanceState != null) {
            fragment1 = (TopFragment) getFragmentManager().findFragmentByTag(TOP_FRAGMENT_1_TAG);
            fragment2 = (TopFragment) getFragmentManager().findFragmentByTag(TOP_FRAGMENT_2_TAG);

            fragment1.setText("1");
            fragment2.setText("2");

        } else {
            fragment1 = new TopFragment();
            getFragmentManager().beginTransaction().add(R.id.top_left_block, fragment1, TOP_FRAGMENT_1_TAG).commit();

            fragment2 = new TopFragment();
            getFragmentManager().beginTransaction().add(R.id.top_right_block, fragment2, TOP_FRAGMENT_2_TAG).commit();
        }

        fragment1.setText("1");
        fragment2.setText("2");

        CustomDialogFragment fragment3 = new CustomDialogFragment();
        getFragmentManager().beginTransaction().add(R.id.bottom_left_block, fragment3).commit();

        WebFragment fragment4 = new WebFragment();
        getFragmentManager().beginTransaction().add(R.id.bottom_right_block, fragment4).commit();
    }
}
