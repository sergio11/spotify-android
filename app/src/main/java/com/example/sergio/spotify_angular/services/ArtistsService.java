package com.example.sergio.spotify_angular.services;

import com.example.sergio.spotify_angular.events.ApiErrorEvent;
import com.example.sergio.spotify_angular.events.ArtistsFoundEvent;
import com.example.sergio.spotify_angular.events.NotFoundArtistEvent;
import com.example.sergio.spotify_angular.events.SearchArtistsEvent;
import com.example.sergio.spotify_angular.utils.AppHelpers;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import kaaes.spotify.webapi.android.SpotifyService;
import kaaes.spotify.webapi.android.models.ArtistsPager;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by sergio on 11/06/2016.
 */
public class ArtistsService extends BaseService {

    public ArtistsService(SpotifyService service, EventBus bus) {
        super(service, bus);
    }

    @Subscribe
    public void onSearchArtists(SearchArtistsEvent event){

        service.searchArtists(event.getText(), AppHelpers.deepMerge(options, event.getOptions()), new Callback<ArtistsPager>() {
            @Override
            public void success(ArtistsPager artistsPager, Response response) {
                bus.post(new ArtistsFoundEvent(artistsPager.artists.items));
            }

            @Override
            public void failure(RetrofitError error) {
                bus.post(new ApiErrorEvent(error));
                bus.post(new NotFoundArtistEvent());
            }
        });
    }
}
