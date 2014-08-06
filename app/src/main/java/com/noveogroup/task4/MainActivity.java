package com.noveogroup.task4;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.webkit.WebViewFragment;
import android.widget.FrameLayout;

public class MainActivity extends FragmentActivity {
    private WebViewFragment rightBottomFragment;
    private Fragment rightTopFragment;
    private boolean replacedFlag = false;
    private static final String KEY_REPLACED_FLAG = "com.noveogroup.task4.replaced.flag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FrameLayout leftTopContainer = (FrameLayout) findViewById(R.id.left_top_fragment);
        FrameLayout leftBottomContainer = (FrameLayout) findViewById(R.id.left_bottom_container);
        rightBottomFragment = new RightBottomFragment();
        rightTopFragment = new TopFragment();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.right_top_container, rightTopFragment)
                .add(R.id.left_bottom_container, new LeftBottomFragment())
                .commit();

        getFragmentManager().beginTransaction()
                .add(R.id.right_bottom_container, rightBottomFragment)
                .commit();

        leftTopContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!replacedFlag) {
                    replaceFragments();
                }
            }
        });

        leftBottomContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LeftBottomDialog dialog = new LeftBottomDialog();
                dialog.show(getSupportFragmentManager(), "dialog");
            }
        });
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if(savedInstanceState != null) {
            replacedFlag = savedInstanceState.getBoolean(KEY_REPLACED_FLAG);
        }
        if(replacedFlag) {
            replaceFragments();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(KEY_REPLACED_FLAG, replacedFlag);
    }

    private void replaceFragments() {
        getSupportFragmentManager().beginTransaction()
                .remove(rightTopFragment)
                .commit();
        getFragmentManager().beginTransaction()
                .remove(rightBottomFragment)
                .commit();

        getSupportFragmentManager().executePendingTransactions();
        getFragmentManager().executePendingTransactions();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.right_bottom_container, rightTopFragment)
                .commit();
        getFragmentManager().beginTransaction()
                .replace(R.id.right_top_container, rightBottomFragment)
                .commit();
        replacedFlag = true;
    }
}
