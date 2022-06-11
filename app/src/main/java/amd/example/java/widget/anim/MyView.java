package amd.example.java.widget.anim;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.ScreenUtils;


/**
 * @author Created by on LvJP 2022/5/27
 */
public class MyView extends View {
    public final float RADIUS = 70f;// 圆的半径 = 70
    private Paint mPaint;
    private MyViewPoint mCurrentMyViewPoint; //当前坐标点

    public MyView(Context context) {
        super(context);
        init();
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLUE);
        float centerWidth = ScreenUtils.getAppScreenWidth() / 2f;

        MyViewPoint startMyViewPoint = new MyViewPoint(centerWidth, ScreenUtils.getAppScreenHeight() - 100);
        MyViewPoint endMyViewPoint = new MyViewPoint(centerWidth, 0 + 100f);

        ValueAnimator animator = ValueAnimator.ofObject(new CustomEvaluator(), startMyViewPoint, endMyViewPoint);
        animator.setDuration(3500);
        animator.addUpdateListener(animation -> {
            mCurrentMyViewPoint = (MyViewPoint) animation.getAnimatedValue();
            invalidate();
        });

        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if (mAnimListener != null) {
                    mAnimListener.onAnimEnd();
                }
            }
        });
        animator.start();
    }

    private AnimListener mAnimListener;

    public interface AnimListener {
        void onAnimEnd();
    }

    public void setAnimListener(AnimListener animListener) {
        mAnimListener = animListener;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float x = mCurrentMyViewPoint.getX();
        float y = mCurrentMyViewPoint.getY();
        canvas.drawCircle(x, y, RADIUS, mPaint);
    }

    //    private void addView() {
//        MyView myView = new MyView(MainActivity.this);
//        myView.setAnimListener(() -> {
//            mBinding.rootLayout.removeView(myView);
//            myView.setAnimListener(null);
//        });
//        mBinding.rootLayout.addView(myView);
//    }


//    private AnimatorPath path;//声明动画集合
//    /*设置动画路径*/
//    public void setPath(){
//        mBinding.fab.setOnClickListener(v -> {
//            startAnimatorPath("fab", path);
//        });
//        path = new AnimatorPath();
//        path.moveTo(0,0);
//        path.lineTo(400,400);
//        path.secondBesselCurveTo(600, 200, 800, 400); //订单
//        path.thirdBesselCurveTo(100,600,900,1000,200,1200);
//    }
//
//    /**
//     * 设置动画
//     * @param propertyName
//     * @param path
//     */
//    private void startAnimatorPath(String propertyName, AnimatorPath path) {
//        ObjectAnimator anim = ObjectAnimator.ofObject(
//                this, propertyName, new PathEvaluator(), path.getPoints().toArray());
//        anim.setInterpolator(new DecelerateInterpolator());//动画插值器
//        anim.setDuration(3000);
//        anim.start();
//    }
}
