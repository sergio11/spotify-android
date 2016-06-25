package com.example.sergio.spotify_angular.events;

import kaaes.spotify.webapi.android.models.Playlist;

/**
 * Created by sergio on 25/06/2016.
 */
public class PlaylistsCreatedEvent {

    private Playlist playlist;

    public PlaylistsCreatedEvent(Playlist playlist) {
        this.playlist = playlist;
    }

    public Playlist getPlaylist() {
        return playlist;
    }

    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
    }
}
