package com.example.sergio.spotify_angular.events;

import java.util.List;

import kaaes.spotify.webapi.android.models.Category;

/**
 * Created by sergio on 14/05/2016.
 */
public class CategoriesLoadedEvent {

    private List<Category> categories;

    public CategoriesLoadedEvent(List<Category> categories) {
        this.categories = categories;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
