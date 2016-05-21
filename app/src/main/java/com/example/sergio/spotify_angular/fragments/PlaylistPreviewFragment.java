package com.example.sergio.spotify_angular.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sergio.spotify_angular.R;
import com.example.sergio.spotify_angular.utils.AnotherView;
import com.nirhart.parallaxscroll.views.ParallaxScrollView;

/**
 * Created by sergio on 21/05/2016.
 */
public class PlaylistPreviewFragment extends Fragment {

    private ParallaxScrollView mScrollView;
    private ListView lvMain;
    private LinearLayout llMain, llMainHolder;
    private AnotherView anotherView;
    private ImageView iv;
    private TextView tvTitle;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.playlist_preview_fragment,container,false);

        // Initialize components

        mScrollView = (ParallaxScrollView) view.findViewById(R.id.scroll_view);

        llMain = (LinearLayout) view.findViewById(R.id.llMain);

        llMainHolder = (LinearLayout) view.findViewById(R.id.llMainHolder);

        lvMain = (ListView) view.findViewById(R.id.lvMain);

        iv = (ImageView) view.findViewById(R.id.iv);

        tvTitle = (TextView) view.findViewById(R.id.tvTitle);

        anotherView = (AnotherView) view.findViewById(R.id.anotherView);

        String[] items = {"one", "two", "three", "four", "five", "six", "seven", "eight",
                "nine", "ten", "evelen", "twelve", "thirteen", "fourteen"};

        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, items);

        lvMain.setAdapter(itemsAdapter);


        lvMain.post(new Runnable() {

            @Override
            public void run() {

                // Adjusts llMain's height to match ListView's height
                setListViewHeight(lvMain, llMain);

                // LayoutParams to set the top margin of LinearLayout holding
                // the content.
                // topMargin = iv.getHeight() - tvTitle.getHeight()
                LinearLayout.LayoutParams p =
                        (LinearLayout.LayoutParams)llMainHolder.getLayoutParams();
                p.topMargin = iv.getHeight() - tvTitle.getHeight();
                llMainHolder.setLayoutParams(p);
            }
        });

        return view;
    }

    // Sets the ListView holder's height
    public void setListViewHeight(ListView listView, LinearLayout llMain) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {

            return;
        }

        int totalHeight = 0;
        int firstHeight = 0;
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(
                listView.getWidth(), View.MeasureSpec.AT_MOST);

        for (int i = 0; i < listAdapter.getCount(); i++) {

            if (i == 0) {
                View listItem = listAdapter.getView(i, null, listView);
                listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
                firstHeight = listItem.getMeasuredHeight();
            }
            totalHeight += firstHeight;
        }

        LinearLayout.LayoutParams params =
                (LinearLayout.LayoutParams)llMain.getLayoutParams();

        params.height = totalHeight + (listView.getDividerHeight() *
                (listAdapter.getCount() - 1));
        llMain.setLayoutParams(params);
        anotherView.requestLayout();
    }
}
