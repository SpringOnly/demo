package amd.example.mvp.view;

import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.alibaba.android.arouter.facade.annotation.Route;

import amd.example.commonlibrary.base.BaseActivity;
import amd.example.commonlibrary.base.arouter.RouterPath;

import com.example.mvp.R;
import com.example.mvp.databinding.ActivityMvpBinding;

import java.util.ArrayList;
import java.util.List;

import amd.example.mvp.bean.SnapHelperBean;
import amd.example.mvp.presenter.MVPPresenter;
import amd.example.mvp.presenter.contract.MVPBannerContract;
import amd.example.mvp.view.adapter.SnapHelperAdapter;


@Route(path = RouterPath.DEMO)
public class MVPActivity extends BaseActivity<ActivityMvpBinding> implements MVPBannerContract.MVPView {

    MVPPresenter mMVPPresenter;

    @Override
    protected ActivityMvpBinding getViewBinding() {
        return ActivityMvpBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView() {
        mMVPPresenter = new MVPPresenter(this);
    }

    @Override
    protected void initListener() {

    }


    @Override
    protected void initData() {
        List<SnapHelperBean> list = new ArrayList<>();
        SnapHelperAdapter snapHelperAdapter = new SnapHelperAdapter();
        snapHelperAdapter.onAttachedToRecyclerView(mBinding.recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mBinding.recycler.setLayoutManager(layoutManager);
        for (int i = 0; i < 20; i++) {
            SnapHelperBean snapHelperBean = new SnapHelperBean();
            snapHelperBean.setBackground(R.mipmap.image);
            list.add(snapHelperBean);
        }
        snapHelperAdapter.setNewData(list);
    }

    public void getData(View view) {
        mMVPPresenter.MVPGetDate();
    }

    @Override
    public void success(String result) {
        runOnUiThread(() -> mBinding.mvpResult.setText(result));
    }

    @Override
    public void onError(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}