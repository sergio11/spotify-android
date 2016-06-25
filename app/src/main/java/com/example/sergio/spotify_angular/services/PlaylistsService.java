package com.example.sergio.spotify_angular.services;


import android.content.Context;

import com.example.sergio.spotify_angular.R;
import com.example.sergio.spotify_angular.events.ApiErrorEvent;
import com.example.sergio.spotify_angular.events.CreatePlaylistEvent;
import com.example.sergio.spotify_angular.events.FeaturedPlaylistLoadedEvent;
import com.example.sergio.spotify_angular.events.LoadFeaturedPlaylistEvent;
import com.example.sergio.spotify_angular.events.LoadPlaylistEvent;
import com.example.sergio.spotify_angular.events.LoadPlaylistTracksEvent;
import com.example.sergio.spotify_angular.events.NotFoundPlaylistEvent;
import com.example.sergio.spotify_angular.events.PlaylistLoadedEvent;
import com.example.sergio.spotify_angular.events.PlaylistTracksLoadedEvent;
import com.example.sergio.spotify_angular.events.PlaylistsCreatedEvent;
import com.example.sergio.spotify_angular.events.PlaylistsFoundEvent;
import com.example.sergio.spotify_angular.events.SearchPlaylistEvent;
import com.example.sergio.spotify_angular.utils.AppHelpers;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import kaaes.spotify.webapi.android.SpotifyService;
import kaaes.spotify.webapi.android.models.FeaturedPlaylists;
import kaaes.spotify.webapi.android.models.Pager;
import kaaes.spotify.webapi.android.models.Playlist;
import kaaes.spotify.webapi.android.models.PlaylistTrack;
import kaaes.spotify.webapi.android.models.PlaylistsPager;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by sergio on 14/05/2016.
 */
public class PlaylistsService extends BaseService {


    public PlaylistsService(Context context, SpotifyService service, EventBus bus) {
        super(context, service, bus);
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
                bus.post(new ApiErrorEvent(ApiErrorEvent.Type.ALERT,error.getMessage()));
            }
        });
    }

    @Subscribe
    public void onLoadPlaylist(LoadPlaylistEvent event){
        service.getPlaylist(event.getOwner(), event.getPlaylist(), new Callback<Playlist>() {
            @Override
            public void success(Playlist playlist, Response response) {
                bus.post(new PlaylistLoadedEvent(playlist));
            }

            @Override
            public void failure(RetrofitError error) {
                bus.post(new ApiErrorEvent(ApiErrorEvent.Type.ALERT,error.getMessage()));
            }
        });
    }



    @Subscribe
    public void onLoadPlaylistTracks(LoadPlaylistTracksEvent event){
        service.getPlaylistTracks(event.getOwner(), event.getPlaylist(), new Callback<Pager<PlaylistTrack>>() {
            @Override
            public void success(Pager<PlaylistTrack> playlistTrackPager, Response response) {
                bus.post(new PlaylistTracksLoadedEvent(playlistTrackPager.items));
            }

            @Override
            public void failure(RetrofitError error) {
                bus.post(new ApiErrorEvent(ApiErrorEvent.Type.ALERT,error.getMessage()));
            }
        });
    }

    @Subscribe
    public void onSearchPlaylists(SearchPlaylistEvent event){
        service.searchPlaylists(event.getText(), AppHelpers.deepMerge(options, event.getOptions()), new Callback<PlaylistsPager>() {
            @Override
            public void success(PlaylistsPager playlistsPager, Response response) {
                bus.post(new PlaylistsFoundEvent(playlistsPager.playlists.items));
            }

            @Override
            public void failure(RetrofitError error) {
                ApiErrorEvent errorEvent;
                if (error.getResponse().getStatus() == 400){
                    errorEvent = new ApiErrorEvent(ApiErrorEvent.Type.INFO,context.getString(R.string.search_playlist_status_code_400));
                }else{
                    errorEvent = new ApiErrorEvent(ApiErrorEvent.Type.ALERT,context.getString(R.string.search_playlist_status_code_500));
                }
                bus.post(errorEvent);
                bus.post(new NotFoundPlaylistEvent());
            }
        });
    }

    @Subscribe
    public void onCreatePlaylist(CreatePlaylistEvent event){
        service.createPlaylist(event.getUserId(), event.getOptions(), new Callback<Playlist>() {
            @Override
            public void success(Playlist playlist, Response response) {
                bus.post(new PlaylistsCreatedEvent(playlist));
            }

            @Override
            public void failure(RetrofitError error) {
                bus.post(new ApiErrorEvent(ApiErrorEvent.Type.ALERT,error.getMessage()));
            }
        });
    }


}
