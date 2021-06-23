package com.example.mvp.widget;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.SizeUtils;

import java.util.ArrayList;
import java.util.List;

public class PlayingView extends View {


    private Paint mPaint;
    //线条高度
    List<Integer> height = new ArrayList<>();
    //每个线条之间的间距
    private int LineInterval = 20;
    //线条的宽度
    private int LineWidth = 5;


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
        mPaint.setStrokeWidth(SizeUtils.dp2px(LineWidth));
        mPaint.setColor(Color.RED);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }
}
