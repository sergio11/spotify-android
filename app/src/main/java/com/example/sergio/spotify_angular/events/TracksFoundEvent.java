package com.example.sergio.spotify_angular.events;

import java.util.List;

import kaaes.spotify.webapi.android.models.Track;

/**
 * Created by sergio on 11/06/2016.
 */
public class TracksFoundEvent {

    private List<Track> tracks;

    public TracksFoundEvent(List<Track> tracks) {
        this.tracks = tracks;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }
}
