package com.example.sergio.spotify_angular.events;

import android.text.TextUtils;

import java.util.List;

/**
 * Created by sergio on 28/05/2016.
 */
public class AreFollowingPlaylistEvent {

    private String owner;
    private String playlist;
    private List<String> users;

    public AreFollowingPlaylistEvent(String owner, String playlist, List<String> users) {
        this.owner = owner;
        this.playlist = playlist;
        this.users = users;
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

    public List<String> getUsers(){
        return this.users;
    }

    public String getJoinUsers() {
        return TextUtils.join(",",users);
    }

    public void setUsers(List<String> users) {
        this.users = users;
    }
}
