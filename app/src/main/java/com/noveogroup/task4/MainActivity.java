package com.noveogroup.task4;


import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;

public class MainActivity extends Activity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		FragmentManager fragmentManager = getFragmentManager();
		if (savedInstanceState == null) {
			fragmentManager.executePendingTransactions();
			fragmentManager.beginTransaction()
					.add(R.id.upper_right, new UpperRightFragment())
					.add(R.id.lower_left, new LowerLeftFragment(), LowerLeftFragment.FRAGMENT_TAG)
					.add(R.id.lower_right, new LowerRightFragment()).commit();


		}
	}

}
