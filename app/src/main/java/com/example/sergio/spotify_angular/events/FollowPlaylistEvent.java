package com.example.sergio.spotify_angular.events;

/**
 * Created by sergio on 28/05/2016.
 */
public class FollowPlaylistEvent {

    private String playlist;
    private String owner;

    public FollowPlaylistEvent(String playlist, String owner) {
        this.playlist = playlist;
        this.owner = owner;
    }

    public String getPlaylist() {
        return playlist;
    }

    public void setPlaylist(String playlist) {
        this.playlist = playlist;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
