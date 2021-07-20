package com.example.mvp.bean;


public class RouterBean {
    private String androidUrl;

    private long userId;
    private long otherId;

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
}
