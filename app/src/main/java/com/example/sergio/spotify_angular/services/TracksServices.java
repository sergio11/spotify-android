package com.example.sergio.spotify_angular.services;

import android.content.Context;

import com.example.sergio.spotify_angular.events.ApiErrorEvent;
import com.example.sergio.spotify_angular.events.SearchTracksEvent;
import com.example.sergio.spotify_angular.events.TracksFoundEvent;
import com.example.sergio.spotify_angular.utils.AppHelpers;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import kaaes.spotify.webapi.android.SpotifyService;
import kaaes.spotify.webapi.android.models.TracksPager;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by sergio on 11/06/2016.
 */
public class TracksServices extends BaseService {


    public TracksServices(Context context, SpotifyService service, EventBus bus) {
        super(context, service, bus);
    }

    @Subscribe
    public void onSearchTracks(SearchTracksEvent event){
        service.searchTracks(event.getText(), AppHelpers.deepMerge(options, event.getOptions()), new Callback<TracksPager>() {
            @Override
            public void success(TracksPager tracksPager, Response response) {
                bus.post(new TracksFoundEvent(tracksPager.tracks.items));
            }

            @Override
            public void failure(RetrofitError error) {
                bus.post(new ApiErrorEvent(ApiErrorEvent.Type.ALERT,error.getMessage()));
            }
        });
    }
}
