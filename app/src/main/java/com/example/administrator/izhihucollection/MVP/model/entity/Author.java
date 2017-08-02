package com.example.administrator.izhihucollection.MVP.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by fangxiao on 16/1/26.
 */
public class Author implements Parcelable {

    public static final String BIO = "bio";

    public static final String HASH = "hash";

    public static final String DESCRIPTION = "description";

    public static final String PROFILE_URL = "profileUrl";

    public static final String AVATAR = "avatar";

    public static final String SLUG = "slug";

    public static final String NAME = "name";

    private String bio;

    private String hash;

    private String description;

    private String profileUrl;

    private Avatar avatar;

    private String slug;

    private String name;

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.bio);
        dest.writeString(this.hash);
        dest.writeString(this.description);
        dest.writeString(this.profileUrl);
        dest.writeParcelable(this.avatar, flags);
        dest.writeString(this.slug);
        dest.writeString(this.name);
    }

    public Author() {
    }

    protected Author(Parcel in) {
        this.bio = in.readString();
        this.hash = in.readString();
        this.description = in.readString();
        this.profileUrl = in.readString();
        this.avatar = in.readParcelable(Avatar.class.getClassLoader());
        this.slug = in.readString();
        this.name = in.readString();
    }

    public static final Creator<Author> CREATOR = new Creator<Author>() {
        public Author createFromParcel(Parcel source) {
            return new Author(source);
        }

        public Author[] newArray(int size) {
            return new Author[size];
        }
    };
}
