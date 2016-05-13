package com.example.sergio.spotify_angular;

import android.app.Application;

import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.FontAwesomeIcons;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

import kaaes.spotify.webapi.android.SpotifyApi;
import kaaes.spotify.webapi.android.SpotifyService;

/**
 * Created by sergio on 05/05/2016.
 */
public class BaseApp extends Application {

    private SpotifyService spotify;

    @Override
    public void onCreate() {
        super.onCreate();
        Iconify.with(new FontAwesomeModule());
    }

    public void initSpotifyService(String accessToken) {
        SpotifyApi api = new SpotifyApi();
        api.setAccessToken(accessToken);
        spotify = api.getService();
    }

    public SpotifyService getSpotify() {
        return spotify;
    }
}
