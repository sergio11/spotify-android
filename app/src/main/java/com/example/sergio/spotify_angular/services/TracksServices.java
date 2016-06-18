package com.example.sergio.spotify_angular.services;

import android.content.Context;

import com.example.sergio.spotify_angular.R;
import com.example.sergio.spotify_angular.events.ApiErrorEvent;
import com.example.sergio.spotify_angular.events.NotFoundTracksEvent;
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
                ApiErrorEvent errorEvent;
                if (error.getResponse().getStatus() == 400){
                    errorEvent = new ApiErrorEvent(ApiErrorEvent.Type.INFO,context.getString(R.string.search_tracks_status_code_400));
                }else{
                    errorEvent = new ApiErrorEvent(ApiErrorEvent.Type.ALERT,context.getString(R.string.search_tracks_status_code_500));
                }
                bus.post(errorEvent);
                bus.post(new NotFoundTracksEvent());
            }
        });
    }
}
