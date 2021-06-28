package com.example.mvp.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.provider.MediaStore;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.PathUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.example.commonlibrary.base.BaseActivity;
import com.example.commonlibrary.base.arouter.ARouterConstant;
import com.example.commonlibrary.util.LogUtil;
import com.example.mvc.databinding.ActivityMvpBinding;
import com.example.mvp.demo.DynamicCircleDemo;
import com.example.mvp.demo.XiaomiMarketingDemo;
import com.example.mvp.presenter.MVPPresenter;
import com.example.mvp.presenter.contract.MVPBannerContract;
import com.kk.taurus.playerbase.assist.OnVideoViewEventHandler;
import com.kk.taurus.playerbase.config.PlayerLibrary;
import com.kk.taurus.playerbase.entity.DataSource;
import com.kk.taurus.playerbase.event.OnPlayerEventListener;
import com.kk.taurus.playerbase.receiver.OnReceiverEventListener;
import com.kk.taurus.playerbase.receiver.ReceiverGroup;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import tv.danmaku.ijk.media.player.IjkMediaPlayer;

import static android.os.storage.StorageManager.ACTION_MANAGE_STORAGE;


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
        DynamicCircleDemo.setOnTouch(Binding.mainActivity,Binding.DynamicCircle);
    }

    @Override
    protected void initListener() {

    }

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