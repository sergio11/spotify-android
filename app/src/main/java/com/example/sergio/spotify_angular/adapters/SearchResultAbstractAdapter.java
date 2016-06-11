package com.example.sergio.spotify_angular.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sergio.spotify_angular.R;

import java.lang.reflect.Constructor;
import java.util.List;

/**
 * Created by sergio on 11/06/2016.
 */
public abstract class SearchResultAbstractAdapter<T,E extends SearchResultAbstractAdapter.SearchResultViewHolder> extends RecyclerViewBaseAdapter<T,E> {


    public SearchResultAbstractAdapter(Context context, List<T> data){
        super(context, data);
    }

    protected abstract int getLayout();

    protected abstract E createViewHolder(View itemView);

    @Override
    public E onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(getLayout(), parent, false);
        return createViewHolder(view);
    }

    @Override
    public void onBindViewHolder(E holder, int position) {
        holder.bind(data.get(position));
    }

    public abstract class SearchResultViewHolder extends RecyclerView.ViewHolder{

        protected final ImageView photo;
        protected final TextView title;
        protected final TextView subtitle;

        public SearchResultViewHolder(View itemView) {
            super(itemView);
            photo = (ImageView)itemView.findViewById(R.id.search_result_photo);
            title = (TextView)itemView.findViewById(R.id.search_result_title);
            title.setSelected(true);
            subtitle = (TextView)itemView.findViewById(R.id.search_result_subtitle);
        }

        public abstract void bind(final T element);
    }
}
