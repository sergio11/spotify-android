package com.example.sergio.spotify_angular.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sergio.spotify_angular.R;
import com.example.sergio.spotify_angular.adapters.MenuAdapter;
import com.example.sergio.spotify_angular.models.MenuAppItem;
import com.example.sergio.spotify_angular.utils.AppHelpers;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sergio on 13/05/2016.
 */
public class MenuExplorerFragment extends Fragment {

    private MenuAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.menu_explorer_fragment, container, false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        RecyclerView recyclerList = (RecyclerView) view.findViewById(R.id.menu_explorer_recyclerview);
        recyclerList.setLayoutManager(linearLayoutManager);

        adapter =  new MenuAdapter(getActivity(), new ArrayList<MenuAppItem>());
        recyclerList.setAdapter(adapter);
        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        List<MenuAppItem> menu = AppHelpers.getMenuFromResources(getActivity(), R.array.explorer_menu_items );
        adapter.setData(menu);
        adapter.notifyDataSetChanged();
    }

}
