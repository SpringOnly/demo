package com.example.main.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.commonlibrary.base.BaseFragment;
import com.example.commonlibrary.util.CommonLog;
import com.example.main.R;
import com.example.main.databinding.FragmentViewPagerBinding;

public class ViewPagerFragment extends BaseFragment<FragmentViewPagerBinding> {

    private String TAG = "create fragment";

    @Override
    public void initView() {
        CommonLog.e(TAG);
        Bundle arguments = getArguments();
        int key = arguments.getInt("key");
        mBinding.textView.setText(String.valueOf(key));
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    public void destroyFragment() {
        TAG = "destroy fragment";
        CommonLog.e(TAG);
    }
}