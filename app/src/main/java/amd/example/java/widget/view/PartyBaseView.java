package amd.example.java.widget.view;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.blankj.utilcode.util.ScreenUtils;

import amd.example.demo.databinding.PartyRoomViewBinding;
import amd.example.java.util.SimpleOnAnimatorListener;

/**
 * @author Created by on LvJP 2021-11-29
 */
public class PartyBaseView extends RelativeLayout {

    protected PartyRoomViewBinding mBinding;

    /**
     * 动画是否正在执行
     */
    private boolean isRunningAnim = false;

    /**
     * 屏幕高度
     */
    private int screenHeight;
    /**
     * view偏移值
     */
    private float offset;

    public PartyBaseView(Context context) {
        this(context, null);
    }

    public PartyBaseView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mBinding = PartyRoomViewBinding.inflate(LayoutInflater.from(context));
        addView(mBinding.getRoot());
        initView();
    }

    private void initView() {
        screenHeight = ScreenUtils.getScreenHeight();
    }

    /**
     * 向上翻页
     */
    public void setTopSlide() {
        runAnim(getTranslationY(), -screenHeight);
    }

    /**
     * 向下翻页
     */
    public void setBottomSlide() {
        runAnim(getTranslationY(), screenHeight);
    }

    /**
     * 重新显示View
     */
    public void showPartyView() {
        setTranslationY(0);
    }

    /**
     * 设置view偏移
     */
    public void setTranslationYOffset(float offset) {
        this.offset = offset;
        if (offset > 0) {
            setTranslationY(-offset);
        } else {
            setTranslationY(Math.abs(offset));
        }
    }

    /**
     * 松手以后重置偏移
     */
    public void resetViewOffset() {
        //向上不到一半就恢复原来的状态
        if (offset > 0 && offset < screenHeight / 2f) {
            runAnim(-offset, 0);
        }
        //向上超过一半直接划过去
        if (offset > 0 && offset > screenHeight / 2f) {
            runAnim(getTranslationY(), -screenHeight);

            //模拟加入房间成功
            new Handler().postDelayed(() -> {
                showPartyView();
            }, 1500);
        }
        //向下不到一半就恢复原来的状态
        if (offset < 0 && Math.abs(offset) < screenHeight / 2f) {
            runAnim(-offset, 0);
        }
        //向下超过一半直接划过去
        if (offset < 0 && Math.abs(offset) > screenHeight / 2f) {
            runAnim(getTranslationY(), screenHeight);

            //模拟加入房间成功
            new Handler().postDelayed(() -> {
                showPartyView();
            }, 1500);
        }
        offset = 0;
    }

    private void runAnim(float start, float end) {
        if (isRunningAnim) return;
        ObjectAnimator animator = ObjectAnimator.ofFloat(this, "translationY",
                start, end);
        animator.addListener(new SimpleOnAnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                isRunningAnim = true;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                isRunningAnim = false;
                offset = 0;
                if (mListener != null) {
                    mListener.onAnimationEnd();
                }
            }
        });
        animator.setDuration(200);
        animator.start();
    }

    private onAnimListener mListener;

    public interface onAnimListener {
        void onAnimationEnd();
    }

    public void registerAnimListener(onAnimListener listener) {
        mListener = listener;
    }

    public void unRegisterAnimListener() {
        mListener = null;
    }
}
