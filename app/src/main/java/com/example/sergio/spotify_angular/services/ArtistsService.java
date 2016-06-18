package com.example.sergio.spotify_angular.services;

import android.content.Context;

import com.example.sergio.spotify_angular.R;
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


    public ArtistsService(Context context, SpotifyService service, EventBus bus) {
        super(context, service, bus);
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
                ApiErrorEvent errorEvent;
                if (error.getResponse().getStatus() == 400){
                    errorEvent = new ApiErrorEvent(ApiErrorEvent.Type.INFO,context.getString(R.string.search_artist_status_code_400));
                }else{
                    errorEvent = new ApiErrorEvent(ApiErrorEvent.Type.ALERT,context.getString(R.string.search_artist_status_code_500));
                }
                bus.post(errorEvent);
                bus.post(new NotFoundArtistEvent());
            }
        });
    }
}
