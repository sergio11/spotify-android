package com.example.sergio.spotify_angular.adapters;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

import kaaes.spotify.webapi.android.models.Category;
import kaaes.spotify.webapi.android.models.Image;

import com.example.sergio.spotify_angular.R;

/**
 * Created by sergio on 04/05/2016.
 */
public class CategoriesAdapter extends GridViewAdapter<Category>{

    public CategoriesAdapter(Context context, List<Category> list) {
        super(context, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Category category = data.get(position);
        if(convertView == null){
            convertView = inflater.inflate(R.layout.category_layout, null);
        }
        ImageView poster = (ImageView) convertView.findViewById(R.id.poster);
        Image image = category.icons.get(0);
        poster.setImageURI(Uri.parse(image.url));
        TextView titulo = (TextView)convertView.findViewById(R.id.title);
        titulo.setText(category.name);
        return convertView;
    }
}
