package com.example.sergio.spotify_angular.events;

/**
 * Created by sergio on 18/06/2016.
 */
public class SeeAllResultsEvent {

    public enum ResultTypes{
        ARTISTS(),ALBUMS();
    }

    private String title;
    private String text;
    private ResultTypes type;

    public SeeAllResultsEvent(String title, String text, ResultTypes type) {
        this.title = title;
        this.text = text;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ResultTypes getType() {
        return type;
    }

    public void setType(ResultTypes type) {
        this.type = type;
    }
}
