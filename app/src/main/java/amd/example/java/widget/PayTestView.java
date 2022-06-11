package amd.example.java.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @author Created by on LvJP 2022/5/25
 * 仿支付宝加载动画
 */
public class PayTestView extends View {

    private float mProgress; // 代表动画当前进度

    private Paint mBluePaint; // 蓝色画笔

    private ValueAnimator mLoadingAnimator;
    private PathMeasure mLoadingPathMeasure;
    private Path mDstPath; // 保存PathMeasure切割后的内容

    public PayTestView(Context context) {
        super(context);
        init();
    }

    public PayTestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PayTestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setLayerType(LAYER_TYPE_SOFTWARE, null); // 取消硬件加速
        // 画笔设置
        mBluePaint = new Paint(Paint.ANTI_ALIAS_FLAG); // 画笔抗锯齿
        mBluePaint.setColor(Color.BLUE);
        mBluePaint.setStyle(Paint.Style.STROKE);
        mBluePaint.setStrokeWidth(10);
        mBluePaint.setStrokeCap(Paint.Cap.ROUND);
        // 新建 PathMeasure
        Path loadingPath = new Path();
        //一个完整的圆轨迹
        loadingPath.addCircle(100, 100, 60, Path.Direction.CW); // CW代表顺时针
        //把圆添加到path里面
        mLoadingPathMeasure = new PathMeasure(loadingPath, false);
        mDstPath = new Path();
        // 动画
        mLoadingAnimator = ValueAnimator.ofFloat(0, 1);
        mLoadingAnimator.setDuration(1500);
        mLoadingAnimator.setRepeatMode(ValueAnimator.RESTART);
        mLoadingAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mLoadingAnimator.addUpdateListener(animation -> {
            mProgress = (float) animation.getAnimatedValue();
            invalidate();
        });
        mLoadingAnimator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mDstPath.reset();
        float stop = mLoadingPathMeasure.getLength() * mProgress;
        mLoadingPathMeasure.getSegment(0, stop, mDstPath, true);
        canvas.drawPath(mDstPath, mBluePaint);
    }
}
