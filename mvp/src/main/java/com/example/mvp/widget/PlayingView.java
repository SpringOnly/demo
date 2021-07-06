package com.example.mvp.widget;


import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.LinearInterpolator;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.SizeUtils;
import com.example.commonlibrary.util.LogUtil;

public class PlayingView extends View implements ViewTreeObserver.OnGlobalLayoutListener {

    private Paint mPaintTop;
    private Paint mPaintBottom;
    //线条的宽度
    private int LineWidth = SizeUtils.dp2px(2);
    private int LineHeight =  SizeUtils.dp2px(35);

    //view的中间位置
    private int centerY;
    private int DrawLineHeight;


    public PlayingView(Context context) {
        super(context);
        initView();
    }

    public PlayingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        mPaintTop = new Paint();
        mPaintTop.setStrokeWidth(LineWidth);
        mPaintTop.setColor(Color.RED);
        mPaintTop.setAntiAlias(true);

        mPaintBottom = new Paint();
        mPaintBottom.setStrokeWidth(LineWidth);
        mPaintBottom.setColor(Color.RED);
        mPaintBottom.setAntiAlias(true);

        getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int height = 0;
        int width = 0;

        switch (widthMode) {
            case MeasureSpec.EXACTLY:
                width = widthSize;
                break;
            case MeasureSpec.AT_MOST:
                width = Math.min(LineWidth, widthSize);
                break;
            case MeasureSpec.UNSPECIFIED:
                break;
        }

        switch (heightMode) {
            case MeasureSpec.EXACTLY:
                height = heightSize;
                break;
            case MeasureSpec.AT_MOST:
                height = Math.min(LineHeight, heightSize);
                break;
            case MeasureSpec.UNSPECIFIED:
                break;
        }
        centerY = height / 2;
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(0, centerY);
        canvas.drawRect(0, 0, LineWidth, -DrawLineHeight, mPaintTop);
        canvas.drawRect(0, 0, LineWidth, DrawLineHeight, mPaintBottom);
    }


    @Override
    public void onGlobalLayout() {
        int HalfHeight = getHeight() / 2;
        ValueAnimator valueAnimator = ValueAnimator.ofInt(5, HalfHeight);
        valueAnimator.setDuration(800);
        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(valueAnimators -> {
            DrawLineHeight = (int) valueAnimators.getAnimatedValue();
            postInvalidate();
        });
        valueAnimator.start();
    }
}
