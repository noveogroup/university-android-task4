package com.noveogroup.task4;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        if (savedInstanceState == null) {
            TopFragment topRight = TopFragment.newInstance(getString(R.string.top_right_text), false);
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction()
                    .add(R.id.top_right, topRight)
                    .add(R.id.bottom_left, new BottomLeftFragment())
                    .add(R.id.bottom_right, new BottomRightFragment())
                    .commit();

            TopFragment topLeft = (TopFragment)manager.findFragmentById(R.id.top_left);
            topLeft.setText(getString(R.string.top_left_text));
        }
    }
}
