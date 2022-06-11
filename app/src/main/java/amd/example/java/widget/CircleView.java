package amd.example.java.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @author Created by on LvJP 2022/6/2
 * 叠加起来的圆
 */
public class CircleView extends View {

    private Paint mPaint;
    private int radius = 30;
    private Xfermode mXfermode;

    public CircleView(Context context) {
        this(context, null);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);

        mPaint = new Paint();
        mPaint.setColor(Color.parseColor("#3B78FF"));
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        int layerId = canvas.saveLayer(0, 0, getWidth(), getHeight(), mPaint);
//        int r = getWidth() / 3;
//        //正常绘制黄色的圆形
//        mPaint.setColor(0xFFFFCC44);
//        canvas.drawCircle(r, r, r, mPaint);
//        //使用CLEAR作为PorterDuffXfermode绘制蓝色的矩形
//        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
//        mPaint.setColor(0xFF66AAFF);
//        canvas.drawRect(r, r, r * 2.7f, r * 2.7f, mPaint);
//        //最后将画笔去除Xfermode
//        mPaint.setXfermode(null);
//        canvas.restoreToCount(layerId);

        int layerId = canvas.saveLayer(0, 0, getWidth(), getHeight(), mPaint);
        canvas.drawCircle(radius, radius + 30, radius, mPaint);
        mPaint.setXfermode(mXfermode);
        mPaint.setColor(Color.GREEN);
        canvas.drawCircle(radius, radius, radius, mPaint);
        mPaint.setXfermode(null);
        canvas.restoreToCount(layerId);
    }
}
