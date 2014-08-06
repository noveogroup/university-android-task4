package com.noveogroup.task4;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TopFragment extends Fragment {

    private static final String KEY_ACTION_ENABLED = "action_enabled";
    private static final String KEY_TEXT = "text";

    private TextView mTextView;
    private String   mText;
    private boolean  mActionEnabled = true;

    public static TopFragment newInstance(String text, boolean actionEnabled) {
        Bundle args = new Bundle();
        args.putString(KEY_TEXT, text);
        args.putBoolean(KEY_ACTION_ENABLED, actionEnabled);
        TopFragment instance = new TopFragment();
        instance.setArguments(args);
        return instance;
    }

    public void setText(String text) {
        mText = text;
        if (mTextView != null) {
            mTextView.setText(text);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = (savedInstanceState == null) ? getArguments() : savedInstanceState;
        if (args != null) {
            mActionEnabled = args.getBoolean(KEY_ACTION_ENABLED);
            mText = args.getString(KEY_TEXT);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_TEXT, mText);
        outState.putBoolean(KEY_ACTION_ENABLED, mActionEnabled);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mTextView = new TextView(getActivity());
        mTextView.setGravity(Gravity.CENTER);
        mTextView.setText(mText);
        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mActionEnabled) {
                    getFragmentManager().beginTransaction()
                            .add(R.id.center_fragment, new CenterFragment())
                            .commit();
                    mActionEnabled = false;
                }
            }
        });
        return mTextView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mTextView = null;
    }
}
