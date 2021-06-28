package com.example.mvp.widget;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.commonlibrary.util.LogUtil;

public class DynamicCircleView extends View {

    private Paint mPaint;
    //最小的半径
    private int circleRadius = 300;
    //显示当前高度的百分比
    private float mProgress;

    int measureWidth = 0;
    int measureHeight = 0;

    public DynamicCircleView(Context context) {
        super(context);
        initView();
    }

    public DynamicCircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public DynamicCircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setDither(true);
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLACK);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

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
        setMeasuredDimension(measureWidth, measureHeight);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        LogUtil.e("onSizeChange:" + h);
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
}
