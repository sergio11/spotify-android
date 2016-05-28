package com.example.sergio.spotify_angular.services;


import com.example.sergio.spotify_angular.events.ApiErrorEvent;
import com.example.sergio.spotify_angular.events.FeaturedPlaylistLoadedEvent;
import com.example.sergio.spotify_angular.events.LoadFeaturedPlaylistEvent;
import com.example.sergio.spotify_angular.events.LoadPlaylistTracks;
import com.example.sergio.spotify_angular.events.PlaylistTracksLoadedEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import kaaes.spotify.webapi.android.SpotifyService;
import kaaes.spotify.webapi.android.models.FeaturedPlaylists;
import kaaes.spotify.webapi.android.models.Pager;
import kaaes.spotify.webapi.android.models.PlaylistTrack;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by sergio on 14/05/2016.
 */
public class PlaylistsService extends BaseService {


    public PlaylistsService(SpotifyService service, EventBus bus) {
        super(service, bus);
    }

    @Subscribe
    public void onLoadFeaturedPlaylists(LoadFeaturedPlaylistEvent event){
        service.getFeaturedPlaylists(options,new Callback<FeaturedPlaylists>() {
            @Override
            public void success(FeaturedPlaylists featuredPlaylists, Response response) {
                bus.post(new FeaturedPlaylistLoadedEvent(featuredPlaylists.message,featuredPlaylists.playlists.items));
            }

            @Override
            public void failure(RetrofitError error) {
                bus.post(new ApiErrorEvent(error));
            }
        });
    }

    @Subscribe
    public void onLoadPlaylistTracks(LoadPlaylistTracks event){
        service.getPlaylistTracks(event.getOwner(), event.getPlaylist(), new Callback<Pager<PlaylistTrack>>() {
            @Override
            public void success(Pager<PlaylistTrack> playlistTrackPager, Response response) {
                bus.post(new PlaylistTracksLoadedEvent(playlistTrackPager.items));
            }

            @Override
            public void failure(RetrofitError error) {
                bus.post(new ApiErrorEvent(error));
            }
        });
    }


}
