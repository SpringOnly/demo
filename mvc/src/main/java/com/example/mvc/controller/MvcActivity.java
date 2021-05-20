package com.example.mvc.controller;

import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.commonlibrary.base.BaseActivity;
import com.example.commonlibrary.base.CallBack;
import com.example.commonlibrary.base.arouter.ARouterConstant;
import com.example.commonlibrary.util.LogUtil;
import com.example.mvc.databinding.ActivityMvcBinding;
import com.example.mvc.model.BannerModel;
import com.example.mvc.model.impl.BannerModelImpl;

@Route(path = ARouterConstant.MVC)
public class MvcActivity extends BaseActivity<ActivityMvcBinding> {

    private BannerModel mBannerModel;

    @Override
    protected ActivityMvcBinding getViewBinding() {
        return ActivityMvcBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView() {
        mBannerModel = new BannerModelImpl();
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    public void getData(View view) {
        mBannerModel.getBanner("asd", new CallBack() {
            @Override

            public void onSuccess(String result) {
                runOnUiThread(() -> Binding.result.setText(result));
            }

            @Override
            public void onError(String msg) {
                LogUtil.e("onError");
            }
        });

    }
}