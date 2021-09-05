package com.example.commonlibrary.base;

import android.graphics.Point;
import android.os.Bundle;
import android.view.MotionEvent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.viewbinding.ViewBinding;

import com.alibaba.android.arouter.launcher.ARouter;
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity;

public abstract class BaseActivity<VB extends ViewBinding> extends RxAppCompatActivity {

    protected VB mBinding;
    private Point mPoint;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = getViewBinding();
        setContentView(mBinding.getRoot());
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        ARouter.getInstance().inject(this);
        initView();
        initListener();
        initData();

        mPoint = new Point();
    }

    protected Point getPoint() {
        return mPoint;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        mPoint.x = (int) ev.getX();
        mPoint.y = (int) ev.getY();
        return super.dispatchTouchEvent(ev);
    }

    protected abstract VB getViewBinding();
    protected abstract void initView();
    protected abstract void initListener();
    protected abstract void initData();
}
