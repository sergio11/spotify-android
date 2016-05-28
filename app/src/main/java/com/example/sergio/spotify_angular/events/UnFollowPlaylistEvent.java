package com.example.sergio.spotify_angular.events;

/**
 * Created by sergio on 28/05/2016.
 */
public class UnFollowPlaylistEvent {

    private String owner;
    private String playlist;

    public UnFollowPlaylistEvent(String owner, String playlist) {
        this.owner = owner;
        this.playlist = playlist;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getPlaylist() {
        return playlist;
    }

    public void setPlaylist(String playlist) {
        this.playlist = playlist;
    }
}
