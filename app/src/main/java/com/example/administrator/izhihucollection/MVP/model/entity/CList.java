package com.example.administrator.izhihucollection.MVP.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by fangxiao on 16/1/26.
 */
public class CList implements Parcelable {

    public static final String ID = "title";

    public static final String TEMPLATE = "href";

    private String title;

    private String href;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.href);
    }

    public CList() {
    }

    public CList(String title)
    {
        this.title = title;
    }

    public CList(String title,String href)
    {
        this.title = title;
        this.href = href;
    }

    protected CList(Parcel in) {
        this.title = in.readString();
        this.href = in.readString();
    }

    public static final Creator<CList> CREATOR = new Creator<CList>() {
        public CList createFromParcel(Parcel source) {
            return new CList(source);
        }

        public CList[] newArray(int size) {
            return new CList[size];
        }
    };
}
