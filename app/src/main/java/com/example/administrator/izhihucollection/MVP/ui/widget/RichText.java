package com.example.administrator.izhihucollection.MVP.ui.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/8/4 0004.
 */
public class RichText extends TextView implements View.OnAttachStateChangeListener {


    private GlideImageGetter glideImageGetter;
    public RichText(Context context) {
        this(context, null);
    }

    public RichText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RichText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setRichText(String text) {
        glideImageGetter=new GlideImageGetter(getContext(), this);
        Spanned spanned = Html.fromHtml(text, new GlideImageGetter(getContext(), this), null);
        super.setText(spanned);
        setMovementMethod(LinkMovementMethod.getInstance());

    }

    @Override
    public void onViewDetachedFromWindow(View v) {

        glideImageGetter.recycle();
    }

    @Override
    public void invalidateDrawable(Drawable who) {

        invalidateOutline();

    }

    @Override
    public void scheduleDrawable(Drawable who, Runnable what, long when) {
    }

    @Override
    public void unscheduleDrawable(Drawable who, Runnable what) {
    }

    @Override
    public void onViewAttachedToWindow(View v) {

    }

}
