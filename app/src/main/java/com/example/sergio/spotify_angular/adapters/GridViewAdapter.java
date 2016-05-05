package com.example.sergio.spotify_angular.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by sergio on 04/05/2016.
 */
public abstract class GridViewAdapter<Model> extends BaseAdapter {

    protected LayoutInflater inflater;
    protected List<Model> data;

    public GridViewAdapter(Context context, List<Model> data){
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.data = data;
    }

    @Override
    public int getCount() {
        return this.data.size();
    }

    @Override
    public Object getItem(int position) {
        return this.data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setData(List<Model> data){
        this.data = data;
    }

}
