package com.example.sergio.spotify_angular.fragments.resultsearch;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.example.sergio.spotify_angular.adapters.ProgressLoadedAdapter;
import com.example.sergio.spotify_angular.adapters.resultsearch.AlbumsAdapter;
import com.example.sergio.spotify_angular.events.AlbumsFoundEvent;
import com.example.sergio.spotify_angular.events.NotFoundAlbumsEvent;
import com.example.sergio.spotify_angular.events.SearchAlbumsEvent;
import com.example.sergio.spotify_angular.utils.EndlessRecyclerViewScrollListener;

import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;
import java.util.Map;

import kaaes.spotify.webapi.android.models.AlbumSimple;

/**
 * Created by sergio on 18/06/2016.
 */
public class AlbumsFragment extends AbstractEndlessScrollFragment<AlbumSimple,LinearLayoutManager> {

    private LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bus.post(new SearchAlbumsEvent(text,defaultOptions));
    }

    @Override
    protected LinearLayoutManager getLayoutManager() {
        return linearLayoutManager;
    }

    @Override
    protected ProgressLoadedAdapter getAdapter() {
        return new AlbumsAdapter(getActivity(),data);
    }

    @Override
    protected EndlessRecyclerViewScrollListener getEndlessRecyclerViewScrollListener() {
        return new EndlessRecyclerViewScrollListener(linearLayoutManager) {

            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                final Map<String,Object> options = new HashMap<>();
                options.put("limit",totalItemsCount);
                options.put("offset",totalItemsCount + page);
                bus.post(new SearchAlbumsEvent(text,options));
            }
        };
    }

    @Subscribe
    public void onAlbumsFound(AlbumsFoundEvent event){
        this.addData(event.getAlbums());
    }

    @Subscribe
    public void onNotFoundAlbums(NotFoundAlbumsEvent event){
        this.notifyNoDataFound();
    }


}
