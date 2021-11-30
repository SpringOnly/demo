package amd.example.java.view;


import android.os.Handler;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.SizeUtils;
import com.blankj.utilcode.util.ToastUtils;

import amd.example.commonlibrary.base.BaseActivity;
import amd.example.commonlibrary.base.arouter.RouterPath;
import amd.example.commonlibrary.util.CommonLog;
import amd.example.demo.databinding.ActivityViewPagerTwoBinding;


@Route(path = RouterPath.ViewPagerTwo)
public class ViewPagerTwoActivity extends BaseActivity<ActivityViewPagerTwoBinding> {

    private GestureDetector mGestureDetector;
    //最小距离
    int X_FLING_MIN_DISTANCE = SizeUtils.dp2px(25);
    //最小速度
    int X_FLING_MIN_VELOCITY = SizeUtils.dp2px(800);

    int Y_FLING_MIN_DISTANCE = SizeUtils.dp2px(100);
    int Y_FLING_MIN_VELOCITY = SizeUtils.dp2px(800);

    //偏移动画是否执行完毕
    private boolean isOffsetAnim;
    //手指按下的事件
    private long mDownTime;

    @Override
    protected ActivityViewPagerTwoBinding getViewBinding() {
        return ActivityViewPagerTwoBinding.inflate(getLayoutInflater());
    }


    @Override
    protected void initView() {
        ViewConfiguration vc = ViewConfiguration.get(this);
        mTouchSlop = vc.getScaledTouchSlop();

        mGestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {

            @Override
            public boolean onDown(MotionEvent e) {
                mDownTime = System.currentTimeMillis();
                return super.onDown(e);
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
//                //如果已经超过滑动阈值
//                if (isDirectionVertical) {
//                    mBinding.partyView.setTranslationYOffset(e1.getY() - e2.getY());
//                }
//                //防止横向滑动偏移触发垂直滑动
//                if (!isDirectionVertical && Math.abs(e1.getY() - e2.getY()) > 80) {
//                    isDirectionVertical = true;
//                }
//                mBinding.partyView.setTranslationYOffset(e1.getY() - e2.getY());
//                CommonLog.e("e1:" + e1.getX() + " " + e1.getY());
//                CommonLog.e("e2:" + e2.getX() + " " + e2.getY());
//
//                if (e2.getY() > e1.getX() && System.currentTimeMillis() - mDownTime > ViewConfiguration.getTapTimeout()) {
//                    mBinding.partyView.setTranslationYOffset(e1.getY() - e2.getY());
//                }

                return false;
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {


                //x方向大于Y方向
                //x方向滑动的距离超过最小距离
                //x方向滑动的速度超过最小速度
                if (Math.abs(velocityX) > Math.abs(velocityY) &&
                        Math.abs(e1.getX() - e2.getX()) > X_FLING_MIN_DISTANCE &&
                        Math.abs(velocityX) > X_FLING_MIN_VELOCITY) {

                    if (e1.getX() - e2.getX() > 0) {
                        CommonLog.e("左");
                        ToastUtils.showShort("左");
                    } else {
                        CommonLog.e("右");
                        ToastUtils.showShort("右");
                    }

                    return true;
                }

                if (Math.abs(velocityY) > Math.abs(velocityX) &&
                        Math.abs(e1.getY() - e2.getY()) > Y_FLING_MIN_DISTANCE &&
                        Math.abs(velocityY) > Y_FLING_MIN_VELOCITY) {
                    isOffsetAnim = true;
                    if (e1.getY() - e2.getY() > 0) {
                        CommonLog.e("上");
                        mBinding.partyView.setTopSlide();
                    } else {
                        CommonLog.e("下");
                        mBinding.partyView.setBottomSlide();
                    }


                    //模拟加入房间成功
                    new Handler().postDelayed(() -> {
                        mBinding.partyView.showPartyView();
                    }, 1000);
                }
                return false;
            }
        });
    }

    @Override
    protected void initListener() {
        mBinding.partyView.registerAnimListener(() -> {
            isOffsetAnim = false;
        });
    }

    @Override
    protected void initData() {

    }

    private int mDownX;
    private int mDownY;
    private int mTouchSlop;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (super.dispatchTouchEvent(ev)) {
            return true;
        }
        int actionMasked = ev.getActionMasked();
        switch (actionMasked) {
            case MotionEvent.ACTION_DOWN:
                mDownTime = System.currentTimeMillis();
                mDownX = (int) ev.getX();
                mDownY = (int) ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                int moveX = (int) ev.getX();
                int moveY = (int) ev.getY();
                int dx = mDownX - moveX;
                int dy = mDownY - moveY;
                if (Math.abs(dy) > mTouchSlop) {
                    if (Math.abs(dy) > Math.abs(dx) && System.currentTimeMillis() - mDownTime > ViewConfiguration.getTapTimeout()) {
                        mBinding.partyView.setTranslationYOffset(dy);
                        return true;
                    }
                }
                break;
        }
        mGestureDetector.onTouchEvent(ev);

        if (!isOffsetAnim && ev.getActionMasked() == MotionEvent.ACTION_UP) {
            mBinding.partyView.resetViewOffset();
        }
        return super.dispatchTouchEvent(ev);
    }
}