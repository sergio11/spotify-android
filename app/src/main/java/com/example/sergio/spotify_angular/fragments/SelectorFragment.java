package com.example.sergio.spotify_angular.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.sergio.spotify_angular.BaseApp;
import com.example.sergio.spotify_angular.R;

import com.example.sergio.spotify_angular.adapters.GridViewAdapter;


/**
 * Created by sergio on 04/05/2016.
 */
public abstract class SelectorFragment<Model> extends Fragment {

    protected GridViewAdapter<Model> adapter;
    protected BaseApp app;

    public SelectorFragment(GridViewAdapter<Model> adapter) {
        this.adapter = adapter;
    }

    protected abstract void loadData();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.category_selector_fragment, container, false);
        GridView gridview = (GridView) vista.findViewById(R.id.categories);
        gridview.setAdapter(adapter);
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(parent.getContext(), "Seleccionado el elemento " + position, Toast.LENGTH_LONG).show();
            }
        });
        return vista;

    }

    /*It's not called because this method has been added in API 23. If you run your application on a device with API 23 (marshmallow) then onAttach(Context) will be called. On all previous Android Versions onAttach(Activity) will be called.*/
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        app = (BaseApp) activity.getApplication();
    }

    @Override
    public void onResume() {
        super.onResume();
        //load data
        this.loadData();
    }
}
