package com.example.sergio.spotify_angular.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.sergio.spotify_angular.R;
import com.example.sergio.spotify_angular.adapters.CategoriesAdapter;
import com.example.sergio.spotify_angular.adapters.PlaylistsAdapter;
import com.example.sergio.spotify_angular.fragments.CategoriesSelectorFragment;
import com.example.sergio.spotify_angular.fragments.FeaturedPlaylistsFragment;
import com.example.sergio.spotify_angular.fragments.PlaylistsSelectorFragment;
import com.example.sergio.spotify_angular.utils.AppHelpers;

import java.util.ArrayList;

import kaaes.spotify.webapi.android.models.Category;
import kaaes.spotify.webapi.android.models.PlaylistSimple;


public class HomeActivity extends AppCompatActivity implements CategoriesSelectorFragment.OnCategorySelectedListener {


    private CategoriesAdapter categoriesAdapter;
    private PlaylistsAdapter playListAdapter;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        categoriesAdapter = new CategoriesAdapter(this,new ArrayList<Category>());
        playListAdapter = new PlaylistsAdapter(this, new ArrayList<PlaylistSimple>());
        setContentView(R.layout.home_main);
        showFeaturedPlaylistsFragment(R.id.featured_playlists);
        showCategoriesSelectorFragment(R.id.left_content);
    }

    @Override
    public void onCategorySelected(String categoryId) {
        Bundle args = new Bundle();
        args.putString(PlaylistsSelectorFragment.ARG_CATEGORY_ID, categoryId);

    }

    private void showFeaturedPlaylistsFragment(int target){
        if (findViewById(target) != null && playListAdapter != null){
            FeaturedPlaylistsFragment fragment = new FeaturedPlaylistsFragment(playListAdapter);
            AppHelpers.setFragment(this,fragment,target, false, false);
        }

    }

    private void showCategoriesSelectorFragment(int target){
        if (findViewById(target) != null && categoriesAdapter != null){
            CategoriesSelectorFragment fragment = new CategoriesSelectorFragment(categoriesAdapter);
            AppHelpers.setFragment(this,fragment,target, false, false);
        }
    }
}
