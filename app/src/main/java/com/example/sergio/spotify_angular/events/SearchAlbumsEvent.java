package com.example.sergio.spotify_angular.events;

import java.util.Map;

/**
 * Created by sergio on 11/06/2016.
 */
public class SearchAlbumsEvent {

    private String text;
    private Map<String, Object> options;

    public SearchAlbumsEvent(String text, Map<String, Object> options) {
        this.text = text;
        this.options = options;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Map<String, Object> getOptions() {
        return options;
    }

    public void setOptions(Map<String, Object> options) {
        this.options = options;
    }
}
