package com.example.sergio.spotify_angular.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sergio.spotify_angular.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import kaaes.spotify.webapi.android.models.AlbumSimple;

/**
 * Created by sergio on 04/06/2016.
 */
public class AlbumsAdapter extends RecyclerViewBaseAdapter<AlbumSimple,AlbumsAdapter.AlbumViewHolder> {

    public AlbumsAdapter(Context context, List<AlbumSimple> data) {
        super(context, data);
    }

    @Override
    public AlbumViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.album_layout, parent, false);
        return new AlbumViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AlbumViewHolder holder, int position) {
        AlbumSimple album = data.get(position);
        holder.bindToView(album);
    }


    public class AlbumViewHolder extends RecyclerView.ViewHolder{

        private final ImageView photo;
        private final TextView name;
        private final TextView type;

        public AlbumViewHolder(View itemView) {
            super(itemView);
            photo = (ImageView)itemView.findViewById(R.id.album_photo);
            name = (TextView)itemView.findViewById(R.id.album_name);
            type = (TextView)itemView.findViewById(R.id.album_type);
        }

        public void bindToView(final AlbumSimple album){
            Picasso.with(context).load(album.images.get(0).url).into(photo);
            name.setText(album.name);
            type.setText(album.album_type);
        }
    }
}
