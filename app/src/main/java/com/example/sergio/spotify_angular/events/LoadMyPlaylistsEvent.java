package com.example.sergio.spotify_angular.events;

import java.util.Map;

/**
 * Created by sergio on 28/05/2016.
 */
public class LoadMyPlaylistsEvent {

    private Map<String, Object> options;

    public LoadMyPlaylistsEvent(Map<String, Object> options) {
        this.options = options;
    }

    public Map<String, Object> getOptions() {
        return options;
    }

    public void setOptions(Map<String, Object> options) {
        this.options = options;
    }
}
