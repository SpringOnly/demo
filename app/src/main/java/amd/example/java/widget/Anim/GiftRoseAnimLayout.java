package amd.example.java.widget.Anim;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.core.content.ContextCompat;

import amd.example.demo.R;

/**
 * @author Created by on LvJP 2022/6/11
 * 爱心送礼定位动画
 */
public class GiftRoseAnimLayout extends RelativeLayout {
    private Drawable mDrawable;
    private LayoutParams mParams;
    private int mWidth;
    private int mHeight;
    //顶部停止的位置
    private int stopHeight = 150;

    public GiftRoseAnimLayout(Context context) {
        this(context, null);
    }

    public GiftRoseAnimLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mDrawable = ContextCompat.getDrawable(getContext(), R.mipmap.red);
        if (mDrawable == null) {
            return;
        }
        //获取实际在屏幕上显示的宽高
        int width = mDrawable.getIntrinsicWidth();
        int height = mDrawable.getIntrinsicHeight();

        //始终在底部
        mParams = new LayoutParams(width, height);
//        mParams.addRule(ALIGN_PARENT_BOTTOM, TRUE);
//        mParams.addRule(CENTER_HORIZONTAL, TRUE);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
    }

    //顶部停止动画的位置 默认值150
    public void setYStopAnim(int y) {
        stopHeight = y;
    }

    public void addGiftView(int[] pointList) {
        final ImageView imageView = new ImageView(getContext());
        imageView.setLayoutParams(mParams);
        imageView.setImageDrawable(mDrawable);
        addView(imageView);

        AnimatorSet animatorSet = getAnimSet(imageView, pointList);
        animatorSet.start();

        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                removeView(imageView);
            }
        });
    }

    public AnimatorSet getAnimSet(ImageView imageView, int[] pointList) {
        float widthCenter = (mWidth / 2f) - (mParams.width / 2f);
        int centerHeight = stopHeight - mDrawable.getIntrinsicHeight();
        imageView.setX(widthCenter);

        //1.逐渐放大动画
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(imageView, "scaleX", 0.5f, 1.2f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(imageView, "scaleY", 0.5f, 1.2f);

        //2.向上移动动画
        ObjectAnimator translationY = ObjectAnimator.ofFloat(imageView, "translationY",
                mHeight - mParams.height, centerHeight);

        AnimatorSet set1 = new AnimatorSet();
        set1.setDuration(1500);
        set1.playTogether(scaleX, scaleY, translationY);


        ObjectAnimator translationX2 = ObjectAnimator.ofFloat(imageView,
                "translationX", widthCenter, pointList[0] - mParams.width / 2f);

        ObjectAnimator translationY2 = ObjectAnimator.ofFloat(imageView,
                "translationY", centerHeight, pointList[1]- mParams.height / 2f);

        AnimatorSet set2 = new AnimatorSet();
        set2.setDuration(1500);
        set2.playTogether(translationY2, translationX2);

        AnimatorSet set3 = new AnimatorSet();
        set3.playSequentially(set1, set2);
        set3.setTarget(imageView);
        return set3;
    }
}
