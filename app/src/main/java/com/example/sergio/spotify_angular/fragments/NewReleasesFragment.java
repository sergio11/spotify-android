package com.example.sergio.spotify_angular.fragments;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sergio.spotify_angular.R;
import com.example.sergio.spotify_angular.adapters.AlbumsAdapter;
import com.example.sergio.spotify_angular.adapters.RecyclerViewBaseAdapter;
import com.example.sergio.spotify_angular.events.LoadNewReleases;
import com.example.sergio.spotify_angular.events.NewReleasesLoaded;
import com.example.sergio.spotify_angular.utils.AppHelpers;
import com.example.sergio.spotify_angular.utils.GridAutofitLayoutManager;
import com.example.sergio.spotify_angular.utils.GridSpacingItemDecoration;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import kaaes.spotify.webapi.android.models.AlbumSimple;


public class NewReleasesFragment extends EventBusFragment implements RecyclerViewBaseAdapter.OnItemClickListener<AlbumSimple>{

    private RecyclerView recyclerView;
    private AlbumsAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_new_releases, container, false);
        float spanColumnWidth = getResources().getDimension(R.dimen.span_column_width_albums);
        recyclerView = (RecyclerView) view.findViewById(R.id.newReleases);
        recyclerView.setLayoutManager(new GridAutofitLayoutManager(getActivity(), (int) spanColumnWidth));
        recyclerView.setNestedScrollingEnabled(false);
        adapter = new AlbumsAdapter(getActivity(), new ArrayList<AlbumSimple>());
        adapter.setOnItemClickListener(this);
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        bus.post(new LoadNewReleases());
    }

    @Subscribe
    public void onNewReleasesLoaded(NewReleasesLoaded event){
        adapter.setData(event.getAlbums());
        adapter.notifyDataSetChanged();
    }


    @Override
    public void onItemClick(AlbumSimple item) {

    }
}
