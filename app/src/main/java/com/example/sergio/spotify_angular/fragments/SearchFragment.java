package com.example.sergio.spotify_angular.fragments;

import android.app.Fragment;
import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.example.sergio.spotify_angular.R;
import com.example.sergio.spotify_angular.activities.HomeActivity;
import com.example.sergio.spotify_angular.fragments.resultsearch.AlbumsSimpleResultSearchFragment;
import com.example.sergio.spotify_angular.fragments.resultsearch.ArtistsSimpleResultSearchFragment;
import com.example.sergio.spotify_angular.fragments.resultsearch.SimpleResultSearchFragment;
import com.example.sergio.spotify_angular.utils.AppHelpers;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by sergio on 04/06/2016.
 */
public class SearchFragment extends Fragment implements SearchView.OnQueryTextListener{

    private SearchView searchView;
    private InputMethodManager imm;
    private List<SimpleResultSearchFragment> resultFragments;

    private ProgressBar loadingView;
    private ViewGroup noSearch;
    private ViewGroup resultsContainer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        resultFragments = new ArrayList<>();
        try {
            Class[] fragments = {ArtistsSimpleResultSearchFragment.class, AlbumsSimpleResultSearchFragment.class};
            for (int i = 0, len = fragments.length; i < len; i++){
                SimpleResultSearchFragment fragment = (SimpleResultSearchFragment) fragments[i].newInstance();
                resultFragments.add(fragment);
                AppHelpers.setFragment(getActivity(),fragment,R.id.results,false,false);
            }
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_layout, container, false);
        resultsContainer = (ViewGroup) view.findViewById(R.id.results_container);
        noSearch = (ViewGroup)view.findViewById(R.id.no_search);
        loadingView = (ProgressBar) view.findViewById(R.id.loadingView);
        imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search_menu,menu);
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
        searchView.setIconifiedByDefault(false);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(new ComponentName(getActivity(), HomeActivity.class)));
        searchView.setOnQueryTextListener(this);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imm.showSoftInput(searchView,0);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        //hide the Android Soft Keyboard
        imm.hideSoftInputFromWindow(searchView.getWindowToken(), 0);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String query) {
        if (!query.isEmpty()){
            noSearch.setVisibility(View.GONE);
            resultsContainer.setVisibility(View.VISIBLE);
        }

        for (SimpleResultSearchFragment fragment : resultFragments)
            fragment.load(query);
        return true;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        //hide the Android Soft Keyboard
        imm.hideSoftInputFromWindow(searchView.getWindowToken(), 0);
    }


}
