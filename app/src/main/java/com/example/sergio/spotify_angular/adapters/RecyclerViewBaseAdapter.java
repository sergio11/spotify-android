package com.example.sergio.spotify_angular.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import java.util.List;

/**
 * Created by sergio on 04/05/2016.
 */
public abstract class RecyclerViewBaseAdapter<Model> extends RecyclerView.Adapter {

    protected Context context;
    protected LayoutInflater inflater;
    protected List<Model> data;

    public RecyclerViewBaseAdapter(Context context, List<Model> data){
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.data = data;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setData(List<Model> data){
        this.data = data;
    }

}
