package com.example.administrator.izhihucollection.MVP.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by fangxiao on 16/1/26.
 */
public class Avatar implements Parcelable {

    public static final String ID = "id";

    public static final String TEMPLATE = "template";

    private String id;

    private String template;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.template);
    }

    public Avatar() {
    }

    protected Avatar(Parcel in) {
        this.id = in.readString();
        this.template = in.readString();
    }

    public static final Creator<Avatar> CREATOR = new Creator<Avatar>() {
        public Avatar createFromParcel(Parcel source) {
            return new Avatar(source);
        }

        public Avatar[] newArray(int size) {
            return new Avatar[size];
        }
    };
}
