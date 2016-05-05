package com.example.sergio.spotify_angular.fragments;

import com.example.sergio.spotify_angular.adapters.GridViewAdapter;

import java.util.List;

import kaaes.spotify.webapi.android.models.Playlist;

/**
 * Created by sergio on 05/05/2016.
 */
public class PlaylistsSelectorFragment extends SelectorFragment<Playlist> {

    public final static String ARG_CATEGORY_ID = "category_id";

    public PlaylistsSelectorFragment(GridViewAdapter<Playlist> adapter) {
        super(adapter);
    }

    @Override
    protected void loadData() {

    }


}
