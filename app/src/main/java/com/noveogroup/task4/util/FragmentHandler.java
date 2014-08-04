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

    public static void swapFragments(FragmentManager fragmentManager, int container1Id, String tag1,
                                     int container2Id, String tag2, boolean doAdd) {

        fragmentManager.executePendingTransactions();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Fragment fragment1 = fragmentManager.findFragmentByTag(tag1);
        Fragment fragment2 = fragmentManager.findFragmentByTag(tag2);
        transaction.remove(fragment1);
        transaction.remove(fragment2);
//        if(doAdd) {
//            transaction.addToBackStack(null);
//        }
        transaction.commit();
        fragmentManager.executePendingTransactions();
        transaction = fragmentManager.beginTransaction();
        transaction.add(container1Id, fragment2, tag2);
        transaction.add(container2Id, fragment1, tag1);
        transaction.commit();
    }
}
