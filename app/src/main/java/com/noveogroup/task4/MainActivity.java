package com.noveogroup.task4;

import android.app.Activity;
import android.app.FragmentManager;
import android.graphics.Color;
import android.os.Bundle;

import com.noveogroup.task4.fragment.ColorFragment;
import com.noveogroup.task4.fragment.SimpleDialogFragment;
import com.noveogroup.task4.fragment.SimpleWebViewFragment;
import com.noveogroup.task4.listener.OnFragmentClickListener;
import com.noveogroup.task4.util.FragmentHandler;


public class MainActivity extends Activity implements OnFragmentClickListener{

    public final static String MAGENTA_TAG = "com.noveogroup.task4.MAGENTA_TAG";
    public final static String DIALOG_TAG = "com.noveogroup.task4.DIALOG_TAG";
    public final static String WEB_TAG = "com.noveogroup.task4.WEB_TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            FragmentManager fragmentManager = getFragmentManager();

            FragmentHandler.putFragment(fragmentManager, R.id.fragment_left_top,
                    ColorFragment.newInstance(Color.MAGENTA), MAGENTA_TAG, false);

            FragmentHandler.putFragment(fragmentManager, R.id.fragment_left_bottom,
                    SimpleDialogFragment.newInstance(getResources()
                            .getString(R.string.final_dialog_text), true), DIALOG_TAG, false);

            FragmentHandler.putFragment(fragmentManager, R.id.fragment_right_bottom,
                    SimpleWebViewFragment.newInstance(getResources().getString(R.string.url)),
                    WEB_TAG, false);
        }
    }

    @Override
    public void onFragmentClick(String tag, Bundle state) {
        if(tag == null) {
            return;
        }
        if(tag.equals(DIALOG_TAG)) {
            FragmentManager fragmentManager = getFragmentManager();
            if(state.getBoolean(SimpleDialogFragment.IS_EMBEDDED)) {
                FragmentHandler.removeFragment(fragmentManager,
                        fragmentManager.findFragmentByTag(DIALOG_TAG), false);
                SimpleDialogFragment dialogFragment = SimpleDialogFragment
                        .newInstance(state.getString(SimpleDialogFragment.DIALOG_TEXT), false);
                dialogFragment.setCancelable(false);
                dialogFragment.show(fragmentManager, DIALOG_TAG);
            }
            else {
                ((SimpleDialogFragment) fragmentManager.findFragmentByTag(DIALOG_TAG)).dismiss();
                SimpleDialogFragment dialogFragment = SimpleDialogFragment
                        .newInstance(state.getString(SimpleDialogFragment.DIALOG_TEXT), true);
                FragmentHandler.putFragment(fragmentManager, R.id.fragment_left_bottom,
                        dialogFragment, DIALOG_TAG, false);

            }
        }
        if(tag.equals(MAGENTA_TAG)) {
            FragmentManager fragmentManager = getFragmentManager();
            if(fragmentManager.findFragmentByTag(MAGENTA_TAG)
                    == fragmentManager.findFragmentById(R.id.fragment_left_top)) {
                FragmentHandler.putTwoFragments(fragmentManager,
                        R.id.fragment_right_bottom, MAGENTA_TAG,
                        ColorFragment.newInstance(Color.MAGENTA),
                        R.id.fragment_left_top, WEB_TAG,
                        SimpleWebViewFragment.newInstance(getResources().getString(R.string.url)),
                        true);
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        ((SimpleWebViewFragment) getFragmentManager().findFragmentByTag(WEB_TAG))
                .getWebView().saveState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        ((SimpleWebViewFragment) getFragmentManager().findFragmentByTag(WEB_TAG))
                .getWebView().restoreState(savedInstanceState);
    }
}
