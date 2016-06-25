package com.example.sergio.spotify_angular.services;

import android.content.Context;

import com.example.sergio.spotify_angular.R;
import com.example.sergio.spotify_angular.events.ApiErrorEvent;
import com.example.sergio.spotify_angular.events.AreFollowingPlaylistCheckedEvent;
import com.example.sergio.spotify_angular.events.AreFollowingPlaylistEvent;
import com.example.sergio.spotify_angular.events.FollowPlaylistEvent;
import com.example.sergio.spotify_angular.events.FollowPlaylistSuccessEvent;
import com.example.sergio.spotify_angular.events.FollowedArtistFoundEvent;
import com.example.sergio.spotify_angular.events.GetFollowedArtistsEvent;
import com.example.sergio.spotify_angular.events.LoadMyPlaylistsEvent;
import com.example.sergio.spotify_angular.events.LoadProfileEvent;
import com.example.sergio.spotify_angular.events.MyPlaylistsLoadedEvent;
import com.example.sergio.spotify_angular.events.NotFoundPlaylistEvent;
import com.example.sergio.spotify_angular.events.ProfileLoadedEvent;
import com.example.sergio.spotify_angular.events.UnFollowPlaylistEvent;
import com.example.sergio.spotify_angular.events.UnFollowPlaylistSuccessEvent;
import com.example.sergio.spotify_angular.utils.AppHelpers;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;
import java.util.Map;

import kaaes.spotify.webapi.android.SpotifyService;
import kaaes.spotify.webapi.android.models.ArtistsCursorPager;
import kaaes.spotify.webapi.android.models.Pager;
import kaaes.spotify.webapi.android.models.PlaylistSimple;
import kaaes.spotify.webapi.android.models.Result;
import kaaes.spotify.webapi.android.models.UserPrivate;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class UserService extends BaseService {


    public UserService(Context context, SpotifyService service, EventBus bus) {
        super(context, service, bus);
    }

    @Subscribe
    public void onFollowPlaylist(FollowPlaylistEvent event){
        service.followPlaylist(event.getPlaylist(), event.getOwner(), new Callback<Result>() {
            @Override
            public void success(Result result, Response response) {
                bus.post(new FollowPlaylistSuccessEvent(result));
            }

            @Override
            public void failure(RetrofitError error) {
                bus.post(new ApiErrorEvent(ApiErrorEvent.Type.ALERT,error.getMessage()));
            }
        });
    }

    @Subscribe
    public void onUnFollowPlaylist(UnFollowPlaylistEvent event){
        service.unfollowPlaylist(event.getOwner(), event.getPlaylist(), new Callback<Result>() {
            @Override
            public void success(Result result, Response response) {
                bus.post(new UnFollowPlaylistSuccessEvent(result));
            }

            @Override
            public void failure(RetrofitError error) {
                bus.post(new ApiErrorEvent(ApiErrorEvent.Type.ALERT,error.getMessage()));
            }
        });
    }

    @Subscribe
    public void onAreFollowingPlaylist(final AreFollowingPlaylistEvent event){
        service.areFollowingPlaylist(event.getOwner(), event.getPlaylist(), event.getJoinUsers(), new Callback<boolean[]>() {
            @Override
            public void success(boolean[] booleen, Response response) {
                Map<String, Boolean> result = new HashMap<String, Boolean>();
                for (int i = 0, len =  event.getUsers().size(); i < len; i++){
                    result.put(event.getUsers().get(i),booleen[i]);
                }
                bus.post(new AreFollowingPlaylistCheckedEvent(result));
            }

            @Override
            public void failure(RetrofitError error) {
                bus.post(new ApiErrorEvent(ApiErrorEvent.Type.ALERT,error.getMessage()));
            }
        });
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
                bus.post(new ApiErrorEvent(ApiErrorEvent.Type.ALERT,error.getMessage()));
            }
        });
    }

    @Subscribe
    public void onLoadMyPlaylists(LoadMyPlaylistsEvent event){
        service.getMyPlaylists( AppHelpers.deepMerge(options, event.getOptions()), new Callback<Pager<PlaylistSimple>>() {
            @Override
            public void success(Pager<PlaylistSimple> playlistSimplePager, Response response) {
                bus.post(new MyPlaylistsLoadedEvent(playlistSimplePager.items));
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
    public void onGetFollowedArtists(GetFollowedArtistsEvent event){
        service.getFollowedArtists(options, new Callback<ArtistsCursorPager>() {
            @Override
            public void success(ArtistsCursorPager artistsCursorPager, Response response) {
                bus.post(new FollowedArtistFoundEvent(artistsCursorPager.artists.items));
            }

            @Override
            public void failure(RetrofitError error) {
                bus.post(new ApiErrorEvent(ApiErrorEvent.Type.ALERT,error.getMessage()));
            }
        });
    }
}

