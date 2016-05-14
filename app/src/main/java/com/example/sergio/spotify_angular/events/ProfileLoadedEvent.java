package com.example.sergio.spotify_angular.events;

import kaaes.spotify.webapi.android.models.UserPrivate;

/**
 * Created by sergio on 14/05/2016.
 */
public class ProfileLoadedEvent {

    private UserPrivate user;

    public ProfileLoadedEvent(UserPrivate user) {
        this.user = user;
    }

    public UserPrivate getUser() {
        return user;
    }

    public void setUser(UserPrivate user) {
        this.user = user;
    }
}
