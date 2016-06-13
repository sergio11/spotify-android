package com.example.sergio.spotify_angular.events;

import java.util.List;

import kaaes.spotify.webapi.android.models.Artist;

/**
 * Created by sergio on 13/06/2016.
 */
public class FollowedArtistFoundEvent {

    private List<Artist> artists;

    public FollowedArtistFoundEvent(List<Artist> artists) {
        this.artists = artists;
    }

    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }
}
