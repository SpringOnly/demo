package com.example.main.view;

import androidx.viewpager2.widget.ViewPager2;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.commonlibrary.base.BaseActivity;
import com.example.commonlibrary.base.arouter.RouterPath;
import com.example.commonlibrary.util.CommonLog;
import com.example.main.databinding.ActivityViewPagerTwoBinding;
import com.example.main.view.adapter.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

@Route(path = RouterPath.ViewPagerTwo)
public class ViewPagerTwoActivity extends BaseActivity<ActivityViewPagerTwoBinding> {

    @Override
    protected ActivityViewPagerTwoBinding getViewBinding() {
        return ActivityViewPagerTwoBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView() {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);
        mBinding.viewPager.setAdapter(viewPagerAdapter);
//        mBinding.viewPager.setCurrentItem(6,false);
//        mBinding.viewPager.setOffscreenPageLimit(ViewPager2.OFFSCREEN_PAGE_LIMIT_DEFAULT);
        mBinding.viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                CommonLog.e("onPageSelected:" + position);
            }
        });

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }
}