package com.example.sergio.spotify_angular.fragments.dialogs;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.sergio.spotify_angular.BaseApp;
import com.example.sergio.spotify_angular.R;
import com.example.sergio.spotify_angular.events.CreatePlaylistEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sergio on 25/06/2016.
 */
public class CreatePlaylistDialogFragment extends DialogFragment {

    private EditText playlistName;
    private EventBus bus = EventBus.getDefault();

    @Override
    public Dialog onCreateDialog(final Bundle savedInstanceState) {

        final View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_fragment_create_playlist,null, false);
        playlistName = (EditText)  view.findViewById(R.id.playlist_name);
        //playlist_name
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.create_playlist_title);
        builder.setView(view);
        builder.setNegativeButton(getString(R.string.cancel_create_playlist), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(final DialogInterface dialog, final int which) {
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(playlistName.getWindowToken(), 0);
            }
        });

        builder.setPositiveButton(getString(R.string.create_playlist), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(final DialogInterface dialog, final int which) {
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(playlistName.getWindowToken(), 0);

                String userId = ((BaseApp) getActivity().getApplication()).getMe().id;
                String name = playlistName.getText().toString();
                Map<String,Object> options = new HashMap();
                options.put("name",name);
                options.put("public", true);
                bus.post(new CreatePlaylistEvent(userId,options));
                dialog.dismiss();


            }
        });

        final AlertDialog dialog = builder.create();

        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(final DialogInterface dialog) {
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(view, 0);

                Button negativeButton = ((AlertDialog)dialog).getButton(DialogInterface.BUTTON_NEGATIVE);
                Button positiveButton = ((AlertDialog)dialog).getButton(DialogInterface.BUTTON_POSITIVE);


                // this not working because multiplying white background (e.g. Holo Light) has no effect
                //negativeButton.getBackground().setColorFilter(0xFFFF0000, PorterDuff.Mode.MULTIPLY);

                final Drawable buttonDrawable = getResources().getDrawable(R.drawable.background_dialog_button);
                if (Build.VERSION.SDK_INT >= 16) {
                    negativeButton.setBackground(buttonDrawable);
                    positiveButton.setBackground(buttonDrawable);
                } else {
                    negativeButton.setBackgroundDrawable(buttonDrawable);
                    positiveButton.setBackgroundDrawable(buttonDrawable);
                }

                negativeButton.invalidate();
                positiveButton.invalidate();
            }
        });



        return dialog;
    }


}
