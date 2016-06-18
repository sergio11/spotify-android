package com.example.sergio.spotify_angular.fragments.resultsearch;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sergio.spotify_angular.R;
import com.example.sergio.spotify_angular.adapters.ProgressLoadedAdapter;
import com.example.sergio.spotify_angular.adapters.resultsearch.ArtistsAdapter;
import com.example.sergio.spotify_angular.events.ArtistsFoundEvent;
import com.example.sergio.spotify_angular.events.NotFoundArtistEvent;
import com.example.sergio.spotify_angular.events.SearchArtistsEvent;
import com.example.sergio.spotify_angular.fragments.EventBusFragment;
import com.example.sergio.spotify_angular.utils.DividerItemDecoration;
import com.example.sergio.spotify_angular.utils.EndlessRecyclerViewScrollListener;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kaaes.spotify.webapi.android.models.Artist;

/**
 * Created by sergio on 18/06/2016.
 */
public class ArtistFragment extends AbstractEndlessScrollFragment<Artist,LinearLayoutManager> {

    private LinearLayoutManager linearLayoutManager =  new LinearLayoutManager(getActivity());

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bus.post(new SearchArtistsEvent(text,defaultOptions));
    }

    @Override
    protected LinearLayoutManager getLayoutManager() {
        return linearLayoutManager;
    }

    @Override
    protected ProgressLoadedAdapter getAdapter() {
        return new ArtistsAdapter(getActivity(),data);
    }

    @Override
    protected EndlessRecyclerViewScrollListener getEndlessRecyclerViewScrollListener() {
        return new EndlessRecyclerViewScrollListener(linearLayoutManager) {

            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                final Map<String,Object> options = new HashMap<>();
                options.put("limit",totalItemsCount);
                options.put("offset",totalItemsCount + page);
                bus.post(new SearchArtistsEvent(text,options));

            }
        };
    }


    @Subscribe
    public void onArtistsFound(ArtistsFoundEvent event){

        List<Artist> artistsFound = event.getArtists();
        if (artistsFound.size() > 0){
            data.addAll(artistsFound);
            // For efficiency purposes, notify the adapter of only the elements that got changed
            // curSize will equal to the index of the first element inserted because the list is 0-indexed
            int curSize = adapter.getItemCount();
            adapter.notifyItemRangeInserted(curSize, data.size() - 1);
        }else{
            adapter.enableFooter(false);
        }
    }

    @Subscribe
    public void onNotFoundArtist(NotFoundArtistEvent event){
        adapter.enableFooter(false);
        adapter.notifyItemChanged(data.size());
    }




}
