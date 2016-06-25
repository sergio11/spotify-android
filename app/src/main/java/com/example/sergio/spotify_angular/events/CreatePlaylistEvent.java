package com.example.sergio.spotify_angular.events;

import java.util.Map;

/**
 * Created by sergio on 25/06/2016.
 */
public class CreatePlaylistEvent {

    private String userId;
    private Map<String, Object> options;

    public CreatePlaylistEvent(String userId, Map<String, Object> options) {
        this.userId = userId;
        this.options = options;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Map<String, Object> getOptions() {
        return options;
    }

    public void setOptions(Map<String, Object> options) {
        this.options = options;
    }
}
