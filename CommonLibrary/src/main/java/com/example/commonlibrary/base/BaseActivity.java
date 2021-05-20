package com.example.commonlibrary.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBinding;

import com.alibaba.android.arouter.launcher.ARouter;

public abstract class BaseActivity<VB extends ViewBinding> extends AppCompatActivity {

    protected VB Binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Binding = getViewBinding();
        setContentView(Binding.getRoot());

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        ARouter.getInstance().inject(this);
        initView();
        initListener();
        initData();
    }

    protected abstract VB getViewBinding();
    protected abstract void initView();
    protected abstract void initListener();
    protected abstract void initData();
}
