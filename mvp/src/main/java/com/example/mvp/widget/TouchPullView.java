package com.example.mvp.widget;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.commonlibrary.util.LogUtil;

/**
 * view高度跟随手势
 */
public class TouchPullView extends View {

    private Paint mPaint;
    //最小的半径
    private int circleRadius = 300;
    //显示当前高度的进度
    private float mProgress;

    int measureWidth = 0;
    int measureHeight = 0;

    public TouchPullView(Context context) {
        super(context);
        initView();
    }

    public TouchPullView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public TouchPullView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setDither(true);
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLACK);


        /**
         * 二阶曲线
         */
        //从左上角的距离
//        mPath.quadTo(400, 0, 600, 200);
        //从移动后的距离moveTo
        //mPath.rQuadTo(200, -300, 400, 0);

        /**
         * 三阶曲线
         */
        //从左上角的距离
        // mPath.cubicTo(180, 0, 400, 0, 500, 200);
        //从移动后的距离moveTo
//        mPath.rCubicTo(-20, -200, 200, -200, 300, 0);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        float height;

        switch (widthMode) {
            case MeasureSpec.EXACTLY:
                measureWidth = widthSize;
                break;
            case MeasureSpec.AT_MOST:
                measureWidth = Math.min(circleRadius, widthSize);
                break;
            case MeasureSpec.UNSPECIFIED:
                break;
        }

        switch (heightMode) {
            case MeasureSpec.EXACTLY:
                measureHeight = heightSize;
                break;
            case MeasureSpec.AT_MOST:
                measureHeight = Math.min(circleRadius, heightSize);
                break;
            case MeasureSpec.UNSPECIFIED:
                break;
        }
        height = measureHeight * mProgress + 0.5f + getPaddingTop() + getPaddingBottom();
        setMeasuredDimension(measureWidth, (int) height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(measureWidth / 2f, measureHeight / 2f, measureHeight / 2f, mPaint);
    }

    public void setProgress(float progress) {
        mProgress = progress;
        requestLayout();
        LogUtil.e("progress:" + progress);
    }

    private void initBezierView() {
        float[] xPoint = {0, 200, 500, 700, 800, 500, 600};
        float[] yPoint = {0, 700, 1200, 200, 800, 1300, 600};

//        mPath = new Path();
//
//        int fps = 1000;
//        for (int i = 0; i < fps; i++) {
//            float progress = (float) i / fps;
//            float x = calculateBezier(progress, xPoint);
//            float y = calculateBezier(progress, yPoint);
//            mPath.lineTo(x, y);
//        }
    }


    /**
     *
     * 计算贝塞尔曲线的位置
     *
     * @param t  进度t
     * @param value 传入需要被计算的坐标
     * @return 当前Time时刻的坐标
     */
    private float calculateBezier(float t, float... value) {
        final int length = value.length;
        for (int i = length - 1; i > 0; i--) {

            for (int j = 0; j < i; j++) {
                value[j] = value[j] + (value[j + 1] - value[j]) * t;
            }
        }
        return value[0];
    }
}
