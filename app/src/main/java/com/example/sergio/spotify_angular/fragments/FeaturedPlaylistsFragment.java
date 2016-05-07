package com.example.sergio.spotify_angular.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sergio.spotify_angular.R;
import com.example.sergio.spotify_angular.adapters.PlaylistsAdapter;

import kaaes.spotify.webapi.android.models.FeaturedPlaylists;
import kaaes.spotify.webapi.android.models.PlaylistSimple;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by sergio on 07/05/2016.
 */
public class FeaturedPlaylistsFragment extends SelectorFragment<PlaylistSimple> {

    public FeaturedPlaylistsFragment(PlaylistsAdapter adapter) {
        super(adapter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.featured_playlist_fragment, container, false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerList = (RecyclerView) view.findViewById(R.id.featured_playlists_recyclerview);
        recyclerList.setLayoutManager(layoutManager);
        recyclerList.setAdapter(adapter);
        return view;
    }

    @Override
    protected void loadData() {

        app.getSpotify().getFeaturedPlaylists(new Callback<FeaturedPlaylists>() {
            @Override
            public void success(FeaturedPlaylists featuredPlaylists, Response response) {
                TextView message = (TextView) getView().findViewById(R.id.message);
                message.setText(featuredPlaylists.message);
                adapter.setData(featuredPlaylists.playlists.items);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(app, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }
}
