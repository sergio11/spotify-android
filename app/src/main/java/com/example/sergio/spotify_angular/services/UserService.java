package com.example.sergio.spotify_angular.services;

import com.example.sergio.spotify_angular.events.ApiErrorEvent;
import com.example.sergio.spotify_angular.events.LoadProfileEvent;
import com.example.sergio.spotify_angular.events.ProfileLoadedEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import kaaes.spotify.webapi.android.SpotifyService;
import kaaes.spotify.webapi.android.models.UserPrivate;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class UserService extends BaseService {

    public UserService(SpotifyService service, EventBus bus) {
        super(service, bus);
    }

    @Subscribe
    public void onLoadProfile(LoadProfileEvent event) {
        service.getMe(new Callback<UserPrivate>() {
            @Override
            public void success(UserPrivate userPrivate, Response response) {
                bus.post(new ProfileLoadedEvent(userPrivate));
            }

            @Override
            public void failure(RetrofitError error) {
                bus.post(new ApiErrorEvent(error));
            }
        });
    }
}

