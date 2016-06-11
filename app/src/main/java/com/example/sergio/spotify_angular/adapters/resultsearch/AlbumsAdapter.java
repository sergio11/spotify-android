package com.example.sergio.spotify_angular.adapters.resultsearch;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sergio.spotify_angular.R;
import com.example.sergio.spotify_angular.adapters.RecyclerViewBaseAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

import kaaes.spotify.webapi.android.models.AlbumSimple;

/**
 * Created by sergio on 11/06/2016.
 */
public class AlbumsAdapter extends RecyclerViewBaseAdapter<AlbumSimple, AlbumsAdapter.AlbumViewHolder> {


    public AlbumsAdapter(Context context, List<AlbumSimple> data) {
        super(context, data);
    }


    @Override
    public AlbumViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.album_result_search_item, parent, false);
        return new AlbumViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AlbumViewHolder holder, int position) {
        holder.bind(data.get(position));
        bindToListener(holder);
    }

    public class AlbumViewHolder extends RecyclerView.ViewHolder{

        protected final ImageView photo;
        protected final TextView title;
        protected final TextView subtitle;

        public AlbumViewHolder(View itemView) {
            super(itemView);
            photo = (ImageView)itemView.findViewById(R.id.search_result_photo);
            title = (TextView)itemView.findViewById(R.id.search_result_title);
            title.setSelected(true);
            subtitle = (TextView)itemView.findViewById(R.id.search_result_subtitle);
        }

        public void bind(AlbumSimple albumSimple) {

            if (albumSimple.images != null && albumSimple.images.size() > 0)
                Picasso.with(context).load(albumSimple.images.get(0).url).into(photo);

            title.setText(albumSimple.name);
            subtitle.setText(albumSimple.album_type);
        }


    }

}
