package com.example.sergio.spotify_angular.events;

/**
 * Created by sergio on 04/06/2016.
 */
public class ExplorerMenuItemSelected {

    int id;

    public ExplorerMenuItemSelected(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
