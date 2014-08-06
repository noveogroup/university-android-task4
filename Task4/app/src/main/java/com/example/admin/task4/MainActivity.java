package com.example.admin.task4;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.admin.task4.fragments.BottomLeftFragment;
import com.example.admin.task4.fragments.BottomRightFragment;
import com.example.admin.task4.fragments.TopFragment;


public class MainActivity extends Activity {

    private boolean isClicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TopFragment topLeftFragment;
        final BottomLeftFragment bottomLeftFragment;
        final BottomRightFragment bottomRightFragment;

        topLeftFragment = new TopFragment();
        getFragmentManager().beginTransaction().replace(R.id.top_left_fragment, topLeftFragment, "TOP_LEFT_FRAGMENT").commit();
        bottomLeftFragment = new BottomLeftFragment();
        getFragmentManager().beginTransaction().replace(R.id.bottom_left_fragment, bottomLeftFragment, "BOTTOM_LEFT_FRAGMENT").commit();
        bottomRightFragment = new BottomRightFragment();
        getFragmentManager().beginTransaction().replace(R.id.bottom_right_fragment, bottomRightFragment, "BOTTOM_RIGHT_FRAGMENT").commit();

        findViewById(R.id.top_left_fragment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isClicked) {
                    isClicked = true;
                    //swap fragments or whatever
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
}
