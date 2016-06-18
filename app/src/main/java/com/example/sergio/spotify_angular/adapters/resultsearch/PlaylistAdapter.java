package com.example.sergio.spotify_angular.adapters.resultsearch;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sergio.spotify_angular.R;
import com.example.sergio.spotify_angular.adapters.RecyclerViewBaseAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

import kaaes.spotify.webapi.android.models.PlaylistSimple;

/**
 * Created by sergio on 11/06/2016.
 */
public class PlaylistAdapter extends RecyclerViewBaseAdapter<PlaylistSimple, PlaylistAdapter.PlaylistViewHolder> {

    public PlaylistAdapter(Context context, List<PlaylistSimple> data) {
        super(context, data);
    }

    @Override
    public PlaylistViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.playlist_result_search_item, parent, false);
        return new PlaylistViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PlaylistViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    public class PlaylistViewHolder extends RecyclerViewBaseAdapter<PlaylistSimple, PlaylistAdapter.PlaylistViewHolder>.BaseViewHolder<PlaylistSimple>{

        protected final ImageView photo;
        protected final TextView title;
        protected final TextView subtitle;

        public PlaylistViewHolder(View itemView) {
            super(itemView);
            photo = (ImageView)itemView.findViewById(R.id.search_result_photo);
            title = (TextView)itemView.findViewById(R.id.search_result_title);
            title.setSelected(true);
            subtitle = (TextView)itemView.findViewById(R.id.search_result_subtitle);
        }

        public void bind(PlaylistSimple playlistSimple) {
            super.bind(playlistSimple);
            if (playlistSimple.images != null && playlistSimple.images.size() > 0)
                Picasso.with(context).load(playlistSimple.images.get(0).url).into(photo);
            title.setText(playlistSimple.name);
            if (playlistSimple.owner != null)
                subtitle.setText(playlistSimple.owner.display_name);
        }
    }
}
