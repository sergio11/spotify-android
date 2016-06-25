package com.example.sergio.spotify_angular.events;

/**
 * Created by sergio on 04/06/2016.
 */
public class MenuItemSelected {

    int id;

    public MenuItemSelected(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
