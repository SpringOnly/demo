package com.example.mvp.view;

import android.Manifest;
import android.annotation.SuppressLint;
import android.view.View;
import android.widget.Toast;


import androidx.annotation.NonNull;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.PermissionUtils;
import com.blankj.utilcode.util.PhoneUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.blankj.utilcode.util.UtilsTransActivity;
import com.example.commonlibrary.base.BaseActivity;
import com.example.commonlibrary.base.arouter.ARouterConstant;
import com.example.commonlibrary.util.LogUtil;
import com.example.mvc.databinding.ActivityMvpBinding;
import com.example.mvp.presenter.MVPPresenter;
import com.example.mvp.presenter.contract.MVPBannerContract;

import org.jetbrains.annotations.NotNull;

import java.util.List;


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
        String permission = Manifest.permission.ACCESS_FINE_LOCATION;
        PermissionUtils.permission(permission)
                .callback(new PermissionUtils.SingleCallback() {
                    @Override
                    public void callback(boolean isAllGranted, @NonNull @NotNull List<String> granted,
                                         @NonNull @NotNull List<String> deniedForever, @NonNull @NotNull List<String> denied) {
                        if (!isAllGranted) {
                            Toast.makeText(MVPActivity.this, "权限被拒绝了", Toast.LENGTH_SHORT).show();
                        }

                    }
                })
                .request();
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