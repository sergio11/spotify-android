package com.example.sergio.spotify_angular.fragments;

import android.app.Fragment;
import android.os.Bundle;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by sergio on 04/06/2016.
 */
public abstract class EventBusFragment extends Fragment{
    protected EventBus bus = EventBus.getDefault();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bus.register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        bus.unregister(this);
    }

}
