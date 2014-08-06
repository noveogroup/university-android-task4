package com.noveogroup.task4;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebViewFragment;
import android.widget.FrameLayout;

public class MainActivity extends Activity {
    private WebViewFragment rightBottomFragment;
    private Fragment rightTopFragment;
    private LeftBottomFragment leftBottomFragment;
    private boolean replacedFlag = false;
    private boolean showDialog = false;
    private static final String KEY_REPLACED_FLAG = "com.noveogroup.task4.replaced.flag";
    private static final String KEY_SHOW_DIALOG = "com.noveogroup.task4.show.dialog";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FrameLayout leftTopContainer = (FrameLayout) findViewById(R.id.left_top_fragment);
        FrameLayout leftBottomContainer = (FrameLayout) findViewById(R.id.left_bottom_container);
        rightBottomFragment = new RightBottomFragment();
        rightTopFragment = new TopFragment();
        leftBottomFragment = new LeftBottomFragment();

        getFragmentManager().beginTransaction()
                .replace(R.id.right_top_container, rightTopFragment)
                .replace(R.id.left_bottom_container, leftBottomFragment)
                .replace(R.id.right_bottom_container, rightBottomFragment)
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
                getFragmentManager().beginTransaction()
                        .remove(leftBottomFragment)
                        .commit();
                getFragmentManager().executePendingTransactions();
                leftBottomFragment.show(getFragmentManager(), "dialog");
                showDialog = true;
            }
        });
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if(savedInstanceState != null) {
            replacedFlag = savedInstanceState.getBoolean(KEY_REPLACED_FLAG);
            showDialog = savedInstanceState.getBoolean(KEY_SHOW_DIALOG);
        }
        if(replacedFlag) {
            replaceFragments();
        }
        if(showDialog) {
            getFragmentManager().beginTransaction()
                    .remove(leftBottomFragment)
                    .commit();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(KEY_REPLACED_FLAG, replacedFlag);
        outState.putBoolean(KEY_SHOW_DIALOG, showDialog);
    }

    private void replaceFragments() {
        getFragmentManager().beginTransaction()
                .remove(rightTopFragment)
                .remove(rightBottomFragment)
                .commit();
        getFragmentManager().executePendingTransactions();
        getFragmentManager().beginTransaction()
                .replace(R.id.right_bottom_container, rightTopFragment)
                .replace(R.id.right_top_container, rightBottomFragment)
                .commit();
        replacedFlag = true;
    }

    public void dismissLeftBottomDialog() {
        leftBottomFragment.dismiss();
        getFragmentManager().executePendingTransactions();
        getFragmentManager().beginTransaction()
                .replace(R.id.left_bottom_container, leftBottomFragment)
                .commit();
        showDialog = false;
    }
}
