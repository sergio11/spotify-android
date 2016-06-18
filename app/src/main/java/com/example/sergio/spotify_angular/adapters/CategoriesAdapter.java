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
public class CategoriesAdapter extends RecyclerViewBaseAdapter<Category,CategoriesAdapter.CategoriesViewHolder> {
    private final static String TAG = "CATEGORIES_ADAPTER";
    public CategoriesAdapter(Context context, List<Category> list) {
        super(context, list);
    }

    @Override
    public CategoriesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.category_layout, parent, false);
        return new CategoriesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CategoriesViewHolder holder, int position) {
        holder.bind(data.get(position));
    }


    public class CategoriesViewHolder extends RecyclerViewBaseAdapter<Category, CategoriesAdapter.CategoriesViewHolder>.BaseViewHolder<Category>{

        private ImageView image;
        private TextView name;

        public CategoriesViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.poster);
            name = (TextView) itemView.findViewById(R.id.tittle);
        }

        @Override
        public void bind(Category category) {
            super.bind(category);
            name.setText(category.name);
            Picasso.with(context).load(category.icons.get(0).url).placeholder(R.drawable.ic_categories).into(image);
        }

    }

}
