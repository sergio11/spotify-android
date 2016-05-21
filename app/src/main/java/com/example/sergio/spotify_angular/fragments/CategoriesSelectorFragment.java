package com.example.sergio.spotify_angular.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sergio.spotify_angular.R;
import com.example.sergio.spotify_angular.adapters.CategoriesAdapter;
import com.example.sergio.spotify_angular.adapters.RecyclerViewBaseAdapter;
import com.example.sergio.spotify_angular.events.CategoriesLoadedEvent;
import com.example.sergio.spotify_angular.events.LoadCategoriesEvent;
import com.example.sergio.spotify_angular.utils.GridAutofitLayoutManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import kaaes.spotify.webapi.android.models.Category;


/**
 * Created by sergio on 04/05/2016.
 */
public class CategoriesSelectorFragment extends SelectorFragment<Category> implements RecyclerViewBaseAdapter.OnItemClickListener<Category>{

    private final static String TAG = "CATEGORIES";
    private final static int COLUMN_WIDTH = 350;
    protected EventBus bus;
    private RecyclerView recyclerList;


    public CategoriesSelectorFragment(CategoriesAdapter adapter) {
        super(adapter);
        bus = EventBus.getDefault();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.category_selector_fragment, container, false);
        GridAutofitLayoutManager gridLayoutManager = new GridAutofitLayoutManager(getActivity(),COLUMN_WIDTH, GridLayoutManager.VERTICAL, false);
        recyclerList = (RecyclerView) view.findViewById(R.id.categories_recyclerview);
        recyclerList.setLayoutManager(gridLayoutManager);
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
        bus.post(new LoadCategoriesEvent());
    }

    @Subscribe
    public void onCategoriesLoaded(CategoriesLoadedEvent event){
        adapter.setData(event.getCategories());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(Category item) {

    }


}
