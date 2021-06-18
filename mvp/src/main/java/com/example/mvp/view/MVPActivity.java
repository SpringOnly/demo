package com.example.mvp.view;

import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.EncodeUtils;
import com.blankj.utilcode.util.EncryptUtils;
import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.PermissionUtils;
import com.blankj.utilcode.util.UtilsTransActivity;
import com.example.commonlibrary.base.BaseActivity;
import com.example.commonlibrary.base.arouter.ARouterConstant;
import com.example.commonlibrary.util.LogUtil;
import com.example.mvc.databinding.ActivityMvpBinding;
import com.example.mvp.adapter.GridLayoutAdapter;
import com.example.mvp.bean.GridlayoutBean;
import com.example.mvp.presenter.MVPPresenter;
import com.example.mvp.presenter.contract.MVPBannerContract;
import com.example.mvp.bean.TypeLeftBean;
import com.example.mvp.bean.TypeRightBean;
import com.example.mvp.adapter.ChatAdapter;
import com.example.mvp.util.BaseUtils;
import com.example.mvp.util.XiaomiMarketing;


import org.jetbrains.annotations.NotNull;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import static android.Manifest.permission.READ_PHONE_STATE;


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
        Binding.check.setOnCheckedChangeListener((buttonView, isChecked) -> LogUtil.e("onCheckedChanged:" + isChecked));
        XiaomiMarketing.initSign();
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void GridRV() {
        List<GridlayoutBean> list = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            GridlayoutBean gridlayoutBean = new GridlayoutBean();
            gridlayoutBean.setMessage("消息：" + i);
            list.add(gridlayoutBean);
        }

        GridLayoutAdapter gridLayoutAdapter = new GridLayoutAdapter(this);
        Binding.RecyclerView.setAdapter(gridLayoutAdapter);
        gridLayoutAdapter.setData(list);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                List<GridlayoutBean> data = gridLayoutAdapter.getData();
                if (data.size() < 5) {
                    if (position == 0) {
                        return 2;
                    }
                } else {
                    if (position == 4) {
                        return 2;
                    }
                }
                return 1;
            }
        });
        Binding.RecyclerView.setLayoutManager(gridLayoutManager);


    }


    @Override
    protected void initListener() {

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


    public void chatRV() {

        ChatAdapter mChatAdapter = new ChatAdapter(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        Binding.RecyclerView.setLayoutManager(layoutManager);
        Binding.RecyclerView.setAdapter(mChatAdapter);

        //聊天对话框的demo
        List<Object> list = new ArrayList<>();

        TypeLeftBean typeLeftBean = new TypeLeftBean();
        typeLeftBean.setLeftMessage("左边1");
        list.add(typeLeftBean);

        TypeRightBean typeRightBean = new TypeRightBean();
        typeRightBean.setRightMessage("右边1");
        list.add(typeRightBean);

        TypeRightBean typeRightBean2 = new TypeRightBean();
        typeRightBean2.setRightMessage("右边2");
        list.add(typeRightBean2);

        TypeLeftBean typeLeftBean2 = new TypeLeftBean();
        typeLeftBean2.setLeftMessage("左边2");
        list.add(typeLeftBean2);


        mChatAdapter.replaceData(list);
    }

}