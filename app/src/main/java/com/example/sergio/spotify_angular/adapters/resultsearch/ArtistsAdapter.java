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
import com.example.sergio.spotify_angular.adapters.RecyclerViewBaseAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

import kaaes.spotify.webapi.android.models.Artist;

/**
 * Created by sergio on 11/06/2016.
 */
public class ArtistsAdapter extends RecyclerViewBaseAdapter<Artist, RecyclerView.ViewHolder> {

    private final int VIEW_TYPE_ITEM = 1;
    private final int VIEW_TYPE_PROGRESSBAR = 0;
    private boolean isFooterEnabled = false;

    public ArtistsAdapter(Context context, List<Artist> data){
        super(context, data);
    }


    @Override
    public int getItemCount() {
        return  (isFooterEnabled) ? data.size() + 1 : data.size();
    }

    @Override
    public int getItemViewType(int position) {
        return (isFooterEnabled && position >= data.size() ) ? VIEW_TYPE_PROGRESSBAR : VIEW_TYPE_ITEM;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(context).inflate(R.layout.artists_result_search_item, parent, false);
            vh = new ArtistViewHolder(view);
        }else{
            View view = LayoutInflater.from(context).inflate(R.layout.progress_item, parent, false);
            vh = new ProgressViewHolder(view);
        }

        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof  ArtistViewHolder){
            ((ArtistViewHolder)holder).bind(data.get(position));
            bindToListener(holder);
        }

    }

    /**
     * Enable or disable footer (Default is true)
     *
     * @param isEnabled boolean to turn on or off footer.
     */
    public void enableFooter(boolean isEnabled){
        this.isFooterEnabled = isEnabled;
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

    public  class ProgressViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        public ProgressViewHolder(View v) {
            super(v);
            progressBar = (ProgressBar) v.findViewById(R.id.progressBar);
        }
    }
}
