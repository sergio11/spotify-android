package com.example.sergio.spotify_angular.events;

import kaaes.spotify.webapi.android.models.Result;

/**
 * Created by sergio on 28/05/2016.
 */
public class FollowPlaylistSuccessEvent {

    private Result result;

    public FollowPlaylistSuccessEvent(Result result) {
        this.result = result;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }
}
