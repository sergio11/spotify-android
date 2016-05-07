package com.example.sergio.spotify_angular.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.sergio.spotify_angular.adapters.CategoriesAdapter;

import com.example.sergio.spotify_angular.R;
import com.example.sergio.spotify_angular.adapters.GridViewAdapter;

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
