package com.example.sergio.spotify_angular.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sergio.spotify_angular.R;
import com.poliveira.parallaxrecyclerview.ParallaxRecyclerAdapter;

import java.util.List;

/**
 * Created by sergio on 21/05/2016.
 */
public class BaseParallaxRecyclerAdapter extends ParallaxRecyclerAdapter<String> {

    private List<String> data;

    public BaseParallaxRecyclerAdapter(List<String> data) {
        super(data);
        this.data = data;
    }

    @Override
    public void onBindViewHolderImpl(RecyclerView.ViewHolder viewHolder, ParallaxRecyclerAdapter<String> parallaxRecyclerAdapter, int i) {
        TrackViewHolder trackViewHolder = (TrackViewHolder)viewHolder;
        trackViewHolder.setTitle(data.get(i));
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolderImpl(ViewGroup viewGroup, ParallaxRecyclerAdapter<String> parallaxRecyclerAdapter, int i) {
        // Here is where you inflate your row and pass it to the constructor of your ViewHolder
        return new TrackViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.playlist_track_layout, viewGroup, false));
    }

    @Override
    public int getItemCountImpl(ParallaxRecyclerAdapter<String> parallaxRecyclerAdapter) {
        return data.size();
    }


    static class TrackViewHolder extends RecyclerView.ViewHolder{

        private TextView title;

        public TrackViewHolder(View itemView) {
            super(itemView);
            title = (TextView)itemView.findViewById(R.id.title);
        }

        public void setTitle(String title) {
            this.title.setText(title);
        }

        public String getTitle() {
            return title.toString();
        }
    }
}
