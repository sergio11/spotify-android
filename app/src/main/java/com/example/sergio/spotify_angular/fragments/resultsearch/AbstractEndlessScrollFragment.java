package com.example.sergio.spotify_angular.fragments.resultsearch;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sergio.spotify_angular.R;
import com.example.sergio.spotify_angular.adapters.ProgressLoadedAdapter;
import com.example.sergio.spotify_angular.adapters.resultsearch.ArtistsAdapter;
import com.example.sergio.spotify_angular.fragments.EventBusFragment;
import com.example.sergio.spotify_angular.utils.DividerItemDecoration;
import com.example.sergio.spotify_angular.utils.EndlessRecyclerViewScrollListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by sergio on 18/06/2016.
 */
public abstract class AbstractEndlessScrollFragment<T, E extends RecyclerView.LayoutManager> extends EventBusFragment{

    private static final String ARG_TEXT = "text_search";
    private static final int DEFAULT_TOTAL_ITEM = 20;

    protected String text;
    protected ProgressLoadedAdapter adapter;
    protected List<T> data = new ArrayList<>();
    protected Map<String,Object> defaultOptions;

    public static AbstractEndlessScrollFragment newInstance(String text, Class<? extends AbstractEndlessScrollFragment> classFragment) throws IllegalAccessException, java.lang.InstantiationException {
        AbstractEndlessScrollFragment fragment = classFragment.newInstance();
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
        defaultOptions = new HashMap<>();
        defaultOptions.put("limit",DEFAULT_TOTAL_ITEM);
        defaultOptions.put("offset",0);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_see_all_results, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(getLayoutManager());
        recyclerView.addItemDecoration(new DividerItemDecoration(getResources().getDrawable(R.drawable.white_divider),false, true));
        adapter = getAdapter();
        adapter.enableFooter(true);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(getEndlessRecyclerViewScrollListener());

        return view;
    }


    protected void addData(List<T> newData){
        if (newData != null && newData.size() > 0){
            data.addAll(newData);
            // For efficiency purposes, notify the adapter of only the elements that got changed
            // curSize will equal to the index of the first element inserted because the list is 0-indexed
            int curSize = adapter.getItemCount();
            adapter.notifyItemRangeInserted(curSize, data.size() - 1);
        }else{
            adapter.enableFooter(false);
        }
    }

    protected void notifyNoDataFound(){
        adapter.enableFooter(false);
        adapter.notifyItemChanged(data.size());
    }


    protected abstract E getLayoutManager();
    protected abstract ProgressLoadedAdapter getAdapter();
    protected abstract EndlessRecyclerViewScrollListener getEndlessRecyclerViewScrollListener();

}
