package com.example.commonlibrary.base.api;


import com.example.commonlibrary.base.BaseConstant;


import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;

public interface ApiService {

    @GET(BaseConstant.banner)
    Observable<ResponseBody> getData();
}