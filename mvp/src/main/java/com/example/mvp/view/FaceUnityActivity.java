package com.example.mvp.view;


import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.LogUtils;
import com.example.commonlibrary.base.BaseActivity;
import com.example.commonlibrary.base.arouter.ARouterConstant;
import com.example.mvp.databinding.ActivityFaceUnityBinding;


@Route(path = ARouterConstant.FACE_UNITY)
public class FaceUnityActivity extends BaseActivity<ActivityFaceUnityBinding> {


    @Override
    protected ActivityFaceUnityBinding getViewBinding() {
        return ActivityFaceUnityBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {
    }

    @Override
    protected void initData() {

    }
}