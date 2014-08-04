package com.noveogroup.task4;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.noveogroup.task4.fragment.ColorFragment;

import com.noveogroup.task4.R;
import com.noveogroup.task4.fragment.SimpleDialogFragment;
import com.noveogroup.task4.fragment.SimpleWebViewFragment;
import com.noveogroup.task4.listener.OnFragmentClickListener;
import com.noveogroup.task4.util.FragmentHandler;


public class MainActivity extends Activity implements OnFragmentClickListener{

    public final static String MAGENTA_TAG = "com.noveogroup.task4.MAGENTA_TAG";
    public final static String YELLOW_TAG = "com.noveogroup.task4.YELLOW_TAG";
    public final static String DIALOG_TAG = "com.noveogroup.task4.DIALOG_TAG";
    public final static String WAS_SWAPPED = "com.noveogroup.task4.WAS_SWAPPED";

    private boolean wasSwapped = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            FragmentManager fragmentManager = getFragmentManager();

            FragmentHandler.putFragment(fragmentManager, R.id.fragment_left_top,
                                      ColorFragment.newInstance(Color.MAGENTA), MAGENTA_TAG, false);

            FragmentHandler.putFragment(fragmentManager, R.id.fragment_right_top,
                                        ColorFragment.newInstance(Color.YELLOW), YELLOW_TAG, false);

            FragmentHandler.putFragment(fragmentManager, R.id.fragment_left_bottom,
                    SimpleDialogFragment.newInstance(getResources()
                            .getString(R.string.final_dialog_text), true), DIALOG_TAG, false);

            FragmentHandler.putFragment(fragmentManager, R.id.fragment_right_bottom,
                    SimpleWebViewFragment.newInstance(getResources().getString(R.string.url)),
                    null, false);
        }
    }

    @Override
    public void onFragmentClick(String tag, Bundle state) {
        if(tag == DIALOG_TAG) {
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
        if(tag == MAGENTA_TAG) {
            if(!wasSwapped) {
                Log.d("COLOR", "Swap.");
                FragmentHandler.swapFragments(getFragmentManager(),
                                              R.id.fragment_left_top, MAGENTA_TAG,
                                              R.id.fragment_right_top, YELLOW_TAG, true);
//                FragmentManager fragmentManager = getFragmentManager();
//                FragmentHandler.removeFragment(fragmentManager,
//                        fragmentManager.findFragmentByTag(MAGENTA_TAG), true);
//                FragmentHandler.removeFragment(fragmentManager,
//                        fragmentManager.findFragmentByTag(YELLOW_TAG), false);
//
//                FragmentHandler.putFragment(fragmentManager, R.id.fragment_right_top,
//                        ColorFragment.newInstance(Color.MAGENTA), MAGENTA_TAG, false);
//                FragmentHandler.putFragment(fragmentManager, R.id.fragment_left_top,
//                        ColorFragment.newInstance(Color.YELLOW), YELLOW_TAG, false);

                wasSwapped = true;
            }

        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(WAS_SWAPPED, wasSwapped);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            wasSwapped = savedInstanceState.getBoolean(WAS_SWAPPED);
        }
    }
}
