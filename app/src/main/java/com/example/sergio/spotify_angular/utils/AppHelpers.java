package com.example.sergio.spotify_angular.utils;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;

import com.example.sergio.spotify_angular.models.MenuAppItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Created by sergio on 07/05/2016.
 */
public class AppHelpers {

    private AppHelpers(){}

    public static void setFragment(Activity activity, Fragment frag, int target, boolean replace, boolean addToBackStack )
    {
        FragmentManager fm = activity.getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        if(replace){
            transaction.replace(target,frag,"FRAGMENT");
        }else{
            transaction.add(target, frag, "FRAGMENT");
        }
        if(addToBackStack) transaction.addToBackStack(null);
        transaction.commit();

    }

    public static void showDialog(Activity activity,DialogFragment dialogFragment){
        FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
        Fragment prev = activity.getFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);

        // Create and show the dialog.
        dialogFragment.show(ft, "dialog");

    }

    @SuppressWarnings("ResourceType")
    public static List<MenuAppItem> getMenuFromResources(Context context, int id){

        int ID_POS = 0, ICON_POS = 1, TEXT_POS = 2;

        TypedArray menuResources = context.getResources().obtainTypedArray(id);
        List<MenuAppItem> menu = new ArrayList<>();

        for (int i = 0; i < menuResources.length(); i++) {
            int resId = menuResources.getResourceId(i, -1);
            if (resId > 0) {
                TypedArray itemDef = context.getResources().obtainTypedArray(resId);
                int ide = itemDef.getResourceId(ID_POS,0);
                String icon = itemDef.getString(ICON_POS);
                String name = itemDef.getString(TEXT_POS);
                menu.add(new MenuAppItem(ide,icon, name));
            }
        }

        return menu;
    }

    public static void showViewById(ViewGroup view ,int id){
        for (int i = 0, len = view.getChildCount(); i < len; i++){
            View child = view.getChildAt(i);
            if (child.getId() == id){
                child.setVisibility(View.VISIBLE);
            }else{
                child.setVisibility(View.GONE);
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    public static int dpToPx(Resources r, int dp) {
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    // This is fancier than Map.putAll(Map)
    public static Map deepMerge(Map original, Map newMap) {
        for (Object key : newMap.keySet()) {
            if (newMap.get(key) instanceof Map && original.get(key) instanceof Map) {
                Map originalChild = (Map) original.get(key);
                Map newChild = (Map) newMap.get(key);
                original.put(key, deepMerge(originalChild, newChild));
            } else {
                original.put(key, newMap.get(key));
            }
        }
        return original;
    }


}
