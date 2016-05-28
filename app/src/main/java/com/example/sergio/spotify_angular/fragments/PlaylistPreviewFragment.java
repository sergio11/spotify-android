package com.example.sergio.spotify_angular.fragments;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sergio.spotify_angular.R;
import com.example.sergio.spotify_angular.events.LoadPlaylistTracks;
import com.example.sergio.spotify_angular.events.PlaylistTracksLoadedEvent;
import com.example.sergio.spotify_angular.utils.ImageUtils;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.poliveira.parallaxrecyclerview.ParallaxRecyclerAdapter;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import kaaes.spotify.webapi.android.models.ArtistSimple;
import kaaes.spotify.webapi.android.models.PlaylistSimple;
import kaaes.spotify.webapi.android.models.PlaylistTrack;

/**
 * Created by sergio on 21/05/2016.
 */
public class PlaylistPreviewFragment extends Fragment {

    private final static String TAG = "PlaylistPreviewFragment";

    public final static String PLAYLIST_PARAM = "playlist";

    private RecyclerView recyclerPlaylistTracks;
    private PlaylistSimple playlist;
    private EventBus eventBus = EventBus.getDefault();
    private ParallaxAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        eventBus.register(this);
        Bundle arguments = getArguments();
        if (arguments != null) playlist = arguments.getParcelable(PLAYLIST_PARAM);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.playlist_preview_fragment,container,false);
        final View header = inflater.inflate(R.layout.playlist_tracks_header, recyclerPlaylistTracks, false);

        String url = playlist.images.get(0).url;
        //background image
        ImageUtils.getBlurredImage(getActivity(),url,playlist.name,10, new ImageUtils.BlurEffectListener(){
            @Override
            public void onDone(Bitmap bitmap) {
                header.setBackground(new BitmapDrawable(getResources(),bitmap));
            }
        } );
        //playlist image
        ImageView image = (ImageView) header.findViewById(R.id.playlist_image);
        Picasso.with(getActivity()).load(url).into(image);
        //Playlist name
        TextView textView = (TextView) header.findViewById(R.id.playlist_name);
        textView.setText(playlist.name);

        recyclerPlaylistTracks = (RecyclerView)view.findViewById(R.id.playlist_tracks);
        recyclerPlaylistTracks.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter = new ParallaxAdapter(new ArrayList<PlaylistTrack>());
        adapter.setOnClickEvent(new ParallaxRecyclerAdapter.OnClickEvent() {
            @Override
            public void onClick(View v, int position) {
                Toast.makeText(getActivity(), "You clicked '" + position + "'", Toast.LENGTH_SHORT).show();
            }
        });
        adapter.setParallaxHeader(header, recyclerPlaylistTracks);
        recyclerPlaylistTracks.setAdapter(adapter);

        //Load Tracks
        eventBus.post(new LoadPlaylistTracks(playlist.owner.id,playlist.id));

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        eventBus.unregister(this);
    }

    @Subscribe
    public void onPlaylistTracksLoaded(PlaylistTracksLoadedEvent event){

        adapter.setData(event.getItems());
        adapter.notifyDataSetChanged();
    }

    static class ParallaxAdapter extends ParallaxRecyclerAdapter<PlaylistTrack>{


        public ParallaxAdapter(List<PlaylistTrack> data) {
            super(data);
        }

        @Override
        public void onBindViewHolderImpl(RecyclerView.ViewHolder viewHolder, ParallaxRecyclerAdapter<PlaylistTrack> parallaxRecyclerAdapter, int i) {
            PlaylistTrack playlistTrack = getData().get(i);
            TrackViewHolder trackViewHolder = (TrackViewHolder)viewHolder;
            trackViewHolder.setTitle(playlistTrack.track.name);
            List<String> names = Lists.transform(playlistTrack.track.artists, new Function<ArtistSimple, String>() {
                @Override
                public String apply(ArtistSimple artist) {
                    return artist.name;
                }
            });
           trackViewHolder.setArtists(TextUtils.join(", ",names));
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolderImpl(ViewGroup viewGroup, ParallaxRecyclerAdapter<PlaylistTrack> parallaxRecyclerAdapter, int i) {
            return new TrackViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.playlist_track_layout, viewGroup, false));
        }

        @Override
        public int getItemCountImpl(ParallaxRecyclerAdapter<PlaylistTrack> parallaxRecyclerAdapter) {
            return getData().size();
        }
    }

    static class TrackViewHolder extends RecyclerView.ViewHolder{

        private TextView title;
        private TextView artists;

        public TrackViewHolder(View itemView) {
            super(itemView);
            title = (TextView)itemView.findViewById(R.id.title);
            artists = (TextView)itemView.findViewById(R.id.artists);
        }

        public void setTitle(String title) {
            this.title.setText(title);
        }

        public String getTitle() {
            return title.toString();
        }

        public String getArtists() {
            return artists.toString();
        }

        public void setArtists(String artists) {
            this.artists.setText(artists);
        }
    }
}
