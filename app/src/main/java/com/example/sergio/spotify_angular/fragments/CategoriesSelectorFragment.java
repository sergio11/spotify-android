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
import com.example.sergio.spotify_angular.events.CategoriesLoadedEvent;
import com.example.sergio.spotify_angular.events.LoadCategoriesEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import kaaes.spotify.webapi.android.models.Category;


/**
 * Created by sergio on 04/05/2016.
 */
public class CategoriesSelectorFragment extends SelectorFragment<Category> {

    private final static String TAG = "CATEGORIES";
    protected EventBus bus;


    public CategoriesSelectorFragment(CategoriesAdapter adapter) {
        super(adapter);
        bus = EventBus.getDefault();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.category_selector_fragment, container, false);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),3,GridLayoutManager.VERTICAL, false);
        RecyclerView recyclerList = (RecyclerView) view.findViewById(R.id.categories_recyclerview);
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



    public interface OnCategorySelectedListener{
        void onCategorySelected(String categoryId);
    }
}
