package com.example.sergio.spotify_angular.services;

import com.example.sergio.spotify_angular.events.AlbumsFoundEvent;
import com.example.sergio.spotify_angular.events.ApiErrorEvent;
import com.example.sergio.spotify_angular.events.LoadNewReleases;
import com.example.sergio.spotify_angular.events.NewReleasesLoaded;
import com.example.sergio.spotify_angular.events.SearchAlbumsEvent;
import com.example.sergio.spotify_angular.utils.AppHelpers;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import kaaes.spotify.webapi.android.SpotifyService;
import kaaes.spotify.webapi.android.models.AlbumsPager;
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

    @Subscribe
    public void onSearchAlbums(SearchAlbumsEvent event){
        service.searchAlbums(event.getText(), AppHelpers.deepMerge(options, event.getOptions()), new Callback<AlbumsPager>() {
            @Override
            public void success(AlbumsPager albumsPager, Response response) {
                bus.post(new AlbumsFoundEvent(albumsPager.albums.items));
            }

            @Override
            public void failure(RetrofitError error) {
                bus.post(new ApiErrorEvent(error));
            }
        });
    }


}
