package com.example.sergio.spotify_angular.events;

import java.util.List;

import kaaes.spotify.webapi.android.models.PlaylistSimple;

/**
 * Created by sergio on 11/06/2016.
 */
public class PlaylistsFoundEvent {

    private List<PlaylistSimple> playlists;

    public PlaylistsFoundEvent(List<PlaylistSimple> playlists) {
        this.playlists = playlists;
    }

    public List<PlaylistSimple> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<PlaylistSimple> playlists) {
        this.playlists = playlists;
    }
}
