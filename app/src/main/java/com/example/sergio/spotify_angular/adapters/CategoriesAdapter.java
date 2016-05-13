package com.example.sergio.spotify_angular.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

import kaaes.spotify.webapi.android.models.Category;

import com.example.sergio.spotify_angular.R;
import com.squareup.picasso.Picasso;

/**
 * Created by sergio on 04/05/2016.
 */
public class CategoriesAdapter extends RecyclerViewBaseAdapter<Category> {
    private final static String TAG = "CATEGORIES_ADAPTER";
    public CategoriesAdapter(Context context, List<Category> list) {
        super(context, list);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.category_layout, parent, false);
        return new CategoriesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Category category = data.get(position);
        CategoriesViewHolder categoryViewHolder = (CategoriesViewHolder)holder;
        categoryViewHolder.setName(category.name);
        Picasso.with(context).load(category.icons.get(0).url).placeholder(R.drawable.loader).into(categoryViewHolder.getImage());
    }


    static class CategoriesViewHolder extends RecyclerView.ViewHolder{

        private ImageView image;
        private TextView name;

        public CategoriesViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.poster);
            name = (TextView) itemView.findViewById(R.id.tittle);
        }

        public ImageView getImage() {
            return image;
        }


        public TextView getName() {
            return name;
        }

        public void setName(String name) {
            this.name.setText(name);
        }
    }

}
