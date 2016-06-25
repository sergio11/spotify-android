package com.example.sergio.spotify_angular.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.sergio.spotify_angular.R;
import com.example.sergio.spotify_angular.models.MenuAppItem;
import com.example.sergio.spotify_angular.utils.AppHelpers;

import java.util.List;

/**
 * Created by sergio on 14/05/2016.
 */
public class ExplorerFragment extends Fragment{

    public final static String ID = "EXPLORER_FRAGMENT";

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_explorer, container, false);
        MenuFragment menuFragment = (MenuFragment) getChildFragmentManager().findFragmentById(R.id.menu_explorer_fragment);
        menuFragment.setMenu(AppHelpers.getMenuFromResources(getActivity(), R.array.explorer_menu_items ));
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Load FeaturedPlayList Fragment
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.explorer_menu, menu);
        super.onCreateOptionsMenu(menu,inflater);
    }


}
