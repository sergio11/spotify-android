package com.example.sergio.spotify_angular.adapters.resultsearch;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sergio.spotify_angular.R;
import com.example.sergio.spotify_angular.adapters.ProgressLoadedAdapter;
import com.example.sergio.spotify_angular.adapters.RecyclerViewBaseAdapter;
import com.google.common.base.Function;
import com.google.common.collect.Lists;

import java.util.List;

import kaaes.spotify.webapi.android.models.ArtistSimple;
import kaaes.spotify.webapi.android.models.Track;

/**
 * Created by sergio on 11/06/2016.
 */
public class TracksAdapter extends ProgressLoadedAdapter<Track, TracksAdapter.TrackViewHolder> {

    public TracksAdapter(Context context, List<Track> data) {
        super(context, data);
    }

    @Override
    protected RecyclerViewBaseAdapter.BaseViewHolder getViewHolderItem(ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.track_result_search_item, parent, false);
        return new TrackViewHolder(view);
    }


    public class TrackViewHolder extends  RecyclerViewBaseAdapter<Track, TracksAdapter.TrackViewHolder>.BaseViewHolder<Track>{

        protected final TextView title;
        protected final TextView subtitle;

        public TrackViewHolder(View itemView) {
            super(itemView);
            title = (TextView)itemView.findViewById(R.id.search_result_title);
            title.setSelected(true);
            subtitle = (TextView)itemView.findViewById(R.id.search_result_subtitle);
        }

        public void bind(Track track) {
            super.bind(track);
            List<String> names = Lists.transform(track.artists, new Function<ArtistSimple, String>() {
                @Override
                public String apply(ArtistSimple artist) {
                    return artist.name;
                }
            });
            if (hasHighlightText()){
                Spannable spannableTitle = getSpannableString(track.name);
                title.setText(spannableTitle);
                Spannable spannableNames = getSpannableString(TextUtils.join(", ",names));
                subtitle.setText(spannableNames);
            }else{
                title.setText(track.name);
                subtitle.setText(TextUtils.join(", ",names));
            }



        }
    }
}
