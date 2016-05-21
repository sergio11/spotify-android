package com.example.sergio.spotify_angular.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sergio.spotify_angular.R;
import com.example.sergio.spotify_angular.adapters.PlaylistsAdapter;
import com.example.sergio.spotify_angular.adapters.RecyclerViewBaseAdapter;
import com.example.sergio.spotify_angular.events.FeaturedPlaylistLoadedEvent;
import com.example.sergio.spotify_angular.events.LoadFeaturedPlaylistEvent;
import com.example.sergio.spotify_angular.events.PlaylistSelectedEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import kaaes.spotify.webapi.android.models.PlaylistSimple;


/**
 * Created by sergio on 07/05/2016.
 */
public class FeaturedPlaylistsSelectorFragment extends SelectorFragment<PlaylistSimple> implements RecyclerViewBaseAdapter.OnItemClickListener<PlaylistSimple>{

    protected EventBus bus;
    protected TextView message;
    private RecyclerView recyclerList;

    public FeaturedPlaylistsSelectorFragment(PlaylistsAdapter adapter) {
        super(adapter);
        bus = EventBus.getDefault();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.featured_playlist_fragment, container, false);
        message = (TextView) view.findViewById(R.id.message);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerList = (RecyclerView) view.findViewById(R.id.featured_playlists_recyclerview);
        recyclerList.setLayoutManager(layoutManager);
        recyclerList.setAdapter(adapter);
        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Override
    protected void loadData() {
        bus.post(new LoadFeaturedPlaylistEvent());
    }


    @Subscribe
    public void onFeaturedPlaylistLoaded(FeaturedPlaylistLoadedEvent event){
        message.setText(event.getMessage());
        adapter.setData(event.getPlaylist());
        adapter.notifyDataSetChanged();
    }


    @Override
    public void onItemClick(PlaylistSimple item) {
        bus.post(new PlaylistSelectedEvent(item));
    }
}
