package com.example.sergio.spotify_angular.fragments.resultsearch;

import android.os.Bundle;
import android.view.View;

import com.example.sergio.spotify_angular.R;
import com.example.sergio.spotify_angular.adapters.resultsearch.PlaylistAdapter;
import com.example.sergio.spotify_angular.adapters.RecyclerViewBaseAdapter;
import com.example.sergio.spotify_angular.events.PlaylistSelectedEvent;
import com.example.sergio.spotify_angular.events.PlaylistsFoundEvent;
import com.example.sergio.spotify_angular.events.SearchPlaylistEvent;
import com.example.sergio.spotify_angular.events.SeeAllResultsEvent;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Locale;

import kaaes.spotify.webapi.android.models.PlaylistSimple;

/**
 * Created by sergio on 11/06/2016.
 */
public class PlaylistSimpleFragment extends AbstractSimpleFragment<PlaylistSimple> implements RecyclerViewBaseAdapter.OnItemClickListener<PlaylistSimple> {

    private String currentSearch;

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
    protected int getSeeAllText() {
        return R.string.see_all_playlist;
    }

    @Override
    protected void seeAllResults() {
        String title = String.format(Locale.getDefault(),getString(R.string.see_all_artists_playlist_title), currentSearch);
        bus.post(new SeeAllResultsEvent(title, currentSearch, SeeAllResultsEvent.ResultTypes.PLAYLIST ));
    }

    @Override
    public void search(String text) {
        currentSearch = text;
        bus.post(new SearchPlaylistEvent(text, options));
    }

    @Subscribe
    public void onPlaylistsFound(PlaylistsFoundEvent event){
        if (event.getPlaylists().size() > 0){
            adapter.setData(event.getPlaylists());
            adapter.notifyDataSetChanged();
            listener.onDataFound(getView());
        }else{
            listener.onDataNotFound(getView());
        }
    }

    @Override
    public void onItemClick(PlaylistSimple item) {
        bus.post(new PlaylistSelectedEvent(item));
    }
}
