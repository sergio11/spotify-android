package com.example.sergio.spotify_angular.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by sergio on 21/05/2016.
 */
public class AnotherView extends LinearLayout {

    private ScrollCallbacks mCallbacks;

    static interface ScrollCallbacks {
        public void onScrollChanged(int l, int t, int oldl, int oldt);
    }

    public void setCallbacks(ScrollCallbacks listener) {
        mCallbacks = listener;
    }

    public AnotherView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (mCallbacks != null) {
            mCallbacks.onScrollChanged(l, t, oldl, oldt);
        }
    }

    @Override
    public int computeVerticalScrollRange() {
        return super.computeVerticalScrollRange();
    }

}