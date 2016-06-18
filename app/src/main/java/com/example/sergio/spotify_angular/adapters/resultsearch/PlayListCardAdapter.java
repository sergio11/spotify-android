package com.example.sergio.spotify_angular.adapters.resultsearch;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sergio.spotify_angular.R;
import com.example.sergio.spotify_angular.adapters.RecyclerViewBaseAdapter;

import java.util.List;

import kaaes.spotify.webapi.android.models.PlaylistSimple;

/**
 * Created by sergio on 18/06/2016.
 */
public class PlayListCardAdapter extends PlaylistAdapter {

    public PlayListCardAdapter(Context context, List<PlaylistSimple> data) {
        super(context, data);
    }

    @Override
    protected RecyclerViewBaseAdapter.BaseViewHolder getViewHolderItem(ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.playlist_card_item, parent, false);
        return new PlaylistViewHolder(view);
    }
}
