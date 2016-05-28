package com.example.sergio.spotify_angular.events;

import java.util.List;

import kaaes.spotify.webapi.android.models.PlaylistTrack;

/**
 * Created by sergio on 28/05/2016.
 */
public class PlaylistTracksLoadedEvent {

    private List<PlaylistTrack> items;

    public PlaylistTracksLoadedEvent(List<PlaylistTrack> items) {
        this.items = items;
    }

    public List<PlaylistTrack> getItems() {
        return items;
    }

    public void setItems(List<PlaylistTrack> items) {
        this.items = items;
    }
}
