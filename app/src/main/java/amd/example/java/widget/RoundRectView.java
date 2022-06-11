package amd.example.java.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import amd.example.commonlibrary.util.CommonLog;

/**
 * @author Created by on LvJP 2022/6/1
 */
public class RoundRectView extends View {
    private Paint mPaint;

    private RectF mRectF;

    //透明度
    private float mAlpha = 1;
    //半径
    private float mRadius = 30;
    //绘制的背景颜色
    private int drawColor = Color.GREEN;

    public RoundRectView(Context context) {
        this(context, null);
    }

    public RoundRectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(drawColor);

        setAlpha(mAlpha);
        mRectF = new RectF();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mRectF.left = 0;
        mRectF.top = 0;
        mRectF.right = getWidth();
        mRectF.bottom = getHeight();
        canvas.drawRoundRect(mRectF, mRadius, mRadius, mPaint);
    }
}
