package com.example.sergio.spotify_angular.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.sergio.spotify_angular.R;

import java.util.List;

/**
 * Created by sergio on 18/06/2016.
 */
public abstract class ProgressLoadedAdapter<T, E extends RecyclerViewBaseAdapter.BaseViewHolder > extends RecyclerViewBaseAdapter<T, RecyclerViewBaseAdapter.BaseViewHolder>{

    private final int VIEW_TYPE_ITEM = 1;
    private final int VIEW_TYPE_PROGRESSBAR = 0;
    private boolean isFooterEnabled = false;

    public ProgressLoadedAdapter(Context context, List<T> data) {
        super(context, data);
    }

    @Override
    public int getItemCount() {
        return  (isFooterEnabled) ? data.size() + 1 : data.size();
    }

    @Override
    public int getItemViewType(int position) {
        return (isFooterEnabled && position >= data.size() ) ? VIEW_TYPE_PROGRESSBAR : VIEW_TYPE_ITEM;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseViewHolder vh;
        if (viewType == VIEW_TYPE_ITEM) {

            vh = getViewHolderItem(parent);
        }else{
            View view = LayoutInflater.from(context).inflate(R.layout.progress_item, parent, false);
            vh = new ProgressViewHolder(view);
        }

        return vh;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        if (holder.getClass() != ProgressViewHolder.class){
            holder.bind(data.get(position));
        }
    }

    /**
     * Enable or disable footer (Default is true)
     *
     * @param isEnabled boolean to turn on or off footer.
     */
    public void enableFooter(boolean isEnabled){
        this.isFooterEnabled = isEnabled;
    }

    protected abstract BaseViewHolder getViewHolderItem(ViewGroup parent);

    public  class ProgressViewHolder extends RecyclerViewBaseAdapter<T, RecyclerViewBaseAdapter.BaseViewHolder>.BaseViewHolder<T> {
        public ProgressBar progressBar;

        public ProgressViewHolder(View v) {
            super(v);
            progressBar = (ProgressBar) v.findViewById(R.id.progressBar);
        }
    }
}
