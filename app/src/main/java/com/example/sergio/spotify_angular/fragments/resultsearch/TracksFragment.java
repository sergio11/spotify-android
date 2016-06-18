package com.example.sergio.spotify_angular.fragments.resultsearch;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.example.sergio.spotify_angular.adapters.ProgressLoadedAdapter;
import com.example.sergio.spotify_angular.adapters.resultsearch.TracksAdapter;
import com.example.sergio.spotify_angular.events.NotFoundTracksEvent;
import com.example.sergio.spotify_angular.events.SearchTracksEvent;
import com.example.sergio.spotify_angular.events.TracksFoundEvent;
import com.example.sergio.spotify_angular.utils.EndlessRecyclerViewScrollListener;
import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;
import java.util.Map;

import kaaes.spotify.webapi.android.models.Track;

/**
 * Created by sergio on 18/06/2016.
 */
public class TracksFragment extends AbstractEndlessScrollFragment<Track,LinearLayoutManager> {

    private LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bus.post(new SearchTracksEvent(text,defaultOptions));
    }

    @Override
    protected LinearLayoutManager getLayoutManager() {
        return linearLayoutManager;
    }

    @Override
    protected ProgressLoadedAdapter getAdapter() {
        return new TracksAdapter(getActivity(),data);
    }

    @Override
    protected EndlessRecyclerViewScrollListener getEndlessRecyclerViewScrollListener() {
        return new EndlessRecyclerViewScrollListener(linearLayoutManager) {

            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                final Map<String,Object> options = new HashMap<>();
                options.put("limit",totalItemsCount);
                options.put("offset",totalItemsCount + page);
                bus.post(new SearchTracksEvent(text,options));

            }
        };
    }

    @Subscribe
    public void onTracksFound(TracksFoundEvent event){
        this.addData(event.getTracks());
    }

    @Subscribe
    public void onNotFoundTracks(NotFoundTracksEvent event){
        this.notifyNoDataFound();
    }

}
