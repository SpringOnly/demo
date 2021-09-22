package amd.example.java;

import android.content.Intent;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.yalantis.ucrop.view.OverlayView;

import java.util.List;

import amd.example.commonlibrary.base.BaseActivity;
import amd.example.commonlibrary.base.arouter.RouterPath;
import amd.example.commonlibrary.router_provider.HelloProvider;
import amd.example.demo.databinding.ActivityMainBinding;
import amd.example.java.widget.GlideEngine;


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

        mBinding.mvp.setOnClickListener(v ->
                ARouter.getInstance().build(RouterPath.DEMO).navigation()
        );

        mBinding.jumpMoudel.setOnClickListener(v ->
                mProvider.sayHello("jack"));

        //        ((HelloService)ARouter
//                .getInstance()
//                .build(ARouterConstant.HelloService)
//                .navigation())
//                .sayHello("jack name");

        mBinding.agora.setOnClickListener(v ->
                ARouter.getInstance().build(RouterPath.AGORA)
                        .navigation());

        mBinding.faceUnity.setOnClickListener(v ->
                ARouter.getInstance().build(RouterPath.FACE_UNITY)
                        .navigation());

        mBinding.picture.setOnClickListener(v -> selectPicture());

        mBinding.viewPagerTwo.setOnClickListener(v ->
                ARouter.getInstance().build(RouterPath.ViewPagerTwo)
                        .navigation());
    }

    @Override
    protected void initData() {

    }

    private void selectPicture() {
        PictureSelector.create(MainActivity.this)
                .openGallery(PictureMimeType.ofImage())
                .imageEngine(GlideEngine.createGlideEngine())
                .isEnableCrop(true)
                .scaleEnabled(false)
                .rotateEnabled(false)
                .freeStyleCropMode(OverlayView.FREESTYLE_CROP_MODE_ENABLE)
                .forResult(PictureConfig.CHOOSE_REQUEST);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 结果回调
                    List<LocalMedia> result = PictureSelector.obtainMultipleResult(data);
                    break;
                default:
                    break;
            }
        }
    }

}