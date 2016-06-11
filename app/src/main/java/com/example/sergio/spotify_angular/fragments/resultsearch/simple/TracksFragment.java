package com.example.sergio.spotify_angular.fragments.resultsearch.simple;

import com.example.sergio.spotify_angular.R;
import com.example.sergio.spotify_angular.adapters.RecyclerViewBaseAdapter;
import com.example.sergio.spotify_angular.adapters.resultsearch.TracksAdapter;
import com.example.sergio.spotify_angular.events.SearchTracksEvent;
import com.example.sergio.spotify_angular.events.TracksFoundEvent;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import kaaes.spotify.webapi.android.models.Track;

/**
 * Created by sergio on 11/06/2016.
 */
public class TracksFragment extends AbstractFragment {

    @Override
    protected RecyclerViewBaseAdapter getAdapter() {
        return new TracksAdapter(getActivity(), new ArrayList<Track>());
    }

    @Override
    protected int getTitle() {
        return R.string.search_result_tracks;
    }

    @Override
    public void search(String text) {
        bus.post(new SearchTracksEvent(text,options));
    }

    @Subscribe
    public void onTracksFound(TracksFoundEvent event){
        if (event.getTracks().size() > 0){
            adapter.setData(event.getTracks());
            adapter.notifyDataSetChanged();
            listener.onDataFound(getView());
        }else{
            listener.onDataNotFound(getView());
        }

    }
}
