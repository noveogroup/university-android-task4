package com.noveogroup.task4;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

public class MainActivity extends FragmentActivity {

    private static final String TOP_LEFT_TAG = "top_left";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        if (savedInstanceState == null) {
            TopFragment topLeft = TopFragment.newInstance(getString(R.string.top_left_text), true);
            TopFragment topRight = TopFragment.newInstance(getString(R.string.top_right_text), false);
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction()
                    .add(R.id.top_left, topLeft, TOP_LEFT_TAG)
                    .add(R.id.top_right, topRight)
                    .commit();
        }
    }
}
