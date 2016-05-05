package com.example.sergio.spotify_angular.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import kaaes.spotify.webapi.android.models.PlaylistSimple;

/**
 * Created by sergio on 05/05/2016.
 */
public class PlayListAdapter extends GridViewAdapter<PlaylistSimple> {

    public PlayListAdapter(Context context, List<PlaylistSimple> data) {
        super(context, data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
