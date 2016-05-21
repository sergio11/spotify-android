package com.example.sergio.spotify_angular.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import java.util.List;

/**
 * Created by sergio on 04/05/2016.
 */
public abstract class RecyclerViewBaseAdapter<T> extends RecyclerView.Adapter {

    protected Context context;
    protected LayoutInflater inflater;
    protected List<T> data;
    private  OnItemClickListener<T> listener;


    public RecyclerViewBaseAdapter(Context context, List<T> data){
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

    public T getItem(int position){ return data.get(position);}

    public void setData(List<T> data){
        this.data = data;
    }

    public void bindToListener(final RecyclerView.ViewHolder view) {
        if(listener != null){
            view.itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(data.get(view.getLayoutPosition()));
                }
            });
        }
    }

    public void setOnItemClickListener(OnItemClickListener<T> listener){
        this.listener = listener;
    }

    public interface OnItemClickListener<T> {
        void onItemClick(T item);
    }

}
