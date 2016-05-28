package com.example.sergio.spotify_angular.events;

import kaaes.spotify.webapi.android.models.Playlist;

/**
 * Created by sergio on 28/05/2016.
 */
public class PlaylistLoadedEvent {

    private Playlist playlist;

    public PlaylistLoadedEvent(Playlist playlist) {
        this.playlist = playlist;
    }

    public Playlist getPlaylist() {
        return playlist;
    }

    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
    }
}
