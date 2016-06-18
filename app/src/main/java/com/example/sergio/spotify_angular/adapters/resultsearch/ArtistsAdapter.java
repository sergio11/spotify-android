package com.example.sergio.spotify_angular.adapters.resultsearch;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.sergio.spotify_angular.R;
import com.example.sergio.spotify_angular.adapters.ProgressLoadedAdapter;
import com.example.sergio.spotify_angular.adapters.RecyclerViewBaseAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

import kaaes.spotify.webapi.android.models.Artist;

/**
 * Created by sergio on 11/06/2016.
 */
public class ArtistsAdapter extends ProgressLoadedAdapter<Artist, ArtistsAdapter.ArtistViewHolder> {


    public ArtistsAdapter(Context context, List<Artist> data){
        super(context, data);
    }

    @Override
    protected RecyclerView.ViewHolder getViewHolderItem(ViewGroup parent) {
         View view = LayoutInflater.from(context).inflate(R.layout.artists_result_search_item, parent, false);
         return new ArtistViewHolder(view);
    }

    @Override
    protected void bind(ArtistViewHolder holder, int position) {
        holder.bind(data.get(position));
        bindToListener(holder);
    }


    public class ArtistViewHolder extends RecyclerView.ViewHolder{

        protected final ImageView photo;
        protected final TextView title;
        protected final TextView subtitle;

        public ArtistViewHolder(View itemView) {
            super(itemView);
            photo = (ImageView)itemView.findViewById(R.id.search_result_photo);
            title = (TextView)itemView.findViewById(R.id.search_result_title);
            title.setSelected(true);
            subtitle = (TextView)itemView.findViewById(R.id.search_result_subtitle);
        }

        public void bind(Artist artist) {
            //search image
            if (artist.images != null && artist.images.size() > 0)
                Picasso.with(context).load(artist.images.get(0).url).into(photo);
            //set name
            title.setText(artist.name);
            /*if (artist.followers != null)
                subtitle.setText(artist.followers.total);*/
        }


    }


}
