package com.example.sergio.spotify_angular.fragments.resultsearch;


import android.os.Bundle;


import com.example.sergio.spotify_angular.R;
import com.example.sergio.spotify_angular.adapters.RecyclerViewBaseAdapter;
import com.example.sergio.spotify_angular.adapters.resultsearch.ArtistsAdapter;
import com.example.sergio.spotify_angular.events.ArtistsFoundEvent;
import com.example.sergio.spotify_angular.events.FollowedArtistFoundEvent;
import com.example.sergio.spotify_angular.events.GetFollowedArtistsEvent;
import com.example.sergio.spotify_angular.events.SearchArtistsEvent;
import com.example.sergio.spotify_angular.events.SeeAllResultsEvent;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import kaaes.spotify.webapi.android.models.Artist;

/**
 * Created by sergio on 11/06/2016.
 */
public class ArtistsSimpleFragment extends AbstractSimpleFragment<Artist> {

    private List<Artist> followedArtists;
    private String currentSearch;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bus.post(new GetFollowedArtistsEvent());
    }

    @Override
    protected RecyclerViewBaseAdapter getAdapter() {
        return new ArtistsAdapter(getActivity(),new ArrayList<Artist>());
    }

    @Override
    protected int getTitle() {
        return R.string.search_result_artists;
    }

    @Override
    protected int getSeeAllText() {
        return R.string.see_all_artists;
    }

    @Override
    protected void seeAllResults() {
        String title = String.format(Locale.getDefault(),getString(R.string.see_all_artists_title), currentSearch);
        bus.post(new SeeAllResultsEvent(title, currentSearch, SeeAllResultsEvent.ResultTypes.ARTISTS ));
    }

    @Override
    public void search(String text) {
        currentSearch = text;
        bus.post(new SearchArtistsEvent(text,options));
    }


    private void markFollowedArtists(){

        List<Artist> artists = adapter.getData();
        for (int i = 0, leni = followedArtists.size(); i < leni; i++){
            for (int k = 0, lenk = artists.size(); k < lenk; k++){
                if (followedArtists.get(i).id.equals(artists.get(k).id)){
                    adapter.notifyItemChanged(k,"Hello Morenito");
                }
            }

        }
    }

    @Subscribe
    public void onArtistsFound(ArtistsFoundEvent event){
        if (event.getArtists().size() > 0){
            adapter.setData(event.getArtists());
            adapter.notifyDataSetChanged();
            if (followedArtists != null && followedArtists.size() > 0)
                markFollowedArtists();
            listener.onDataFound(getView());
        }else{
            listener.onDataNotFound(getView());
        }
    }

    @Subscribe
    public void onFollowedArtistFound(FollowedArtistFoundEvent event){
        followedArtists = event.getArtists();
        if (adapter.getData() != null && adapter.getData().size() > 0){
            markFollowedArtists();
        }

    }



}
