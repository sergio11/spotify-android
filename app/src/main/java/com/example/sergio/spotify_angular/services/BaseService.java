package com.example.sergio.spotify_angular.services;

import android.content.Context;

import com.neovisionaries.i18n.CountryCode;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import kaaes.spotify.webapi.android.SpotifyService;

/**
 * Created by sergio on 21/05/2016.
 */
public class BaseService {

    protected Context context;
    protected SpotifyService service;
    protected EventBus bus;
    protected Map<String,Object> options = new HashMap<>();

    public BaseService(Context context, SpotifyService service, EventBus bus) {
        this.context = context;
        this.service = service;
        this.bus = bus;
        String country = CountryCode.getByLocale(Locale.getDefault()).getAlpha2();
        options.put("country", country);
        options.put("locale", "es_"+country );
    }
}
