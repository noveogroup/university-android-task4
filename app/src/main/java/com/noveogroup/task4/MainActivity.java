package com.noveogroup.task4;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

public class MainActivity extends Activity {

    private boolean clickFlag = false;
    private boolean replacedFlag = false;
    private static final String KEY_CLICK_FLAG = "com.noveogroup.task4.click.flag";
    private static final String KEY_REPLACED_FLAG = "com.noveogroup.task4.replaced.flag";
    private static final String TAG_RIGHT_BOTTOM = "com.noveogroup.task4.right.bottom";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FrameLayout leftTopContainer;
        FrameLayout rightBottomContainer;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        leftTopContainer = (FrameLayout) findViewById(R.id.left_top_fragment);
        rightBottomContainer = (FrameLayout) findViewById(R.id.right_bottom_container);

        getFragmentManager().beginTransaction()
                .add(R.id.right_top_container, new TopFragment(), "")
                .commit();

        getFragmentManager().beginTransaction()
                .add(R.id.left_bottom_container, new LeftBottomFragment())
                .commit();

        getFragmentManager().beginTransaction()
                .add(R.id.right_bottom_container, new RightBottomFragment(), TAG_RIGHT_BOTTOM)
                .commit();

        leftTopContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!clickFlag) {
                    replaceFragments();
                    clickFlag = true;
                }
            }
        });

        rightBottomContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if(savedInstanceState != null) {
            clickFlag = savedInstanceState.getBoolean(KEY_CLICK_FLAG);
            replacedFlag = savedInstanceState.getBoolean(KEY_REPLACED_FLAG);
        }
        if(replacedFlag) {
            replaceFragments();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(KEY_CLICK_FLAG, clickFlag);
        outState.putBoolean(KEY_REPLACED_FLAG, replacedFlag);
    }

    private void replaceFragments() {
        getFragmentManager().beginTransaction()
                .replace(R.id.left_bottom_container, new RightBottomFragment())
                .commit();
        getFragmentManager().beginTransaction()
                .replace(R.id.right_top_container, new LeftBottomFragment())
                .commit();
        getFragmentManager().beginTransaction()
                .replace(R.id.right_bottom_container, new TopFragment(), TAG_RIGHT_BOTTOM)
                .commit();
        replacedFlag = true;
    }
}
