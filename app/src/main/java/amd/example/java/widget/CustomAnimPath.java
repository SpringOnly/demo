package amd.example.java.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @author Created by on LvJP 2022/5/27
 */
public class CustomAnimPath extends View {
    private Paint mPaint;
    private Path mPath;

    public CustomAnimPath(Context context) {
        super(context);
        initView();
    }

    public CustomAnimPath(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        mPaint = new Paint();
        mPaint.setStrokeWidth(8);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);

        mPath = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPath.moveTo(60,60);
        mPath.lineTo(460,460);
        mPath.quadTo(660, 260, 860, 460); //订单
        mPath.cubicTo(160,660,960,1060,260,1260);
//        //移动位置
//        mPath.moveTo(60, 60);
//        //连线
//        mPath.lineTo(400, 400);
//        //二阶贝塞尔曲线 控制点 终点
//        mPath.quadTo(600, 200, 800, 400);
//        //三阶贝塞尔曲线 第一个控制点 第二个控制点 终点
//        mPath.cubicTo(100, 600, 700, 800, 200, 1200);
        canvas.drawPath(mPath, mPaint);
    }
}
