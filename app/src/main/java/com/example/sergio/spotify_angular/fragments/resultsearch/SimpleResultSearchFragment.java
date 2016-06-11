package com.example.sergio.spotify_angular.fragments.resultsearch;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sergio.spotify_angular.R;
import com.example.sergio.spotify_angular.adapters.SearchResultAbstractAdapter;
import com.example.sergio.spotify_angular.fragments.EventBusFragment;
import com.example.sergio.spotify_angular.utils.DividerItemDecoration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public abstract class SimpleResultSearchFragment<T> extends EventBusFragment {

    protected TextView titleTextView;
    protected RecyclerView recyclerView;

    protected List<T> data;
    protected SearchResultAbstractAdapter adapter;
    protected Map<String, Object> options;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        options = new HashMap<>();
        options.put("limit",5);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_result_search_simple, container, false);
        titleTextView = (TextView) view.findViewById(R.id.result_title);
        titleTextView.setText(getString(getTitle()));
        recyclerView = (RecyclerView) view.findViewById(R.id.result_recyclerView);
        recyclerView.setLayoutManager( new LinearLayoutManager(getActivity()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), null,true, true));
        adapter = getAdapter();
        recyclerView.setAdapter(adapter);
        return view;
    }

    protected abstract SearchResultAbstractAdapter getAdapter();

    protected abstract int getTitle();

    public abstract void load(String text);

}
