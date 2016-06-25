package com.example.sergio.spotify_angular.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;

import java.util.List;

/**
 * Created by sergio on 04/05/2016.
 */
public abstract class RecyclerViewBaseAdapter<T,E extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<E> {

    protected Context context;
    protected LayoutInflater inflater;
    protected List<T> data;
    private  OnItemClickListener<T> listener;
    protected String highlightText;


    public RecyclerViewBaseAdapter(Context context, List<T> data){
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.data = data;
    }

    public void setHighlightText(String highlightText) {
        this.highlightText = highlightText.toLowerCase();
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public T getItem(int position){ return data.get(position);}

    public void setData(List<T> data){
        this.data = data;
    }

    public List<T> getData(){
        return this.data;
    };

    public boolean hasHighlightText(){
        return highlightText != null && highlightText.length() > 0 ? true : false;
    }

    protected Spannable getSpannableString(String text){
        String lowerText = text.toLowerCase();
        Spannable spannable = new SpannableString(lowerText);
        int startIndex = lowerText.indexOf(highlightText);
        if (startIndex >= 0){
            int stopIndex = startIndex + highlightText.length();
            spannable.setSpan(new ForegroundColorSpan(Color.YELLOW), startIndex, stopIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return spannable;
    }

    public void setOnItemClickListener(OnItemClickListener<T> listener){
        this.listener = listener;
    }

    public interface OnItemClickListener<T> {
        void onItemClick(T item);
    }

    public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder{

        public BaseViewHolder(View itemView) {
            super(itemView);
        }

        public void bind(T element){
            if(listener != null){
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override public void onClick(View v) {
                        listener.onItemClick(data.get(getLayoutPosition()));
                    }
                });
            }
        }
    }

}
