package amd.example.mvp.view;


import com.alibaba.android.arouter.facade.annotation.Route;
import amd.example.commonlibrary.base.BaseActivity;
import amd.example.commonlibrary.base.arouter.RouterPath;
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