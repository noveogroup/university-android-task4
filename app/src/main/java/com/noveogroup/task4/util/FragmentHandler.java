package com.noveogroup.task4.util;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

public class FragmentHandler {

    private FragmentHandler() {
        throw new UnsupportedOperationException("FragmentHandler instance can not be created.");
    }

    public static void putFragment(FragmentManager fragmentManager, int containerId,
                                   Fragment fragment, String tag, boolean doAdd) {
        fragmentManager.executePendingTransactions();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(containerId, fragment, tag);
        if(doAdd) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }

    public static void removeFragment(FragmentManager fragmentManager, Fragment fragment,
                                      boolean doAdd) {
        fragmentManager.executePendingTransactions();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.remove(fragment);
        if(doAdd) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }

    public static void putTwoFragments(FragmentManager fragmentManager,
                                     int container1Id, String tag1, Fragment fragment1,
                                     int container2Id, String tag2, Fragment fragment2,
                                     boolean doAdd) {

        fragmentManager.executePendingTransactions();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(container1Id, fragment1, tag1);
        transaction.replace(container2Id, fragment2, tag2);
        if(doAdd) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }
}
