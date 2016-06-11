package com.example.sergio.spotify_angular.fragments.resultsearch;

import com.example.sergio.spotify_angular.R;
import com.example.sergio.spotify_angular.adapters.AlbumsSearchResultAdapter;
import com.example.sergio.spotify_angular.adapters.SearchResultAbstractAdapter;
import com.example.sergio.spotify_angular.events.AlbumsFoundEvent;
import com.example.sergio.spotify_angular.events.SearchAlbumsEvent;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import kaaes.spotify.webapi.android.models.AlbumSimple;

/**
 * Created by sergio on 11/06/2016.
 */
public class AlbumsSimpleResultSearchFragment extends SimpleResultSearchFragment<AlbumSimple> {

    @Override
    protected SearchResultAbstractAdapter getAdapter() {
        return new AlbumsSearchResultAdapter(getActivity(), new ArrayList<AlbumSimple>());
    }

    @Override
    protected int getTitle() {
        return R.string.search_result_albums;
    }

    @Override
    public void load(String text) {
        bus.post(new SearchAlbumsEvent(text,options));
    }

    @Subscribe
    public void onAlbumsFound(AlbumsFoundEvent event){
        adapter.setData(event.getAlbums());
        adapter.notifyDataSetChanged();
    }
}
