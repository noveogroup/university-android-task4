package com.noveogroup.task4;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class BottomLeftFragment extends DialogFragment {

    private static final int BLINK_DELAY = 1500;

    private TextView mView;
    private boolean  mBlink;
    private int      mBlinkColor;
    private Handler  mHandler;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mBlinkColor = getResources().getColor(R.color.dialog_color);
        mHandler = new Handler(getActivity().getMainLooper());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = new TextView(getActivity());
        mView.setText(R.string.dialog_text);
        mView.setGravity(Gravity.CENTER);
        mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!getShowsDialog()) {
                    FragmentManager manager = getFragmentManager();
                    manager.beginTransaction()
                            .remove(BottomLeftFragment.this)
                            .commit();
                    manager.executePendingTransactions();
                    show(manager, null);
                }
            }
        });
        mHandler.postDelayed(mBlinker, BLINK_DELAY);
        return mView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mView = null;
    }

    private Runnable mBlinker = new Runnable() {
        @Override
        public void run() {
            if (mView != null) {
                mView.setBackgroundColor(mBlink ? mBlinkColor : 0);
                mBlink = !mBlink;
                mHandler.postDelayed(this, BLINK_DELAY);
            }
        }
    };

}
