package amd.example.java.widget.pathAnim;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author Created by on LvJP 2022/5/31
 */

public class PathView extends View {

    private Paint paint;
    private Path mPath;

    public PathView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        paint = new Paint();
        //抗锯齿
        paint.setAntiAlias(true);
        //防抖动
        paint.setDither(true);
        //设置画笔未实心
        paint.setStyle(Paint.Style.STROKE);
        //设置颜色
        paint.setColor(Color.GREEN);
        //设置画笔宽度
        paint.setStrokeWidth(3);
        mPath = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPath.moveTo(60, 60);
        mPath.lineTo(460, 460);
        mPath.quadTo(660, 260, 860, 460); //订单
        mPath.cubicTo(160, 660, 960, 1060, 260, 1260);
        canvas.drawPath(mPath, paint);
    }
}