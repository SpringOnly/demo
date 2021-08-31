package com.example.mvp.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.ScreenUtils;
import com.example.commonlibrary.base.BaseActivity;
import com.example.commonlibrary.base.arouter.ARouterConstant;
import com.example.commonlibrary.util.CommonLog;
import com.example.mvp.R;
import com.example.mvp.databinding.ActivityMvpBinding;
import com.example.mvp.bean.SnapHelperBean;
import com.example.mvp.presenter.MVPPresenter;
import com.example.mvp.presenter.contract.MVPBannerContract;
import com.example.mvp.view.adapter.SnapHelperAdapter;

import java.util.ArrayList;
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
        PagerSnapHelper pagerSnapHelper = new PagerSnapHelper();
        pagerSnapHelper.attachToRecyclerView(Binding.recycler);

        SnapHelperAdapter snapHelperAdapter = new SnapHelperAdapter();
        Binding.recycler.setAdapter(snapHelperAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        Binding.recycler.setLayoutManager(layoutManager);

        List<SnapHelperBean> beanList = new ArrayList<>();
        int[] ints = {R.color.purple_200, R.color.purple_500, R.color.purple_700,
                R.color.teal_200, R.color.teal_700};
        for (int i = 0; i < 5; i++) {
            SnapHelperBean snapHelperBean = new SnapHelperBean();
            snapHelperBean.setBackground(ints[i]);
            beanList.add(snapHelperBean);
        }
        snapHelperAdapter.setNewData(beanList);
        Binding.recycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    isOpenClean = true;
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                isOpenClean = false;
            }
        });

    }

//    //记录按下的坐标 不拦截
    float downX = 0;
    //记录移动的坐标
    float moveX = 0;
    //是否接收清屏动作
    boolean isOpenClean = false;
//
//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        if (!isOpenClean) {
//            return super.dispatchTouchEvent(ev);
//        }
//        int actionMasked = ev.getActionMasked();
//        switch (actionMasked) {
//            case MotionEvent.ACTION_DOWN:
//                downX = ev.getRawX();
//                CommonLog.e("ACTION_DOWN:" + downX);
//                return super.dispatchTouchEvent(ev);
//            case MotionEvent.ACTION_MOVE:
//                //移动过程中的坐标
//                moveX = ev.getRawX();
//                return super.dispatchTouchEvent(ev);
//            case MotionEvent.ACTION_CANCEL:
//                CommonLog.e("ACTION_CANCEL");
//            case MotionEvent.ACTION_UP:
//                //如果滑动的像素大于300 那么拦截
//                //右滑
//                CommonLog.e("result right:" + (moveX - downX));
//                if (moveX - downX > 300) {
//                    onRightSwipe();
//                    return true;
//                }
//                //左滑
//                CommonLog.e("result left:" + (downX - moveX));
//                if (downX - moveX > 300) {
//                    onLeftSwipe();
//                    return true;
//                }
//                return super.dispatchTouchEvent(ev);
//        }
//        return super.dispatchTouchEvent(ev);
//    }
//
//    private void onRightSwipe() {
//        if (Binding.image.getVisibility() == View.VISIBLE) {
//            return;
//        }
//        isOpenClean = false;
//        Binding.image.setVisibility(View.VISIBLE);
//        ObjectAnimator translationX = ObjectAnimator.ofFloat(Binding.image,
//                "translationX", -ScreenUtils.getScreenWidth(), 0);
//        translationX.setDuration(300);
//        translationX.addListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                super.onAnimationEnd(animation);
//                isOpenClean = true;
//            }
//        });
//        translationX.start();
//    }
//
//    public void onLeftSwipe() {
//        if (Binding.image.getVisibility() == View.GONE) {
//            return;
//        }
//        isOpenClean = false;
//        ObjectAnimator translationX = ObjectAnimator.ofFloat(Binding.image,
//                "translationX", 0, -ScreenUtils.getScreenWidth());
//        translationX.setDuration(300);
//        translationX.addListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                super.onAnimationEnd(animation);
//                isOpenClean = true;
//                Binding.image.setVisibility(View.GONE);
//            }
//        });
//        translationX.start();
//    }

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