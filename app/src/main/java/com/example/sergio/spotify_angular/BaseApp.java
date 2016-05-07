package com.example.sergio.spotify_angular;

import android.app.Application;

import kaaes.spotify.webapi.android.SpotifyApi;
import kaaes.spotify.webapi.android.SpotifyService;

/**
 * Created by sergio on 05/05/2016.
 */
public class BaseApp extends Application {

    private SpotifyService spotify;

    public void initSpotifyService(String accessToken) {
        SpotifyApi api = new SpotifyApi();
        api.setAccessToken(accessToken);
        spotify = api.getService();
    }

    public SpotifyService getSpotify() {
        return spotify;
    }
}
