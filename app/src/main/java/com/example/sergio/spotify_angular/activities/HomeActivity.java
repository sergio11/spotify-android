package com.example.sergio.spotify_angular.activities;


import android.os.Bundle;

import com.example.sergio.spotify_angular.R;
import com.example.sergio.spotify_angular.adapters.CategoriesAdapter;
import com.example.sergio.spotify_angular.adapters.MenuAdapter;
import com.example.sergio.spotify_angular.adapters.PlaylistsAdapter;
import com.example.sergio.spotify_angular.fragments.CategoriesSelectorFragment;
import com.example.sergio.spotify_angular.fragments.FeaturedPlaylistsFragment;
import com.example.sergio.spotify_angular.fragments.MenuExplorerFragment;
import com.example.sergio.spotify_angular.models.MenuAppItem;

import java.util.ArrayList;
import kaaes.spotify.webapi.android.models.Category;
import kaaes.spotify.webapi.android.models.PlaylistSimple;


public class HomeActivity extends BaseActivity implements CategoriesSelectorFragment.OnCategorySelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Load FeaturedPlayList Fragment
        PlaylistsAdapter playListAdapter = new PlaylistsAdapter(this, new ArrayList<PlaylistSimple>());
        FeaturedPlaylistsFragment featuredPlaylistsFragment = new FeaturedPlaylistsFragment(playListAdapter);
        showFragment(featuredPlaylistsFragment,R.id.featured_playlists);
        //Load MenuExplorer Fragment
        MenuAdapter menuAdapter = new MenuAdapter(this, new ArrayList<MenuAppItem>());
        MenuExplorerFragment menuExplorerFragment = new MenuExplorerFragment(menuAdapter);
        showFragment(menuExplorerFragment,R.id.menu_explorer_container);
        //Load Categories Fragment
        CategoriesAdapter categoriesAdapter = new CategoriesAdapter(this,new ArrayList<Category>());
        CategoriesSelectorFragment categoriesSelectorFragment = new CategoriesSelectorFragment(categoriesAdapter);
        showFragment(categoriesSelectorFragment,R.id.left_content);

    }

    @Override
    protected int getLayout() {
        return R.layout.home_main;
    }


    @Override
    public void onCategorySelected(String categoryId) {
        Bundle args = new Bundle();
        //args.putString(PlaylistsSelectorFragment.ARG_CATEGORY_ID, categoryId);

    }


}
