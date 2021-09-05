package com.example.mvp.view;


import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.commonlibrary.base.BaseActivity;
import com.example.commonlibrary.base.arouter.RouterPath;
import com.example.mvp.databinding.ActivityFaceUnityBinding;


@Route(path = RouterPath.FACE_UNITY)
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