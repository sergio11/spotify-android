package com.example.sergio.spotify_angular.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.provider.SearchRecentSuggestions;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.example.sergio.spotify_angular.R;
import com.example.sergio.spotify_angular.activities.HomeActivity;
import com.example.sergio.spotify_angular.contentproviders.SearchHistoryContentProvider;
import com.example.sergio.spotify_angular.fragments.resultsearch.AbstractSimpleFragment;
import com.example.sergio.spotify_angular.fragments.resultsearch.AlbumsSimpleFragment;
import com.example.sergio.spotify_angular.fragments.resultsearch.ArtistsSimpleFragment;
import com.example.sergio.spotify_angular.fragments.resultsearch.PlaylistSimpleFragment;
import com.example.sergio.spotify_angular.fragments.resultsearch.TracksSimpleFragment;
import com.example.sergio.spotify_angular.utils.AppHelpers;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


/**
 * Created by sergio on 04/06/2016.
 */
public class SearchFragment extends Fragment implements SearchView.OnQueryTextListener, AbstractSimpleFragment.OnResultSearchListener{

    private SearchView searchView;
    private InputMethodManager imm;
    private List<AbstractSimpleFragment> resultFragments;
    private SearchRecentSuggestions suggestions = new SearchRecentSuggestions(getActivity(),
            SearchHistoryContentProvider.AUTHORITY, SearchHistoryContentProvider.MODE);
    private TextView notResultFoundTextView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        resultFragments = new ArrayList<>();

        try {
            Class[] fragments = {ArtistsSimpleFragment.class, AlbumsSimpleFragment.class, PlaylistSimpleFragment.class, TracksSimpleFragment.class};
            for (int i = 0, len = fragments.length; i < len; i++){
                AbstractSimpleFragment fragment = (AbstractSimpleFragment) fragments[i].newInstance();
                fragment.setListener(this);
                resultFragments.add(fragment);
                AppHelpers.setFragment(getActivity(),fragment,R.id.results,false,true);
            }
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
    }



    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_layout, container, false);
        notResultFoundTextView = (TextView) view.findViewById(R.id.not_result_found_primary);
        setHasOptionsMenu(true);
        FragmentManager childFm = getFragmentManager();
        int entryCount = childFm.getBackStackEntryCount();
        if (entryCount > 0) {
            for (Fragment childfragnested: resultFragments) {
                FragmentManager childFmNestManager = childfragnested.getFragmentManager();
                if (childFmNestManager.getBackStackEntryCount() > 0)
                    childFmNestManager.popBackStack();
            }
        }

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
        
        /*searchView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                return;
            }
        });*/

    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        //hide the Android Soft Keyboard
        imm.hideSoftInputFromWindow(searchView.getWindowToken(), 0);

        suggestions.saveRecentQuery(query, null);


        return true;
    }

    @Override
    public boolean onQueryTextChange(String query) {
        if (!query.isEmpty()){
            AppHelpers.showViewById((ViewGroup) getView(),R.id.results_container);
            for (AbstractSimpleFragment fragment : resultFragments) fragment.search(query);
        }else{
            AppHelpers.showViewById((ViewGroup) getView(),R.id.no_search);
        }

        return true;
    }

    @Override
    public void onPause() {
        super.onPause();
        //hide the Android Soft Keyboard
        imm.hideSoftInputFromWindow(searchView.getWindowToken(), 0);
    }


    @Override
    public void onDataNotFound(View view) {
        view.setVisibility(View.GONE);
        int i = 0, len = resultFragments.size() - 1;
        for(; i < len || resultFragments.get(i).isVisible(); i++ );
        if (i == len){
            AppHelpers.showViewById((ViewGroup) getView(),R.id.not_result_found);
           notResultFoundTextView.setText(String.format(Locale.getDefault(), getString(R.string.search_not_result_found_primary), searchView.getQuery().toString() ));
        }
    }

    @Override
    public void onDataFound(View view) {
        view.setVisibility(View.VISIBLE);
    }


}
