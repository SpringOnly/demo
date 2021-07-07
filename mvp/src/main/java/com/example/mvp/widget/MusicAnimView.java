package com.example.mvp.widget;


import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.blankj.utilcode.util.SizeUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 音乐播放动画
 */
public class MusicAnimView extends View {

    private Paint mPaintTop;
    private Paint mPaintBottom;

    //线条的宽度
    private final int LineWidth = SizeUtils.dp2px(2);
    //线条的高度
    private final int LineMaxHeight = SizeUtils.dp2px(15);
    //线条最低高度
    private final int LineMinHeight = SizeUtils.dp2px(3);
    //线条间距
    private final int LineInterval = SizeUtils.dp2px(2);
    //线条圆角的大小
    private final int LineCircleRadius = SizeUtils.dp2px(3);
    //线条的数量
    private final int LineNumber = 3;
    //view的中间位置
    private int centerY;
    //是否正在播放中
    private boolean isPlaying = false;

    List<Point> mPointList;
    ValueAnimator valueAnimator;

    public MusicAnimView(Context context) {
        super(context);
        initView();
    }

    public MusicAnimView(Context context, @Nullable AttributeSet attrs) {
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

        mPointList = new ArrayList<>();
        for (int i = 0; i < LineNumber; i++) {
            Point point = new Point();
            point.setLineHeight(0);
            mPointList.add(point);
        }
        post(this::startAnim);
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
                width = (LineWidth + LineInterval) * LineNumber;
                break;
            case MeasureSpec.UNSPECIFIED:
                break;
        }

        switch (heightMode) {
            case MeasureSpec.EXACTLY:
                height = heightSize;
                break;
            case MeasureSpec.AT_MOST:
                height = Math.min(LineMaxHeight, heightSize);
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
        int LineLeft = 0;
        for (int i = 0; i < mPointList.size(); i++) {
            int height = mPointList.get(i).getLineHeight();
            canvas.drawRoundRect(LineLeft, 0, LineLeft + LineWidth, -height, LineCircleRadius, LineCircleRadius, mPaintTop);
            canvas.drawRoundRect(LineLeft, 0, LineLeft + LineWidth, height, LineCircleRadius, LineCircleRadius, mPaintBottom);

            canvas.drawRect(LineLeft, 0, LineLeft + LineWidth, -LineMinHeight, mPaintTop);
            canvas.drawRect(LineLeft, 0, LineLeft + LineWidth, LineMinHeight, mPaintBottom);
            LineLeft += LineWidth + LineInterval;
        }
    }

    @Override
    protected void onVisibilityChanged(@NonNull View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        if (visibility == VISIBLE) {
            isPlaying = true;
            startAnim();
        } else {
            isPlaying = false;
            stopAnim();
        }
    }

    public void startAnim() {
        if (valueAnimator == null) {
            valueAnimator = new ValueAnimator();
        }
        valueAnimator.setIntValues(LineMinHeight, centerY);
        valueAnimator.setDuration(800);
        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(animator -> {
            if (!isPlaying) {
                return;
            }
            //计算线条跳动的规律
            int MeasureHeight = (int) animator.getAnimatedValue();
            for (int i = 0; i < mPointList.size(); i++) {
                if (i == 0 || i == 2) {
                    int i2 = centerY - MeasureHeight + LineMinHeight;
                    mPointList.get(i).setLineHeight(i2);
                }
                if (i == 1) {
                    mPointList.get(i).setLineHeight(MeasureHeight);
                }
            }
            postInvalidate();
        });
        valueAnimator.start();
    }

    public void stopAnim() {
        if (valueAnimator != null) {
            valueAnimator.cancel();
            valueAnimator = null;
        }
    }

    public static class Point {
        private int LineHeight;

        public int getLineHeight() {
            return LineHeight;
        }

        public void setLineHeight(int lineHeight) {
            LineHeight = lineHeight;
        }
    }
}

