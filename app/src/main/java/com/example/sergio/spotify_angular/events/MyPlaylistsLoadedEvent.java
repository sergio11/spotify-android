package com.example.sergio.spotify_angular.events;

import java.util.List;

import kaaes.spotify.webapi.android.models.PlaylistSimple;

/**
 * Created by sergio on 28/05/2016.
 */
public class MyPlaylistsLoadedEvent {

    private List<PlaylistSimple> items;

    public MyPlaylistsLoadedEvent(List<PlaylistSimple> items) {
        this.items = items;
    }

    public List<PlaylistSimple> getItems() {
        return items;
    }

    public void setItems(List<PlaylistSimple> items) {
        this.items = items;
    }
}
