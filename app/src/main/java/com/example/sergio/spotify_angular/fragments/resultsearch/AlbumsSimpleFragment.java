package com.example.sergio.spotify_angular.fragments.resultsearch;

import com.example.sergio.spotify_angular.R;
import com.example.sergio.spotify_angular.adapters.RecyclerViewBaseAdapter;
import com.example.sergio.spotify_angular.adapters.resultsearch.AlbumsAdapter;
import com.example.sergio.spotify_angular.events.AlbumsFoundEvent;
import com.example.sergio.spotify_angular.events.SearchAlbumsEvent;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import kaaes.spotify.webapi.android.models.AlbumSimple;

/**
 * Created by sergio on 11/06/2016.
 */
public class AlbumsSimpleFragment extends AbstractSimpleFragment<AlbumSimple> {

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

    }

    @Override
    public void search(String text) {
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
