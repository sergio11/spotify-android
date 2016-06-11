package com.example.sergio.spotify_angular.adapters;

import android.content.Context;
import android.view.View;

import com.example.sergio.spotify_angular.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import kaaes.spotify.webapi.android.models.Artist;

/**
 * Created by sergio on 11/06/2016.
 */
public class ArtistsSearchResultAdapter extends SearchResultAbstractAdapter<Artist,ArtistsSearchResultAdapter.ArtistViewHolder> {

    public ArtistsSearchResultAdapter(Context context, List<Artist> data){
        super(context, data);
    }

    @Override
    protected int getLayout() {
        return R.layout.artists_search_result_item;
    }

    @Override
    protected ArtistViewHolder createViewHolder(View itemView) {
        return new ArtistViewHolder(itemView);
    }


    public class ArtistViewHolder extends SearchResultAbstractAdapter<Artist, ArtistsSearchResultAdapter.ArtistViewHolder>.SearchResultViewHolder{
        public ArtistViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void bind(Artist artist) {
            //load image
            if (artist.images != null && artist.images.size() > 0)
                Picasso.with(context).load(artist.images.get(0).url).into(photo);
            //set name
            title.setText(artist.name);
            /*if (artist.followers != null)
                subtitle.setText(artist.followers.total);*/
        }


    }
}
