package com.example.mvp.bean;


import android.os.Parcel;
import android.os.Parcelable;


public class RouterBean implements Parcelable {

    private String androidUrl;
    private long userId;
    private long otherId;

    public RouterBean() {
    }


    protected RouterBean(Parcel in) {
        androidUrl = in.readString();
        userId = in.readLong();
        otherId = in.readLong();
    }

    public static final Creator<RouterBean> CREATOR = new Creator<RouterBean>() {
        @Override
        public RouterBean createFromParcel(Parcel in) {
            return new RouterBean(in);
        }

        @Override
        public RouterBean[] newArray(int size) {
            return new RouterBean[size];
        }
    };

    public String getAndroidUrl() {
        return androidUrl;
    }

    public void setAndroidUrl(String androidUrl) {
        this.androidUrl = androidUrl;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getOtherId() {
        return otherId;
    }

    public void setOtherId(long otherId) {
        this.otherId = otherId;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(androidUrl);
        dest.writeLong(userId);
        dest.writeLong(otherId);
    }
}
