package com.noveogroup.task4;


import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity implements View.OnClickListener {

	public static final String BOOL_KEY = "SWITCHED_BOOL";
	boolean switched = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

		FragmentManager fragmentManager = getFragmentManager();


		if (savedInstanceState == null) {
			fragmentManager.executePendingTransactions();
			fragmentManager.beginTransaction()
					.add(R.id.upper_left, new UpperLeftFragment(), "UpperLeft")
					.add(R.id.upper_right, new UpperRightFragment())
					.add(R.id.lower_left, new LowerLeftFragment())
					.add(R.id.lower_right, new LowerRightFragment()).commit();


		} else {
			switched = savedInstanceState.getBoolean(BOOL_KEY);
		}
	}

	@Override
	public void onClick(View v) {

		System.out.println(R.id.upper_left);
		if (!switched && v.getTag() == UpperLeftFragment.TAG) {
			Fragment fragmentUp = getFragmentManager().findFragmentById(R.id.upper_right);
			Fragment fragmentDown = getFragmentManager().findFragmentById(R.id.lower_right);
			getFragmentManager().beginTransaction()
					.remove(fragmentUp)
					.remove(fragmentDown).commit();
			getFragmentManager().executePendingTransactions();
			getFragmentManager().beginTransaction()
					.replace(R.id.lower_right, fragmentUp)
					.replace(R.id.upper_right, fragmentDown)
					.commit();
			switched = true;
			return;
		}
		if (v.getTag() == LowerLeftFragment.TAG) {
			LowerLeftFragment fragment = (LowerLeftFragment) getFragmentManager().findFragmentById(R.id.lower_left);
			fragment.showDialog();
		}
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		outState.putBoolean(BOOL_KEY, switched);
		super.onSaveInstanceState(outState);
	}
}
