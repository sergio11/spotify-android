package com.example.sergio.spotify_angular.fragments.resultsearch;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.example.sergio.spotify_angular.adapters.ProgressLoadedAdapter;
import com.example.sergio.spotify_angular.adapters.RecyclerViewBaseAdapter;
import com.example.sergio.spotify_angular.adapters.resultsearch.PlaylistAdapter;
import com.example.sergio.spotify_angular.events.LoadMyPlaylistsEvent;
import com.example.sergio.spotify_angular.events.MyPlaylistsLoadedEvent;
import com.example.sergio.spotify_angular.events.NotFoundPlaylistEvent;
import com.example.sergio.spotify_angular.events.PlaylistSelectedEvent;
import com.example.sergio.spotify_angular.utils.EndlessRecyclerViewScrollListener;

import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;
import java.util.Map;

import kaaes.spotify.webapi.android.models.PlaylistSimple;

/**
 * Created by sergio on 25/06/2016.
 */
public class MyPlaylistsFragment extends AbstractEndlessScrollFragment<PlaylistSimple,LinearLayoutManager> implements RecyclerViewBaseAdapter.OnItemClickListener<PlaylistSimple> {

    private LinearLayoutManager linearLayoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bus.post(new LoadMyPlaylistsEvent(defaultOptions));
    }



    @Override
    protected LinearLayoutManager getLayoutManager() {
        linearLayoutManager =  new LinearLayoutManager(getActivity());
        return linearLayoutManager;
    }

    @Override
    protected ProgressLoadedAdapter getAdapter() {
        PlaylistAdapter adapter = new PlaylistAdapter(getActivity(), data);
        adapter.setOnItemClickListener(this);
        return adapter;
    }

    @Override
    protected EndlessRecyclerViewScrollListener getEndlessRecyclerViewScrollListener() {
         return new EndlessRecyclerViewScrollListener(linearLayoutManager) {

            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                final Map<String,Object> options = new HashMap<>();
                options.put("limit",totalItemsCount);
                options.put("offset",totalItemsCount + page);
                bus.post(new LoadMyPlaylistsEvent(options));
            }
        };
    }

    @Override
    public void onItemClick(PlaylistSimple item) {
        bus.post(new PlaylistSelectedEvent(item));
    }

    @Subscribe
    public void onMyPlaylistsLoaded(MyPlaylistsLoadedEvent event){
        if (event.getItems().size() > 0)
            this.addData(event.getItems());
        else
            this.notifyNoDataFound();
    }

    @Subscribe
    public void onNotFoundPlaylist(NotFoundPlaylistEvent event){
        this.notifyNoDataFound();
    }
}
