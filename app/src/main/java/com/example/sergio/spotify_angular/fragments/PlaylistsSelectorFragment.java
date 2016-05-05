package com.example.sergio.spotify_angular.fragments;

import android.os.Bundle;
import android.widget.Toast;


import com.example.sergio.spotify_angular.adapters.GridViewAdapter;

import kaaes.spotify.webapi.android.models.PlaylistSimple;
import kaaes.spotify.webapi.android.models.PlaylistsPager;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import com.example.sergio.spotify_angular.R;

/**
 * Created by sergio on 05/05/2016.
 */
public class PlaylistsSelectorFragment extends SelectorFragment<PlaylistSimple> {

    public final static String ARG_CATEGORY_ID = "category_id";

    public PlaylistsSelectorFragment(GridViewAdapter<PlaylistSimple> adapter) {
        super(adapter);
    }

    @Override
    protected void loadData() {
        Bundle args = getArguments();
        String categoryId = args.getString(PlaylistsSelectorFragment.ARG_CATEGORY_ID);
        app.getSpotify().getPlaylistsForCategory(categoryId, null, new Callback<PlaylistsPager>() {
            @Override
            public void success(PlaylistsPager list, Response response) {
                adapter.setData(list.playlists.items);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(app, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }


}
