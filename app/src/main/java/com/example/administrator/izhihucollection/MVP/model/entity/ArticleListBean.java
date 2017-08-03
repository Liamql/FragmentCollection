package com.example.administrator.izhihucollection.MVP.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by fangxiao on 16/1/5.
 */
public class ArticleListBean implements Parcelable {

    private String author;

    private String author_des;

    private String title;

    private String summary;

    private String content;

    private String url;

    private String href;

    private String likesCount;


    public String getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(String likesCount) {
        this.likesCount = likesCount;
    }

    public String getAuthor_des()
    {
        return this.author_des;
    }

    public void setAuthor_des(String author_des)
    {
        this.author_des = author_des;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
        dest.writeString(this.author);
        dest.writeString(this.author_des);
        dest.writeString(this.title);
        dest.writeString(this.summary);
        dest.writeString(this.content);
        dest.writeString(this.url);
        dest.writeString(this.href);
        dest.writeString(this.likesCount);
    }

    public ArticleListBean() {
    }

    public ArticleListBean(String title,String summary,String author,String author_des,String likesCount) {
        this.title = title;
        this.author_des = author_des;
        this.author = author;
        this.summary = summary;
        this.likesCount = likesCount;
    }

    protected ArticleListBean(Parcel in) {
        this.author = in.readString();
        this.author_des = in.readString();
        this.title = in.readString();
        this.summary = in.readString();
        this.content = in.readString();
        this.url = in.readString();
        this.href = in.readString();
        this.likesCount = in.readString();
    }

    public static final Creator<ArticleListBean> CREATOR = new Creator<ArticleListBean>() {
        public ArticleListBean createFromParcel(Parcel source) {
            return new ArticleListBean(source);
        }

        public ArticleListBean[] newArray(int size) {
            return new ArticleListBean[size];
        }
    };

    public String show()
    {
        return author+":"+summary;
    }
}
