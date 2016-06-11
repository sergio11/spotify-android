package com.example.sergio.spotify_angular.fragments.resultsearch.simple;

import android.os.Bundle;
import android.view.View;

import com.example.sergio.spotify_angular.R;
import com.example.sergio.spotify_angular.adapters.resultsearch.PlaylistAdapter;
import com.example.sergio.spotify_angular.adapters.RecyclerViewBaseAdapter;
import com.example.sergio.spotify_angular.events.PlaylistSelectedEvent;
import com.example.sergio.spotify_angular.events.PlaylistsFoundEvent;
import com.example.sergio.spotify_angular.events.SearchPlaylistEvent;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import kaaes.spotify.webapi.android.models.PlaylistSimple;

/**
 * Created by sergio on 11/06/2016.
 */
public class PlaylistFragment extends AbstractFragment<PlaylistSimple> implements RecyclerViewBaseAdapter.OnItemClickListener<PlaylistSimple> {


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter.setOnItemClickListener(this);
    }

    @Override
    protected RecyclerViewBaseAdapter getAdapter() {
        return new PlaylistAdapter(getActivity(), new ArrayList<PlaylistSimple>());
    }

    @Override
    protected int getTitle() {
        return R.string.search_result_playlist;
    }

    @Override
    public void search(String text) {
        bus.post(new SearchPlaylistEvent(text, options));
    }

    @Subscribe
    public void onPlaylistsFound(PlaylistsFoundEvent event){
        adapter.setData(event.getPlaylists());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(PlaylistSimple item) {
        bus.post(new PlaylistSelectedEvent(item));
    }
}
