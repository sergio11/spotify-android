package com.example.sergio.spotify_angular.events;

import java.util.Map;

/**
 * Created by sergio on 28/05/2016.
 */
public class AreFollowingPlaylistCheckedEvent {

    private Map<String,Boolean> result;

    public AreFollowingPlaylistCheckedEvent(Map<String, Boolean> result) {
        this.result = result;
    }

    public Map<String, Boolean> getResult() {
        return result;
    }

    public void setResult(Map<String, Boolean> result) {
        this.result = result;
    }
}
