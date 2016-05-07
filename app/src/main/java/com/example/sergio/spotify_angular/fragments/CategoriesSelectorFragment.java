package com.example.sergio.spotify_angular.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.sergio.spotify_angular.R;
import com.example.sergio.spotify_angular.adapters.CategoriesAdapter;

import kaaes.spotify.webapi.android.models.CategoriesPager;
import kaaes.spotify.webapi.android.models.Category;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by sergio on 04/05/2016.
 */
public class CategoriesSelectorFragment extends SelectorFragment<Category> {

    private final static String TAG = "CATEGORIES";


    public CategoriesSelectorFragment(CategoriesAdapter adapter) {
        super(adapter);
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
    protected void loadData() {
        app.getSpotify().getCategories(null, new Callback<CategoriesPager>() {
            @Override
            public void success(CategoriesPager categoriesPager, Response response) {
                adapter.setData(categoriesPager.categories.items);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG,error.getMessage());
                Toast.makeText(app, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public interface OnCategorySelectedListener{
        void onCategorySelected(String categoryId);
    }
}
