package com.example.sergio.spotify_angular.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sergio.spotify_angular.R;
import com.example.sergio.spotify_angular.utils.AppHelpers;

/**
 * Created by sergio on 25/06/2016.
 */
public class YourLibraryFragment extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_your_library, container, false);
        MenuFragment menuFragment = (MenuFragment) getFragmentManager().findFragmentById(R.id.menu_your_library);
        menuFragment.setMenu(AppHelpers.getMenuFromResources(getActivity(),R.array.your_library_menu));
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        MenuFragment m = (MenuFragment)getFragmentManager().findFragmentById(R.id.menu_your_library);
        if (m != null)
            getFragmentManager().beginTransaction().remove(m).commit();
    }


}
