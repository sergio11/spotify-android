package com.example.sergio.spotify_angular.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.sergio.spotify_angular.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import kaaes.spotify.webapi.android.models.AlbumSimple;

/**
 * Created by sergio on 11/06/2016.
 */
public class AlbumsSearchResultAdapter extends SearchResultAbstractAdapter<AlbumSimple, AlbumsSearchResultAdapter.AlbumViewHolder> {


    public AlbumsSearchResultAdapter(Context context, List<AlbumSimple> data) {
        super(context, data);
    }

    @Override
    protected int getLayout() {
        return R.layout.album_search_result_item;
    }

    @Override
    protected AlbumViewHolder createViewHolder(View itemView) {
        return new AlbumViewHolder(itemView);
    }

    public class AlbumViewHolder extends SearchResultAbstractAdapter<AlbumSimple, AlbumsSearchResultAdapter.AlbumViewHolder>.SearchResultViewHolder{


        public AlbumViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void bind(AlbumSimple albumSimple) {

            if (albumSimple.images != null && albumSimple.images.size() > 0)
                Picasso.with(context).load(albumSimple.images.get(0).url).into(photo);

            title.setText(albumSimple.name);
            subtitle.setText(albumSimple.album_type);
        }


    }

}
