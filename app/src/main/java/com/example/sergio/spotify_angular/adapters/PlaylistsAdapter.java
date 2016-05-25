package com.example.sergio.spotify_angular.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sergio.spotify_angular.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import kaaes.spotify.webapi.android.models.PlaylistSimple;

/**
 * Created by sergio on 07/05/2016.
 */
public class PlaylistsAdapter extends RecyclerViewBaseAdapter<PlaylistSimple> {

    public PlaylistsAdapter(Context context, List<PlaylistSimple> data) {
        super(context, data);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.playlist_layout, parent, false);
        return new FeaturedPlaylistsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        PlaylistSimple playlist = data.get(position);
        FeaturedPlaylistsViewHolder playlistsViewHolder = (FeaturedPlaylistsViewHolder)holder;
        playlistsViewHolder.setName(playlist.name);
        bindToListener(playlistsViewHolder);
        Picasso.with(context).load(playlist.images.get(0).url).placeholder(R.drawable.ic_playlist).into(playlistsViewHolder.getImage());
    }



    static class FeaturedPlaylistsViewHolder extends RecyclerView.ViewHolder{

        private ImageView image;
        private TextView name;

        public FeaturedPlaylistsViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.playlist_photo);
            name = (TextView) itemView.findViewById(R.id.playlist_name);
        }

        public ImageView getImage() {
            return image;
        }


        public TextView getName() {
            return name;
        }

        public void setName(String name) {
            this.name.setText(name);
        }


    }
}
