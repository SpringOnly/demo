package amd.example.java;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;

import amd.example.commonlibrary.base.BaseActivity;
import amd.example.commonlibrary.base.arouter.RouterPath;
import amd.example.commonlibrary.router_provider.HelloProvider;
import amd.example.commonlibrary.util.CommonLog;
import amd.example.demo.databinding.ActivityMainBinding;
import amd.example.java.bean.CloneTest;

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


        mBinding.viewPagerTwo.setOnClickListener(v ->
                ARouter.getInstance().build(RouterPath.ViewPagerTwo)
                        .navigation());
    }

    @Override
    protected void initData() {

    }
}