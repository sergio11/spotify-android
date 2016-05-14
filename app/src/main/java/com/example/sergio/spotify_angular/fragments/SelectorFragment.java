package com.example.sergio.spotify_angular.fragments;

import android.app.Activity;
import android.app.Fragment;
import com.example.sergio.spotify_angular.adapters.RecyclerViewBaseAdapter;

import org.greenrobot.eventbus.EventBus;


/**
 * Created by sergio on 04/05/2016.
 */
public abstract class SelectorFragment<Model> extends Fragment {

    protected RecyclerViewBaseAdapter<Model> adapter;

    public SelectorFragment(RecyclerViewBaseAdapter<Model> adapter) {
        this.adapter = adapter;
    }

    protected abstract void loadData();



    /*It's not called because this method has been added in API 23. If you run your application on a device with API 23 (marshmallow) then onAttach(Context) will be called. On all previous Android Versions onAttach(Activity) will be called.*/
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onResume() {
        super.onResume();
        //load data
        this.loadData();
    }


}
