package com.example.sergio.spotify_angular.fragments.resultsearch;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sergio.spotify_angular.R;
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
public class ArtistFragment extends EventBusFragment {

    private static final String ARG_TEXT = "text_search";
    private static final int DEFAULT_TOTAL_ITEM = 20;

    private String text;

    private RecyclerView recyclerView;
    private ArtistsAdapter artistsAdapter;
    private List<Artist> artists = new ArrayList<>();

    public static ArtistFragment newInstance(String text) {
        ArtistFragment fragment = new ArtistFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TEXT, text);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            text = getArguments().getString(ARG_TEXT);
        }
        Map<String,Object> options = new HashMap<>();
        options.put("limit",DEFAULT_TOTAL_ITEM);
        options.put("offset",0);
        bus.post(new SearchArtistsEvent(text,options));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_see_all_results, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getResources().getDrawable(R.drawable.white_divider),false, true));
        artistsAdapter = new ArtistsAdapter(getActivity(), artists);
        artistsAdapter.enableFooter(true);
        recyclerView.setAdapter(artistsAdapter);
        recyclerView.addOnScrollListener(new EndlessRecyclerViewScrollListener(linearLayoutManager) {

            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                final Map<String,Object> options = new HashMap<>();
                options.put("limit",totalItemsCount);
                options.put("offset",totalItemsCount + page);
                bus.post(new SearchArtistsEvent(text,options));

            }
        });

        return view;
    }

    @Subscribe
    public void onArtistsFound(ArtistsFoundEvent event){

        List<Artist> artistsFound = event.getArtists();
        if (artistsFound.size() > 0){
            artists.addAll(artistsFound);
            // For efficiency purposes, notify the adapter of only the elements that got changed
            // curSize will equal to the index of the first element inserted because the list is 0-indexed
            int curSize = artistsAdapter.getItemCount();
            artistsAdapter.notifyItemRangeInserted(curSize, artists.size() - 1);
        }else{
            artistsAdapter.enableFooter(false);
        }
    }

    @Subscribe
    public void onNotFoundArtist(NotFoundArtistEvent event){
        artistsAdapter.enableFooter(false);
        artistsAdapter.notifyItemChanged(artists.size());
    }




}
