package com.example.yuri.myapplication;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


public class MainActivity extends Activity {

    private boolean isTopLeftClickable = true;
    private static final String IS_TOP_LEFT_CLICKABLE_KEY = "IS_TOP_LEFT_CLICKABLE_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            isTopLeftClickable = savedInstanceState.getBoolean(IS_TOP_LEFT_CLICKABLE_KEY);
        }

        setContentView(R.layout.activity_main);

        final TopFragment fragment1;
        final TopFragment fragment2;
        final CustomDialogFragment fragment3;
        final WebFragment fragment4;

        fragment1 = (TopFragment) getFragmentManager().findFragmentByTag(getResources().getString(R.string.top_fragment_tag));

        if (savedInstanceState == null) {
            fragment2 = new TopFragment();
            getFragmentManager().beginTransaction().replace(R.id.top_right_block, fragment2).commit();

            fragment3 = new CustomDialogFragment();
            getFragmentManager().beginTransaction().replace(R.id.bottom_left_block, fragment3).commit();

            fragment4 = new WebFragment();
            getFragmentManager().beginTransaction().replace(R.id.bottom_right_block, fragment4).commit();
        } else {
            fragment3 = (CustomDialogFragment) getFragmentManager().findFragmentById(R.id.bottom_left_block);

            if (isTopLeftClickable) {
                fragment2 = (TopFragment) getFragmentManager().findFragmentById(R.id.top_right_block);
                fragment4 = (WebFragment) getFragmentManager().findFragmentById(R.id.bottom_right_block);
            } else {
                fragment2 = (TopFragment) getFragmentManager().findFragmentById(R.id.bottom_right_block);
                fragment4 = (WebFragment) getFragmentManager().findFragmentById(R.id.top_right_block);
            }
        }

        findViewById(R.id.top_left_block).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isTopLeftClickable) {
                    isTopLeftClickable = false;
                    getFragmentManager().beginTransaction().replace(R.id.bottom_right_block, new TopFragment()).commit();
                    getFragmentManager().beginTransaction().replace(R.id.top_right_block, new WebFragment()).commit();
                }
            }
        });

        findViewById(R.id.bottom_left_block).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                if (fragment3 != null) {
                    ft.remove(fragment3);
                }

                fragment3.show(ft, "dialog");
            }
        });

        // Check that all fragments are correct
        Log.d("MyLog", fragment1.getId() + "");
        Log.d("MyLog", fragment2.getId() + "");
        Log.d("MyLog", fragment3.getId() + "");
        Log.d("MyLog", fragment4.getId() + "");
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putBoolean(IS_TOP_LEFT_CLICKABLE_KEY, isTopLeftClickable);
    }
}
