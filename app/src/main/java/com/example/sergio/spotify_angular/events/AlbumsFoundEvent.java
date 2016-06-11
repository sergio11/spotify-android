package com.example.sergio.spotify_angular.events;

import java.util.List;

import kaaes.spotify.webapi.android.models.AlbumSimple;

/**
 * Created by sergio on 11/06/2016.
 */
public class AlbumsFoundEvent {

    private List<AlbumSimple> albums;

    public AlbumsFoundEvent(List<AlbumSimple> albums) {
        this.albums = albums;
    }

    public List<AlbumSimple> getAlbums() {
        return albums;
    }

    public void setAlbums(List<AlbumSimple> albums) {
        this.albums = albums;
    }
}
