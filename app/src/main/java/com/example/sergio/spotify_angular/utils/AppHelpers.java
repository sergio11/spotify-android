package com.example.sergio.spotify_angular.utils;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;


/**
 * Created by sergio on 07/05/2016.
 */
public class AppHelpers {

    private AppHelpers(){}

    public static void setFragment(Activity activity, Fragment frag, int target, boolean replace, boolean addToBackStack )
    {
        FragmentManager fm = activity.getFragmentManager();
        if (fm.findFragmentById(target) == null) {
            FragmentTransaction transaction = fm.beginTransaction();
            if(replace){
                transaction.replace(target, frag);
            }else{
                transaction.add(target, frag);
            }
            if(addToBackStack) transaction.addToBackStack(null);
            transaction.commit();
        }

    }
}
