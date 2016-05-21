package com.example.sergio.spotify_angular.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sergio.spotify_angular.R;
import com.example.sergio.spotify_angular.adapters.CategoriesAdapter;
import com.example.sergio.spotify_angular.adapters.MenuAdapter;
import com.example.sergio.spotify_angular.adapters.PlaylistsAdapter;
import com.example.sergio.spotify_angular.adapters.RecyclerViewBaseAdapter;
import com.example.sergio.spotify_angular.events.PlaylistSelectedEvent;
import com.example.sergio.spotify_angular.models.MenuAppItem;
import com.example.sergio.spotify_angular.utils.AppHelpers;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import kaaes.spotify.webapi.android.models.Category;
import kaaes.spotify.webapi.android.models.PlaylistSimple;

/**
 * Created by sergio on 14/05/2016.
 */
public class ExplorerFragment extends Fragment{

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_explorer, container, false);

        //Load FeaturedPlayList Fragment
        PlaylistsAdapter playListAdapter = new PlaylistsAdapter(getActivity(), new ArrayList<PlaylistSimple>());

        FeaturedPlaylistsSelectorFragment featuredPlaylistsFragment = new FeaturedPlaylistsSelectorFragment(playListAdapter);
        AppHelpers.setFragment(getActivity(),featuredPlaylistsFragment,R.id.featured_playlists, false, false);
        //Load MenuExplorer Fragment
        MenuAdapter menuAdapter = new MenuAdapter(getActivity(), new ArrayList<MenuAppItem>());
        MenuExplorerFragment menuExplorerFragment = new MenuExplorerFragment(menuAdapter);
        AppHelpers.setFragment(getActivity(),menuExplorerFragment,R.id.menu_explorer_container, false, false);

        //Load Categories Fragment
        CategoriesAdapter categoriesAdapter = new CategoriesAdapter(getActivity(), new ArrayList<Category>());
        CategoriesSelectorFragment categoriesSelectorFragment = new CategoriesSelectorFragment(categoriesAdapter);
        AppHelpers.setFragment(getActivity(),categoriesSelectorFragment,R.id.left_content, false, false);

        return view;

    }



}
