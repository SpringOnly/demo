package com.example.main;

import android.content.Intent;
import android.view.View;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.ToastUtils;
import com.example.commonlibrary.base.BaseActivity;
import com.example.commonlibrary.base.arouter.ARouterConstant;
import com.example.commonlibrary.router_provider.HelloProvider;
import com.example.commonlibrary.util.LogUtil;
import com.example.main.databinding.ActivityMainBinding;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


public class MainActivity extends BaseActivity<ActivityMainBinding> {


    @Autowired
    HelloProvider mProvider;

    @Override
    protected ActivityMainBinding getViewBinding() {
        return ActivityMainBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {
        Binding.mvc.setOnClickListener(v ->
                ARouter.getInstance()
                        .build(ARouterConstant.MVC)
                        .navigation(MainActivity.this,
                                new NavCallback() {
                                    @Override
                                    public void onArrival(Postcard postcard) {
                                        LogUtil.e("MainActivity：" + "onArrival : " + postcard.getPath());
                                    }

                                    @Override
                                    public void onInterrupt(Postcard postcard) {
                                        LogUtil.e("MainActivity：" + "onInterrupt : " + postcard.getPath());
                                    }
                                }));
        Binding.mvp.setOnClickListener(v ->
                ARouter.getInstance().build(ARouterConstant.MVP)
                        .withInt("age", 18)
                        .navigation(MainActivity.this, new NavCallback() {
                            @Override
                            public void onArrival(Postcard postcard) {
                                LogUtil.e("MainActivity：" + "onArrival : " + postcard.getPath());
                            }
                        }));

        Binding.jumpMoudel.setOnClickListener(v ->
                mProvider.sayHello("jack"));

        //        ((HelloService)ARouter
//                .getInstance()
//                .build(ARouterConstant.HelloService)
//                .navigation())
//                .sayHello("jack name");
    }

    @Override
    protected void initData() {

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (data != null) {
                int name = data.getIntExtra("age", 0);
                ToastUtils.showShort(String.valueOf(name));
            }
        }
    }
}