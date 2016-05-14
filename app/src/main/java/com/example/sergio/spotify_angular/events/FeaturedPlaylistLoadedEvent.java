package com.example.sergio.spotify_angular.events;

import java.util.List;

import kaaes.spotify.webapi.android.models.PlaylistSimple;

/**
 * Created by sergio on 14/05/2016.
 */
public class FeaturedPlaylistLoadedEvent {

    private String message;
    private List<PlaylistSimple> playlist;

    public FeaturedPlaylistLoadedEvent(String message, List<PlaylistSimple> playlist) {
        this.message = message;
        this.playlist = playlist;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<PlaylistSimple> getPlaylist() {
        return playlist;
    }

    public void setPlaylist(List<PlaylistSimple> playlist) {
        this.playlist = playlist;
    }
}
