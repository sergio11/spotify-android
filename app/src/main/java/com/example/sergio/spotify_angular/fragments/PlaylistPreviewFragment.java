package com.example.sergio.spotify_angular.fragments;


import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sergio.spotify_angular.BaseApp;
import com.example.sergio.spotify_angular.R;
import com.example.sergio.spotify_angular.activities.HomeActivity;
import com.example.sergio.spotify_angular.events.AreFollowingPlaylistCheckedEvent;
import com.example.sergio.spotify_angular.events.AreFollowingPlaylistEvent;
import com.example.sergio.spotify_angular.events.FollowPlaylistEvent;
import com.example.sergio.spotify_angular.events.FollowPlaylistSuccessEvent;
import com.example.sergio.spotify_angular.events.LoadPlaylistEvent;
import com.example.sergio.spotify_angular.events.PlaylistLoadedEvent;
import com.example.sergio.spotify_angular.events.UnFollowPlaylistEvent;
import com.example.sergio.spotify_angular.events.UnFollowPlaylistSuccessEvent;
import com.example.sergio.spotify_angular.utils.ImageUtils;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.poliveira.parallaxrecyclerview.ParallaxRecyclerAdapter;
import com.squareup.picasso.Picasso;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import kaaes.spotify.webapi.android.models.ArtistSimple;
import kaaes.spotify.webapi.android.models.Playlist;
import kaaes.spotify.webapi.android.models.PlaylistTrack;
import kaaes.spotify.webapi.android.models.UserPrivate;

/**
 * Created by sergio on 21/05/2016.
 */
public class PlaylistPreviewFragment extends EventBusFragment {

    private final static String TAG = "PlaylistPreviewFragment";

    public final static String PLAYLIST_ID_PARAM = "playlist_id";
    public final static String PLAYLIST_OWNER_ID_PARAM = "owner_id";

    private RecyclerView recyclerPlaylistTracks;
    private View recyclerHeader;
    private Playlist playlist;
    private UserPrivate me;
    private boolean isFollowing;
    private Button btnFollow;
    private ParallaxAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

        Bundle arguments = getArguments();
        View view = null;
        if(arguments != null && arguments.containsKey(PLAYLIST_ID_PARAM) && arguments.containsKey(PLAYLIST_OWNER_ID_PARAM) ){
            view = inflater.inflate(R.layout.playlist_preview_fragment,container,false);
            //clear the toolbar
            ((HomeActivity) getActivity()).clearToolbar();
            recyclerPlaylistTracks = (RecyclerView)view.findViewById(R.id.playlist_tracks);
            recyclerPlaylistTracks.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerHeader = LayoutInflater.from(getActivity()).inflate(R.layout.playlist_tracks_header, recyclerPlaylistTracks, false);
            me = ((BaseApp) getActivity().getApplication()).getMe();
            //Load Tracks
            bus.post(new LoadPlaylistEvent(arguments.getString(PLAYLIST_OWNER_ID_PARAM),arguments.getString(PLAYLIST_ID_PARAM)));
        }
        return view;

    }


    @Subscribe
    public void onPlaylistLoaded(final PlaylistLoadedEvent event){

        playlist = event.getPlaylist();

        String url = playlist.images.get(0).url;
        //background image
        ImageUtils.getBlurredImage(getActivity(),url,playlist.name,10, new ImageUtils.BlurEffectListener(){
            @Override
            public void onDone(Bitmap bitmap) {
                recyclerHeader.setBackground(new BitmapDrawable(getResources(),bitmap));
            }
        } );
        //playlist image
        ImageView image = (ImageView) recyclerHeader.findViewById(R.id.playlist_image);
        Picasso.with(getActivity()).load(url).into(image);
        //Playlist name
        TextView textView = (TextView) recyclerHeader.findViewById(R.id.playlist_name);
        textView.setText(playlist.name);

        //followers and user
        TextView followers = (TextView) recyclerHeader.findViewById(R.id.user_and_followers);
        int total = playlist.followers != null ? playlist.followers.total : 0;
        followers.setText(String.format(Locale.getDefault(),getResources().getString(R.string.playlist_followers_and_user),total,playlist.owner.display_name));

        adapter = new ParallaxAdapter(new ArrayList<PlaylistTrack>());
        adapter.setOnClickEvent(new ParallaxRecyclerAdapter.OnClickEvent() {
            @Override
            public void onClick(View v, int position) {
                Toast.makeText(getActivity(), "You clicked '" + position + "'", Toast.LENGTH_SHORT).show();
            }
        });
        adapter.setParallaxHeader(recyclerHeader, recyclerPlaylistTracks);
        adapter.setData(playlist.tracks.items);
        recyclerPlaylistTracks.setAdapter(adapter);
        List<String> users = new ArrayList<>();
        users.add(me.id);
        bus.post(new AreFollowingPlaylistEvent(playlist.owner.id,playlist.id,users));
    }

    @Subscribe
    public void onAreFollowingPlaylistChecked(AreFollowingPlaylistCheckedEvent event){
        isFollowing = event.getResult().get(me.id);
        btnFollow = (Button) recyclerHeader.findViewById(R.id.follow_btn);
        if(isFollowing){
            setBtnAsFollowing();
        }
        btnFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isFollowing){
                    bus.post(new FollowPlaylistEvent(playlist.owner.id,playlist.id));
                }else{
                    bus.post(new UnFollowPlaylistEvent(playlist.owner.id,playlist.id));
                }
            }
        });
    }

    @Subscribe
    public void onFollowPlaylistSuccess(FollowPlaylistSuccessEvent event){
        setBtnAsFollowing();
    }

    @Subscribe
    public void onUnFollowPlaylistSuccess(UnFollowPlaylistSuccessEvent event){
        setBtnAsNotFollow();
    }

    private void setBtnAsFollowing(){
        btnFollow.setBackground(getResources().getDrawable(R.drawable.follow_btn));
        btnFollow.setText(getResources().getString(R.string.follow));
        btnFollow.setTextColor(getResources().getColor(R.color.primary));
        isFollowing = true;
    }

    private void setBtnAsNotFollow(){
        btnFollow.setBackground(getResources().getDrawable(R.drawable.no_follow_btn));
        btnFollow.setText(getResources().getString(R.string.not_follow));
        btnFollow.setTextColor(getResources().getColor(android.R.color.white));
        isFollowing = false;
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
