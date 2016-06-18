package com.example.sergio.spotify_angular;

import android.app.Application;
import android.content.Context;

import com.example.sergio.spotify_angular.events.ProfileLoadedEvent;

import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.File;

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
                bus.register(Class.forName(classPath.replace(File.separatorChar, '.')).getDeclaredConstructor(Context.class,SpotifyService.class, EventBus.class).newInstance(this,spotify,bus));
            } catch (Exception e){
                e.printStackTrace();
            }
        }

    }


    @Subscribe
    public void onProfileLoaded(ProfileLoadedEvent event){
        me = event.getUser();

    }



    public UserPrivate getMe() {
        return me;
    }
}
