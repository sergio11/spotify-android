package com.example.sergio.spotify_angular.fragments.resultsearch;

import android.os.Bundle;

import com.example.sergio.spotify_angular.R;
import com.example.sergio.spotify_angular.adapters.ProgressLoadedAdapter;
import com.example.sergio.spotify_angular.adapters.RecyclerViewBaseAdapter;
import com.example.sergio.spotify_angular.adapters.resultsearch.PlayListCardAdapter;
import com.example.sergio.spotify_angular.events.NotFoundPlaylistEvent;
import com.example.sergio.spotify_angular.events.PlaylistSelectedEvent;
import com.example.sergio.spotify_angular.events.PlaylistsFoundEvent;
import com.example.sergio.spotify_angular.events.SearchPlaylistEvent;
import com.example.sergio.spotify_angular.utils.EndlessRecyclerViewScrollListener;
import com.example.sergio.spotify_angular.utils.GridAutofitLayoutManager;


import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;
import java.util.Map;

import kaaes.spotify.webapi.android.models.PlaylistSimple;

/**
 * Created by sergio on 18/06/2016.
 */
public class PlaylistFragment extends AbstractEndlessScrollFragment<PlaylistSimple,GridAutofitLayoutManager> implements RecyclerViewBaseAdapter.OnItemClickListener<PlaylistSimple> {

    GridAutofitLayoutManager gridAutofitLayoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        float spanColumnWidth = getResources().getDimension(R.dimen.span_column_width_albums);
        gridAutofitLayoutManager = new GridAutofitLayoutManager(getActivity(), (int) spanColumnWidth);
        bus.post(new SearchPlaylistEvent(text,defaultOptions));
    }

    @Override
    protected GridAutofitLayoutManager getLayoutManager() {
        return gridAutofitLayoutManager;
    }

    @Override
    protected ProgressLoadedAdapter getAdapter() {
        PlayListCardAdapter adapter = new PlayListCardAdapter(getActivity(),data);
        adapter.setOnItemClickListener(this);
        return adapter;
    }

    @Override
    protected EndlessRecyclerViewScrollListener getEndlessRecyclerViewScrollListener() {
        return new EndlessRecyclerViewScrollListener(gridAutofitLayoutManager) {

            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                final Map<String,Object> options = new HashMap<>();
                options.put("limit",totalItemsCount);
                options.put("offset",totalItemsCount + page);
                bus.post(new SearchPlaylistEvent(text,options));

            }
        };
    }

    @Subscribe
    public void onPlaylistsFound(PlaylistsFoundEvent event){
        this.addData(event.getPlaylists());
    }

    @Subscribe
    public void onNotFoundPlaylist(NotFoundPlaylistEvent event){
        this.notifyNoDataFound();
    }

    @Override
    public void onItemClick(PlaylistSimple item) {
        bus.post(new PlaylistSelectedEvent(item));
    }
}
