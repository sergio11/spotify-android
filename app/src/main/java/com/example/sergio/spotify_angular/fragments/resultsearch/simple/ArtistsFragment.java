package com.example.sergio.spotify_angular.fragments.resultsearch.simple;


import com.example.sergio.spotify_angular.R;
import com.example.sergio.spotify_angular.adapters.RecyclerViewBaseAdapter;
import com.example.sergio.spotify_angular.adapters.resultsearch.ArtistsAdapter;
import com.example.sergio.spotify_angular.events.ArtistsFoundEvent;
import com.example.sergio.spotify_angular.events.SearchArtistsEvent;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import kaaes.spotify.webapi.android.models.Artist;

/**
 * Created by sergio on 11/06/2016.
 */
public class ArtistsFragment extends AbstractFragment<Artist> {

    @Override
    protected RecyclerViewBaseAdapter getAdapter() {
        return new ArtistsAdapter(getActivity(),new ArrayList<Artist>());
    }

    @Override
    protected int getTitle() {
        return R.string.search_result_artists;
    }

    @Override
    public void search(String text) {
        bus.post(new SearchArtistsEvent(text,options));
    }

    @Subscribe
    public void onArtistsFound(ArtistsFoundEvent event){
        if (event.getArtists().size() > 0){
            adapter.setData(event.getArtists());
            adapter.notifyDataSetChanged();
            listener.onDataFound(getView());
        }else{
            listener.onDataNotFound(getView());
        }
    }



}
