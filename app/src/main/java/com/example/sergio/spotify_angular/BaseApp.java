package com.example.sergio.spotify_angular;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import com.example.sergio.spotify_angular.events.ApiErrorEvent;
import com.example.sergio.spotify_angular.services.CategoriesService;
import com.example.sergio.spotify_angular.services.PlaylistsService;
import com.example.sergio.spotify_angular.services.UserService;
import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.FontAwesomeIcons;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import kaaes.spotify.webapi.android.SpotifyApi;
import kaaes.spotify.webapi.android.SpotifyService;

/**
 * Created by sergio on 05/05/2016.
 */
public class BaseApp extends Application {

    private UserService userService;
    private CategoriesService categoriesService;
    private PlaylistsService playlistsService;
    private EventBus bus = EventBus.getDefault();

    @Override
    public void onCreate() {
        super.onCreate();
        Iconify.with(new FontAwesomeModule());
    }

    public void initServices(String accessToken) {
        SpotifyApi api = new SpotifyApi();
        api.setAccessToken(accessToken);
        SpotifyService spotify = api.getService();

        userService = new UserService(spotify, bus);
        categoriesService = new CategoriesService(spotify, bus);
        playlistsService = new PlaylistsService(spotify,bus);

        bus.register(userService);
        bus.register(categoriesService);
        bus.register(playlistsService);
        bus.register(this); //listen for "global" events

    }

    @Subscribe
    public void onApiError(ApiErrorEvent event) {
        Toast.makeText(this,event.getError().getMessage(), Toast.LENGTH_LONG).show();
        Log.e("ReaderApp", event.getError().getMessage());
    }


}
