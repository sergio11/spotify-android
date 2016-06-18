package com.example.sergio.spotify_angular.contentproviders;

import android.content.SearchRecentSuggestionsProvider;

/**
 * Created by sergio on 18/06/2016.
 */
public class SearchHistoryContentProvider extends SearchRecentSuggestionsProvider {
    public final static String AUTHORITY = "com.example.sergio.spotify_angular.contentproviders";
    public final static int MODE = DATABASE_MODE_QUERIES;

    public SearchHistoryContentProvider() {
        setupSuggestions(AUTHORITY, MODE);
    }
}
