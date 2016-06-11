package com.example.sergio.spotify_angular.fragments.resultsearch.simple;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sergio.spotify_angular.R;
import com.example.sergio.spotify_angular.adapters.RecyclerViewBaseAdapter;
import com.example.sergio.spotify_angular.fragments.EventBusFragment;
import com.example.sergio.spotify_angular.utils.DividerItemDecoration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public abstract class AbstractFragment<T> extends EventBusFragment {

    protected TextView titleTextView;
    protected RecyclerView recyclerView;

    protected List<T> data;
    protected RecyclerViewBaseAdapter adapter;
    protected Map<String, Object> options;
    protected OnResultSearchListener listener;


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
        recyclerView.addItemDecoration(new DividerItemDecoration(getResources().getDrawable(R.drawable.white_divider),false, false));
        adapter = getAdapter();
        recyclerView.setAdapter(adapter);
        return view;
    }

    protected abstract RecyclerViewBaseAdapter getAdapter();

    protected abstract int getTitle();

    public abstract void search(String text);

    public void setListener(OnResultSearchListener listener) {
        this.listener = listener;
    }

    public interface OnResultSearchListener{
        void onDataNotFound(View view);
        void onDataFound(View view);
    }

}
