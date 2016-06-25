package com.example.sergio.spotify_angular.fragments.resultsearch;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

/**
 * Created by sergio on 25/06/2016.
 */
public abstract class AbstractShowResultsFragment<T, E extends RecyclerView.LayoutManager> extends AbstractEndlessScrollFragment<T,E> {

    private static final String ARG_TEXT = "text_search";

    protected String text;

    public static AbstractShowResultsFragment newInstance(String text, Class<? extends AbstractShowResultsFragment> classFragment) throws IllegalAccessException, java.lang.InstantiationException {
        AbstractShowResultsFragment fragment = classFragment.newInstance();
        Bundle args = new Bundle();
        args.putString(ARG_TEXT, text);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            text = getArguments().getString(ARG_TEXT);
        }
    }
}
