package amd.example.java.view;


import android.os.Handler;
import android.view.GestureDetector;
import android.view.MotionEvent;

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
    //是否已经触发了垂直滑动
    private boolean isDirectionVertical;

    @Override
    protected ActivityViewPagerTwoBinding getViewBinding() {
        return ActivityViewPagerTwoBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView() {

        mGestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                if (isDirectionVertical) {
                    mBinding.partyView.setTranslationYOffset(e1.getY() - e2.getY());
                    return false;
                }
                if (Math.abs(e1.getY() - e2.getY()) > 100) {
                    isDirectionVertical = true;
                    mBinding.partyView.setTranslationYOffset(e1.getY() - e2.getY());
                }
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
                    }, 1500);
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

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (super.dispatchTouchEvent(ev)) {
            CommonLog.e("aaa:");
            return true;
        }
        mGestureDetector.onTouchEvent(ev);
        int action = ev.getActionMasked();
        if (!isOffsetAnim && action == MotionEvent.ACTION_UP) {
            mBinding.partyView.resetViewOffset();
            isDirectionVertical = false;
        }
        return super.dispatchTouchEvent(ev);
    }
}