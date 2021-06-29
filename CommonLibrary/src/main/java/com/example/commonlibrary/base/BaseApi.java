package com.example.commonlibrary.base;

import android.util.Log;


import com.example.commonlibrary.BuildConfig;
import com.example.commonlibrary.base.api.SSLSocketClient;

import org.jetbrains.annotations.NotNull;

import java.net.Proxy;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public class BaseApi {

    private static BaseApi instance;

    public static BaseApi getInstance() {
        if (null == instance) {
            synchronized (BaseApi.class) {
                if (null == instance) {
                    instance = new BaseApi();
                }
            }
        }
        return instance;
    }

    public Retrofit getRetrofit() {
        Retrofit build = new Retrofit.Builder()
                .baseUrl(BaseConstant.BaseUrl)
                .client(getClient().build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return build;
    }

    Interceptor logInterceptor;


    private OkHttpClient.Builder getClient() {
        //处理网络请求的日志拦截输出
        if (BuildConfig.DEBUG) {
            logInterceptor = new HttpLoggingInterceptor(new HttpLog()).setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            logInterceptor = new HttpLoggingInterceptor();
        }
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(15, TimeUnit.SECONDS);
        httpClientBuilder.readTimeout(15, TimeUnit.SECONDS);
        httpClientBuilder.writeTimeout(15, TimeUnit.SECONDS);
        httpClientBuilder.sslSocketFactory(SSLSocketClient.getSSLSocketFactory());
        httpClientBuilder.hostnameVerifier(SSLSocketClient.getHostnameVerifier());
        httpClientBuilder.proxy(Proxy.NO_PROXY);
        httpClientBuilder.hostnameVerifier((hostname, session) -> true);
        httpClientBuilder.addInterceptor(logInterceptor);
        return httpClientBuilder;
    }

    public static class HttpLog implements HttpLoggingInterceptor.Logger {
        @Override
        public void log(@NotNull String message) {
            Log.d("HttpLogInfo", message);
        }
    }
}
