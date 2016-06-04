package com.example.sergio.spotify_angular.models;

/**
 * Created by sergio on 13/05/2016.
 */
public class MenuAppItem {

    private int id;
    private String icon;
    private String text;

    public MenuAppItem(int id, String icon, String text) {
        this.id = id;
        this.icon = icon;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
