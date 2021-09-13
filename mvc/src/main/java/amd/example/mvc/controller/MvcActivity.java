package amd.example.mvc.controller;

import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import amd.example.commonlibrary.base.BaseActivity;
import amd.example.commonlibrary.base.CallBack;
import amd.example.commonlibrary.base.arouter.RouterPath;
import amd.example.commonlibrary.util.CommonLog;
import com.example.mvc.databinding.ActivityMvcBinding;
import amd.example.mvc.model.BannerModel;
import amd.example.mvc.model.impl.BannerModelImpl;

@Route(path = RouterPath.MVC)
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
                runOnUiThread(() -> mBinding.result.setText(result));
            }

            @Override
            public void onError(String msg) {
                CommonLog.e("onError");
            }
        });

    }
}