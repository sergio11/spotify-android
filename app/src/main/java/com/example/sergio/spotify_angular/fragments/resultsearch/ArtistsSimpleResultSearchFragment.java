package com.example.sergio.spotify_angular.fragments.resultsearch;


import com.example.sergio.spotify_angular.R;
import com.example.sergio.spotify_angular.adapters.ArtistsSearchResultAdapter;
import com.example.sergio.spotify_angular.adapters.SearchResultAbstractAdapter;
import com.example.sergio.spotify_angular.events.ArtistsFoundEvent;
import com.example.sergio.spotify_angular.events.SearchArtistsEvent;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import kaaes.spotify.webapi.android.models.Artist;

/**
 * Created by sergio on 11/06/2016.
 */
public class ArtistsSimpleResultSearchFragment extends SimpleResultSearchFragment<Artist> {

    @Override
    protected SearchResultAbstractAdapter getAdapter() {
        return new ArtistsSearchResultAdapter(getActivity(),new ArrayList<Artist>());
    }

    @Override
    protected int getTitle() {
        return R.string.search_result_artists;
    }

    @Override
    public void load(String text) {
        bus.post(new SearchArtistsEvent(text,options));
    }

    @Subscribe
    public void onArtistsFound(ArtistsFoundEvent event){
        adapter.setData(event.getArtists());
        adapter.notifyDataSetChanged();
    }



}
