package com.example.sergio.spotify_angular.fragments.resultsearch;

import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;

import com.example.sergio.spotify_angular.R;
import com.example.sergio.spotify_angular.adapters.RecyclerViewBaseAdapter;
import com.example.sergio.spotify_angular.adapters.resultsearch.AlbumsAdapter;
import com.example.sergio.spotify_angular.events.AlbumsFoundEvent;
import com.example.sergio.spotify_angular.events.SearchAlbumsEvent;
import com.example.sergio.spotify_angular.events.SeeAllResultsEvent;
import com.google.common.base.Function;
import com.google.common.collect.Lists;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Locale;

import kaaes.spotify.webapi.android.models.AlbumSimple;

/**
 * Created by sergio on 11/06/2016.
 */
public class AlbumsSimpleFragment extends AbstractSimpleFragment<AlbumSimple> {

    private String currentSearch;

    @Override
    protected RecyclerViewBaseAdapter getAdapter() {
        return new AlbumsAdapter(getActivity(), new ArrayList<AlbumSimple>());
    }

    @Override
    protected int getTitle() {
        return R.string.search_result_albums;
    }

    @Override
    protected int getSeeAllText() {
        return R.string.see_all_albums;
    }

    @Override
    protected void seeAllResults() {
        String title = String.format(Locale.getDefault(),getString(R.string.see_all_artists_albums_title), currentSearch);
        bus.post(new SeeAllResultsEvent(title, currentSearch, SeeAllResultsEvent.ResultTypes.ALBUMS ));
    }

    @Override
    public void search(String text) {
        currentSearch = text;
        adapter.setHighlightText(text);
        bus.post(new SearchAlbumsEvent(text,options));
    }

    @Subscribe
    public void onAlbumsFound(AlbumsFoundEvent event){
        if (event.getAlbums().size() > 0){
            adapter.setData(event.getAlbums());
            adapter.notifyDataSetChanged();
            listener.onDataFound(getView());
        }else{
            listener.onDataNotFound(getView());
        }
    }
}
