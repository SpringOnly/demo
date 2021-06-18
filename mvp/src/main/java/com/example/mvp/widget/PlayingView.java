package com.example.mvp.widget;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.BoringLayout;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.SizeUtils;
import com.example.commonlibrary.util.LogUtil;

import java.util.Random;

import static android.animation.ValueAnimator.INFINITE;

public class PlayingView extends View {
    private Paint mPaint;
    private Random mRandom;


    public PlayingView(Context context) {
        super(context);
        initView();
    }


    public PlayingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        mPaint = new Paint();
        mPaint.setStrokeWidth(SizeUtils.dp2px(10));
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLUE);
        mRandom = new Random();
    }

    private int LineY = 0;


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawLine(0, LineY, 0, getHeight(), mPaint);
    }


    public void startAnim() {
        int measuredHeight = getMeasuredHeight();
        ValueAnimator valueAnimator = ObjectAnimator.ofInt(LineY, getHeight());
        valueAnimator.setRepeatCount(INFINITE);
        valueAnimator.setDuration(500);
        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                LineY = (int) animation.getAnimatedValue();
                invalidate();
            }
        });
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                startAnim();
            }
        });
        valueAnimator.start();
    }

}

