package com.example.sergio.spotify_angular.events;

import java.util.List;
import kaaes.spotify.webapi.android.models.AlbumSimple;

/**
 * Created by sergio on 04/06/2016.
 */
public class NewReleasesLoaded {

    private List<AlbumSimple> albums;

    public NewReleasesLoaded(List<AlbumSimple> albums) {
        this.albums = albums;
    }

    public List<AlbumSimple> getAlbums() {
        return albums;
    }
}
