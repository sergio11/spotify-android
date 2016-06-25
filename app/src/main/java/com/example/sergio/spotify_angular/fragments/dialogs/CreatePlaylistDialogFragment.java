package com.example.sergio.spotify_angular.fragments.dialogs;


import android.app.DialogFragment;
import android.content.Context;
import android.graphics.PorterDuff;
import android.inputmethodservice.Keyboard;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.example.sergio.spotify_angular.BaseApp;
import com.example.sergio.spotify_angular.R;
import com.example.sergio.spotify_angular.events.CreatePlaylistEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by sergio on 25/06/2016.
 */
public class CreatePlaylistDialogFragment extends DialogFragment {

    @BindView(R.id.playlist_name) EditText playlistName;
    private EventBus bus = EventBus.getDefault();
    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_fragment_create_playlist, container, false);
        unbinder = ButterKnife.bind(this, view);
        playlistName.getBackground().setColorFilter(getResources().getColor(R.color.primary), PorterDuff.Mode.SRC_IN);
        InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null) {
            inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
        }
        return view;
    }

    @OnClick(R.id.create_playlist)
    protected void createPlaylist(){

        if (!TextUtils.isEmpty(playlistName.getText())){
            String name = playlistName.getText().toString();
            String userId = ((BaseApp) getActivity().getApplication()).getMe().id;
            Map<String,Object> options = new HashMap();
            options.put("name",name);
            options.put("public", true);
            bus.post(new CreatePlaylistEvent(userId,options));
            InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (inputMethodManager != null) {
                inputMethodManager.hideSoftInputFromWindow(playlistName.getWindowToken(), 0);
            }
            this.dismiss();
        }else{
            playlistName.setError(getString(R.string.playlist_name_not_empty));
        }

    }

    @OnClick(R.id.cancel_create_playlist)
    protected void cancel(){
        InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(playlistName.getWindowToken(), 0);
        }
        this.dismiss();
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
