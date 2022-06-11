package amd.example.java.widget.Anim;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.core.content.ContextCompat;

import java.util.Random;

import amd.example.demo.R;

/**
 * @author Created by on LvJP 2022/6/11
 * 直播点赞飘心动画
 */
public class LoveLayout extends RelativeLayout {
    private Context mContext;
    private LayoutParams mParams;
    private Drawable[] icons = new Drawable[4];
    private Interpolator[] interpolators = new Interpolator[4];
    private int mWidth;
    private int mHeight;

    public LoveLayout(Context context) {
        this(context, null);
    }

    public LoveLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    private void init() {
        icons[0] = ContextCompat.getDrawable(mContext, R.mipmap.green);
        icons[1] = ContextCompat.getDrawable(mContext, R.mipmap.purple);
        icons[2] = ContextCompat.getDrawable(mContext, R.mipmap.red);
        icons[3] = ContextCompat.getDrawable(mContext, R.mipmap.yellow);

        interpolators[0] = new AccelerateDecelerateInterpolator(); // 在动画开始与结束的地方速率改变比较慢，在中间的时候加速
        interpolators[1] = new AccelerateInterpolator(); // 在动画开始的地方速率改变比较慢，然后开始加速
        interpolators[2] = new DecelerateInterpolator(); // 在动画开始的地方快然后慢
        interpolators[3] = new LinearInterpolator(); // 以常量速率改变

        //第一张图片  实际在屏幕上显示的宽高
        int width = icons[0].getIntrinsicWidth();
        int height = icons[1].getIntrinsicHeight();
        mParams = new LayoutParams(width, height);

        //在下中位置
        mParams.addRule(CENTER_HORIZONTAL, TRUE);
        mParams.addRule(ALIGN_PARENT_BOTTOM, TRUE);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
    }

    //界面里面调用这个就可以
    public void addLoveView() {
        ImageView imageView = new ImageView(mContext);
        imageView.setLayoutParams(mParams);
        imageView.setImageDrawable(icons[new Random().nextInt(4)]);

        addView(imageView);

        AnimatorSet animatorSet = getAnimSet(imageView);
        animatorSet.start();
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                removeView(imageView);
            }
        });
    }

    private AnimatorSet getAnimSet(ImageView imageView) {
        // 1.透明度动画
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(imageView, "alpha", 0.3f, 1f);

        // 2.缩放动画
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(imageView, "scaleX", 0.2f, 1f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(imageView, "scaleY", 0.2f, 1f);

        //图片本身动画同时执行
        AnimatorSet set = new AnimatorSet();
        set.playTogether(objectAnimator, scaleX, scaleY);
        set.setDuration(500);

        //向上路径动画
        ValueAnimator bzierAnim = getBzierAnimator(imageView);

        //图片本身动画结束 播放路径动画 按照顺序执行
        AnimatorSet set2 = new AnimatorSet();
        set2.playSequentially(set, bzierAnim);
        set2.setTarget(imageView);
        return set2;
    }

    private ValueAnimator getBzierAnimator(ImageView imageView) {
        PointF[] point = getPoint();
        //两个控制点 p1和p2
        BaseEvaluator evaluator = new BaseEvaluator(point[1], point[2]);
        //开始点和结束点 p0和p3
        ValueAnimator valueAnimator = ValueAnimator.ofObject(evaluator, point[0], point[3]);
        valueAnimator.addUpdateListener(animation -> {
            PointF pointF = (PointF) valueAnimator.getAnimatedValue();
            imageView.setX(pointF.x);
            imageView.setY(pointF.y);
            imageView.setAlpha(1 - valueAnimator.getAnimatedFraction());
        });
        valueAnimator.setTarget(imageView);
        valueAnimator.setDuration(3000);
        valueAnimator.setInterpolator(interpolators[new Random().nextInt(4)]);
        return valueAnimator;
    }

    private PointF[] getPoint() {
        PointF[] pointFS = new PointF[4];
        //p0
        pointFS[0] = new PointF();
        //控件宽度(目前也就是屏幕宽度)/2 - 图标宽度/2  = 中间左偏移
        pointFS[0].x = (mWidth / 2f) - (mParams.width / 2f);
        //控件高度-图标高度 = 底部向上偏移
        pointFS[0].y = mHeight - mParams.height;

        //p1
        pointFS[1] = new PointF();
        //屏幕左半宽度的随机值
        pointFS[1].x = new Random().nextInt(mWidth / 2);
        //屏幕高度的4/3
        pointFS[1].y = (mHeight / 4f) * 3;

        //p2
        pointFS[2] = new PointF();
        //屏幕右半宽度的随机值 指定右半个
        pointFS[2].x = new Random().nextInt(mWidth / 2) + (mWidth / 2f);
        //屏幕高度的4/1
        pointFS[2].y = (mHeight / 4f);

        //p3
        pointFS[3] = new PointF();
        //结束点宽度 屏幕宽度随机值
        pointFS[3].x = new Random().nextInt(mWidth);
        //结束点高度 屏幕最顶部
        pointFS[3].y = 0;
        return pointFS;
    }
}
