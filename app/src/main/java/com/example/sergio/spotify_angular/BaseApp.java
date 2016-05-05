package com.example.sergio.spotify_angular;

import android.app.Application;

import kaaes.spotify.webapi.android.SpotifyApi;
import kaaes.spotify.webapi.android.SpotifyService;

/**
 * Created by sergio on 05/05/2016.
 */
public class BaseApp extends Application {

    private final static String TOKEN = "c65cbfadaf724c3a89034d5672ae023a";

    private SpotifyService spotify;

    @Override
    public void onCreate() {
        super.onCreate();
        SpotifyApi api = new SpotifyApi();
        api.setAccessToken(BaseApp.TOKEN);
        spotify = api.getService();
    }

    public SpotifyService getSpotify() {
        return spotify;
    }
}
