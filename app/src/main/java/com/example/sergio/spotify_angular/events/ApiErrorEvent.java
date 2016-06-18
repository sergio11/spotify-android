package com.example.sergio.spotify_angular.events;


/**
 * Created by sergio on 14/05/2016.
 */
public class ApiErrorEvent {

    public enum Type{
        ALERT(),INFO(),WARNING();
    }

    private Type type;
    private String message;

    public ApiErrorEvent(Type type, String message) {
        this.type = type;
        this.message = message;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
