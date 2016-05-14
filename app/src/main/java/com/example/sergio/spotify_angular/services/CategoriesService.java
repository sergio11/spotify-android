package com.example.sergio.spotify_angular.services;


import com.example.sergio.spotify_angular.events.ApiErrorEvent;
import com.example.sergio.spotify_angular.events.CategoriesLoadedEvent;
import com.example.sergio.spotify_angular.events.LoadCategoriesEvent;
import com.neovisionaries.i18n.CountryCode;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import kaaes.spotify.webapi.android.SpotifyService;
import kaaes.spotify.webapi.android.models.CategoriesPager;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by sergio on 14/05/2016.
 */
public class CategoriesService {

    private SpotifyService service;
    private EventBus bus;
    private Map<String,Object> options = new HashMap<>();

    public CategoriesService(SpotifyService service, EventBus bus) {
        this.service = service;
        this.bus = bus;
        String country = CountryCode.getByLocale(Locale.getDefault()).getAlpha2();
        options.put("country", country);
        options.put("locale", "es_"+country );
    }

    @Subscribe
    public void onLoadCategories(LoadCategoriesEvent event){
        service.getCategories(options, new Callback<CategoriesPager>() {
            @Override
            public void success(CategoriesPager categoriesPager, Response response) {
                bus.post(new CategoriesLoadedEvent(categoriesPager.categories.items));
            }

            @Override
            public void failure(RetrofitError error) {
                bus.post(new ApiErrorEvent(error));
            }
        });
    }
}
