package com.example.sergio.spotify_angular.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sergio.spotify_angular.R;
import com.example.sergio.spotify_angular.models.MenuAppItem;
import com.joanzapata.iconify.IconDrawable;
import com.joanzapata.iconify.fonts.FontAwesomeIcons;

import java.util.List;

/**
 * Created by sergio on 13/05/2016.
 */
public class MenuAdapter extends RecyclerViewBaseAdapter<MenuAppItem,MenuAdapter.MenuViewHolder> {

    public MenuAdapter(Context context, List<MenuAppItem> data) {
        super(context, data);
    }

    @Override
    public MenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.menu_item_layout, parent, false);
        return new MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MenuViewHolder holder, int position) {
        holder.bind(data.get(position));

    }


    public class MenuViewHolder extends RecyclerViewBaseAdapter<MenuAppItem, MenuAdapter.MenuViewHolder>.BaseViewHolder<MenuAppItem>{

        private ImageView icon;
        private TextView text;

        public MenuViewHolder(View itemView) {
            super(itemView);
            icon = (ImageView) itemView.findViewById(R.id.menu_icon);
            text = (TextView) itemView.findViewById(R.id.menu_text);
        }

        @Override
        public void bind(MenuAppItem item) {
            super.bind(item);
            text.setText(item.getText());
            icon.setImageDrawable(new IconDrawable(context, FontAwesomeIcons.valueOf(item.getIcon())).colorRes(R.color.primary));
        }
    }
}