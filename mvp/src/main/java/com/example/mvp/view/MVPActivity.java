package com.example.mvp.view;

import android.graphics.Bitmap;
import android.net.Uri;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.PagerSnapHelper;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.ImageUtils;
import com.bun.miitmdid.core.MdidSdkHelper;
import com.bun.miitmdid.interfaces.IIdentifierListener;
import com.bun.miitmdid.interfaces.IdSupplier;
import com.example.commonlibrary.base.BaseActivity;
import com.example.commonlibrary.base.arouter.ARouterConstant;
import com.example.commonlibrary.util.LogUtil;
import com.example.mvc.R;
import com.example.mvc.databinding.ActivityMvpBinding;
import com.example.mvp.bean.RouterBean;
import com.example.mvp.bean.SnapHelperBean;
import com.example.mvp.demo.crp.ApplyTestDemo;
import com.example.mvp.presenter.MVPPresenter;
import com.example.mvp.presenter.contract.MVPBannerContract;
import com.example.mvp.view.adapter.SnapHelperAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.internal.bind.TypeAdapters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


@Route(path = ARouterConstant.MVP)
public class MVPActivity extends BaseActivity<ActivityMvpBinding> implements MVPBannerContract.MVPView {

    MVPPresenter mMVPPresenter;

    @Autowired(name = "age")
    int age;

    @Override
    protected ActivityMvpBinding getViewBinding() {
        return ActivityMvpBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView() {
        mMVPPresenter = new MVPPresenter(this);
        Binding.mvpResult.setText(String.valueOf(age));
    }

    @Override
    protected void initListener() {
        Map<String, Object> kvMap = new HashMap<>();

        String routerUrl = "native://?androidUrl=/user/home&otherId=10281";
        Uri uri = Uri.parse(routerUrl);
        Set<String> queryParameterNames = uri.getQueryParameterNames();

        for (String parameter : queryParameterNames) {
            kvMap.put(parameter, uri.getQueryParameter(parameter));
            LogUtil.e("par:" + parameter + "queryParameterNames:" + queryParameterNames);

        }

        //这里就有了所有的参数
        RouterBean routerBean = mapToObject(kvMap, RouterBean.class);
        LogUtil.e("getAndroidUrl:" + routerBean.getAndroidUrl());
        LogUtil.e("getOtherId:" + routerBean.getOtherId());
        LogUtil.e("getUserId:" + routerBean.getUserId());
    }

    @Nullable
    public static <T> T mapToObject(Map<String, Object> map, Class<T> clazz) {
        JsonElement jsonElement = toJsonTree(map);
        if (jsonElement == null) {
            return null;
        }

        return fromJson(jsonElement, clazz);
    }

    @Nullable
    public static <T> T fromJson(JsonElement json, Class<T> clazz) {
        try {
            return GSON.fromJson(json, clazz);
        } catch (JsonSyntaxException | IllegalStateException e) {
            return null;
        }
    }

    /**
     * 转换为json列表
     *
     * @param obj 对象
     */
    @Nullable
    public static JsonElement toJsonTree(Object obj) {
        try {
            return GSON.toJsonTree(obj);
        } catch (JsonIOException | IllegalStateException e) {
            return null;
        }
    }

    public static Gson GSON = new GsonBuilder()
            .create();

    @Override
    protected void initData() {

    }

    public void getData(View view) {
        mMVPPresenter.MVPGetDate();
    }

    @Override
    public void success(String result) {
        runOnUiThread(() -> Binding.mvpResult.setText(result));
    }

    @Override
    public void onError(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}