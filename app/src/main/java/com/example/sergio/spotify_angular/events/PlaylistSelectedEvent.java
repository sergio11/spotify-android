package com.example.sergio.spotify_angular.events;

import kaaes.spotify.webapi.android.models.PlaylistSimple;

/**
 * Created by sergio on 21/05/2016.
 */
public class PlaylistSelectedEvent {

    private PlaylistSimple playlist;

    public PlaylistSelectedEvent(PlaylistSimple playlist) {
        this.playlist = playlist;
    }

    public PlaylistSimple getPlaylist() {
        return playlist;
    }

    public void setPlaylist(PlaylistSimple playlist) {
        this.playlist = playlist;
    }
}
