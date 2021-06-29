package com.example.mvp.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class TestView extends View {
    private Paint mPaint;


    public TestView(Context context) {
        super(context);
        initView();
    }

    public TestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public TestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setDither(true);
        mPaint.setStrokeWidth(10);
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLACK);

        mPath = new Path();
        mPath.moveTo(200, 200);


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

    private Path mPath;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(mPath, mPaint);
        canvas.drawPoint(180, 0, mPaint);
        canvas.drawPoint(400, 0, mPaint);
        canvas.drawPoint(500, 200, mPaint);

    }
}
