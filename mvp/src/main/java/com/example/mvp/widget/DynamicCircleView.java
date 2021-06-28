package com.example.mvp.widget;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.SizeUtils;
import com.example.commonlibrary.util.LogUtil;

public class DynamicCircleView extends View {
    private Paint mPaint;
    private int circleRadius = 300;

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
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int radius = circleRadius >> 1;
        canvas.drawCircle(radius, radius, radius, mPaint);
    }

    public void setProgress(float progress) {
        LogUtil.e("progress:" + progress);
    }
}
