package com.example.administrator.izhihucollection.MVP.ui.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.widget.TextView;

import com.bumptech.glide.GenericRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.example.administrator.izhihucollection.app.utils.MeasureUtil;

import java.util.HashSet;

/**
 * Created by Administrator on 2017/8/4 0004.
 */
public class GlideImageGetter implements Html.ImageGetter {

    private final Context mContext;
    private final TextView mTextView;
    private HashSet<Target> targets;
    private HashSet<GifDrawable> gifDrawables;

    public GlideImageGetter(Context context, TextView textView) {
        this.mContext = context;
        this.mTextView = textView;
        targets = new HashSet<>();
        gifDrawables = new HashSet<>();
    }

    @Override
    public Drawable getDrawable(String url) {
        final UrlDrawable urlDrawable = new UrlDrawable();
        GenericRequestBuilder load;
        final Target target;
        if(isGif(url)){
            load = Glide.with(mContext).load(url).asGif();
            target = new GifTarget(urlDrawable);
        }else {
            load = Glide.with(mContext).load(url).asBitmap();
            target = new BitmapTarget(urlDrawable);
        }
        targets.add(target);
        load.diskCacheStrategy(DiskCacheStrategy.RESULT).into(target);
        return urlDrawable;
    }

    private static boolean isGif(String path) {
        int index = path.lastIndexOf('.');
        return index > 0 && "gif".toUpperCase().equals(path.substring(index + 1).toUpperCase());
    }

    private class BitmapTarget extends SimpleTarget<Bitmap> {
        private final UrlDrawable urlDrawable;

        public BitmapTarget(UrlDrawable urlDrawable) {
            this.urlDrawable = urlDrawable;
        }
        @Override
        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
            Drawable drawable = new BitmapDrawable(mContext.getResources(), resource);
            int w = MeasureUtil.getScreenSize(mContext).x;
            int hh=drawable.getIntrinsicHeight();
            int ww=drawable.getIntrinsicWidth() ;
            int high=hh*(w-40)/ww;
            Rect rect = new Rect(20, 20,w-20,high);
            drawable.setBounds(rect);
            urlDrawable.setBounds(rect);
            urlDrawable.setDrawable(drawable);
            mTextView.setText(mTextView.getText());
            mTextView.invalidate();
        }
    }

    public void recycle() {
        targets.clear();
        for (GifDrawable gifDrawable : gifDrawables) {
            gifDrawable.setCallback(null);
            gifDrawable.recycle();
        }
        gifDrawables.clear();
    }

    private class GifTarget extends SimpleTarget<GifDrawable> {
        private final UrlDrawable urlDrawable;


        private  GifTarget(UrlDrawable urlDrawable) {
            this.urlDrawable = urlDrawable;

        }

        @Override
        public void onResourceReady(GifDrawable resource, GlideAnimation<? super GifDrawable> glideAnimation) {

            int w = MeasureUtil.getScreenSize(mContext).x;
            int hh=resource.getIntrinsicHeight();
            int ww=resource.getIntrinsicWidth() ;
            int high = hh * (w - 50)/ww;
            Rect rect = new Rect(20, 20,w-30,high);
            resource.setBounds(rect);
            urlDrawable.setBounds(rect);
            urlDrawable.setDrawable(resource);
            gifDrawables.add(resource);
            resource.setCallback(mTextView);
            resource.start();
            resource.setLoopCount(GlideDrawable.LOOP_FOREVER);
            mTextView.setText(mTextView.getText());
            mTextView.invalidate();
        }
    }
}
