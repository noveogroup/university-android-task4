package com.example.admin.task4;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.admin.task4.fragments.BottomLeftFragment;
import com.example.admin.task4.fragments.BottomRightFragment;
import com.example.admin.task4.fragments.TopFragment;


public class MainActivity extends Activity {

    TopFragment topLeftFragment;
    BottomLeftFragment bottomLeftFragment;
    BottomRightFragment bottomRightFragment;

    private boolean isClicked = false;
    private static final String KEY_IS_CLICKED = "IS_CLICKED";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            isClicked = savedInstanceState.getBoolean(KEY_IS_CLICKED);
        }

        topLeftFragment = new TopFragment();
        getFragmentManager().beginTransaction().replace(R.id.top_left_fragment, topLeftFragment).commit();
        bottomLeftFragment = new BottomLeftFragment();
        getFragmentManager().beginTransaction().replace(R.id.bottom_left_fragment, bottomLeftFragment).commit();
        bottomRightFragment = new BottomRightFragment();
        getFragmentManager().beginTransaction().replace(R.id.bottom_right_fragment, bottomRightFragment).commit();

        if (isClicked) {
            swapFragments();
        }

        findViewById(R.id.top_left_fragment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isClicked) {
                    isClicked = true;
                    swapFragments();
                }
            }
        });

        findViewById(R.id.bottom_left_fragment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomLeftFragment dialog = new BottomLeftFragment();
                dialog.show(getFragmentManager(), "DIALOG");
            }
        });
    }

    private void swapFragments() {
        bottomLeftFragment = new BottomLeftFragment();
        bottomRightFragment = new BottomRightFragment();
        getFragmentManager().beginTransaction().replace(R.id.bottom_right_fragment, bottomLeftFragment).commit();
        getFragmentManager().beginTransaction().replace(R.id.bottom_left_fragment, bottomRightFragment).commit();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(KEY_IS_CLICKED, isClicked);
    }
}
