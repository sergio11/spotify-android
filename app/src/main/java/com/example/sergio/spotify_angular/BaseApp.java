package com.example.sergio.spotify_angular;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import com.example.sergio.spotify_angular.events.ApiErrorEvent;
import com.example.sergio.spotify_angular.events.ProfileLoadedEvent;
import com.example.sergio.spotify_angular.services.AlbumsService;
import com.example.sergio.spotify_angular.services.BaseService;
import com.example.sergio.spotify_angular.services.CategoriesService;
import com.example.sergio.spotify_angular.services.PlaylistsService;
import com.example.sergio.spotify_angular.services.UserService;
import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.FontAwesomeIcons;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;

import kaaes.spotify.webapi.android.SpotifyApi;
import kaaes.spotify.webapi.android.SpotifyService;
import kaaes.spotify.webapi.android.models.UserPrivate;

/**
 * Created by sergio on 05/05/2016.
 */
public class BaseApp extends Application {

    private EventBus bus = EventBus.getDefault();
    private UserPrivate me;
    @Override
    public void onCreate() {
        super.onCreate();
        Iconify.with(new FontAwesomeModule());
        bus.register(this); //listen for "global" events
    }

    public void initServices(String accessToken) {
        SpotifyApi api = new SpotifyApi();
        api.setAccessToken(accessToken);
        SpotifyService spotify = api.getService();

        //Load all app services
        String[] services = getResources().getStringArray(R.array.app_services);
        String packageName = getApplicationContext().getPackageName()+ File.separatorChar + "services" + File.separatorChar;
        for (int i = 0, len = services.length; i < len; i++){
            try {
                String classPath = packageName + services[i];
                bus.register(Class.forName(classPath.replace(File.separatorChar, '.')).getDeclaredConstructor(SpotifyService.class, EventBus.class).newInstance(spotify,bus));
            } catch (Exception e){
                e.printStackTrace();
            }
        }

    }


    @Subscribe
    public void onProfileLoaded(ProfileLoadedEvent event){
        me = event.getUser();

    }

    @Subscribe
    public void onApiError(ApiErrorEvent event) {
        Toast.makeText(this,event.getError().getMessage(), Toast.LENGTH_LONG).show();
        Log.e("ReaderApp", event.getError().getMessage());
    }

    public UserPrivate getMe() {
        return me;
    }
}
