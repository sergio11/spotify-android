package com.example.sergio.spotify_angular.events;

import retrofit.RetrofitError;

/**
 * Created by sergio on 14/05/2016.
 */
public class ApiErrorEvent {

    private RetrofitError error;

    public ApiErrorEvent(RetrofitError error) {
        this.error = error;
    }

    public RetrofitError getError() {
        return error;
    }
}
