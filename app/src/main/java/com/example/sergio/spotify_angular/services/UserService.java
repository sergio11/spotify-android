package com.example.sergio.spotify_angular.services;

import com.example.sergio.spotify_angular.events.LoadProfileEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import kaaes.spotify.webapi.android.SpotifyService;

public class UserService {

    private SpotifyService service;
    private EventBus bus;

    public UserService(SpotifyService service, EventBus bus) {
        this.service = service;
        this.bus = bus;
    }

    @Subscribe
    public void onLoadProfile(LoadProfileEvent event) {

    }
}

