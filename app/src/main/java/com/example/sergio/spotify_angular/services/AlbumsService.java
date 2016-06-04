package com.example.sergio.spotify_angular.services;

import com.example.sergio.spotify_angular.events.ApiErrorEvent;
import com.example.sergio.spotify_angular.events.LoadNewReleases;
import com.example.sergio.spotify_angular.events.NewReleasesLoaded;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import kaaes.spotify.webapi.android.SpotifyService;
import kaaes.spotify.webapi.android.models.NewReleases;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by sergio on 04/06/2016.
 */
public class AlbumsService extends BaseService {

    public AlbumsService(SpotifyService service, EventBus bus) {
        super(service, bus);
    }

    @Subscribe
    public void onLoadNewReleases(LoadNewReleases event){
        service.getNewReleases(options, new Callback<NewReleases>() {
            @Override
            public void success(NewReleases newReleases, Response response) {
                bus.post(new NewReleasesLoaded(newReleases.albums.items));
            }

            @Override
            public void failure(RetrofitError error) {
                bus.post(new ApiErrorEvent(error));
            }
        });

    }


}
