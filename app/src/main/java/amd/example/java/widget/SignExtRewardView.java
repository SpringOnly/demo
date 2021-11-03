package amd.example.java.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.SizeUtils;

import java.util.ArrayList;
import java.util.List;

import amd.example.demo.R;

public class SignExtRewardView extends View {

    //画线
    private Paint mLinePaint;
    //画圆
    private Paint mRoundPaint;
    //线条填充颜色
    private int FILL_COLOR = Color.parseColor("#CCB3FF");
    //线条未填充颜色
    private int UN_FILL_COLOR = Color.parseColor("#F6F5F8");
    //小圆填充颜色
    private int MIN_FILL_COLOR = Color.parseColor("#9E34F9");
    //小圆未填充颜色
    private int MIN_FILL_UN_COLOR = Color.parseColor("#A5A4A6");
    //大圆未填充颜色
    private int MAX_FILL_UN_COLOR = Color.parseColor("#E4E3E6");
    //线条的高度半径
    private int LineHeightRadius = SizeUtils.dp2px(2);

    //小圆的半径
    int minCircleRadius = SizeUtils.dp2px(4);
    //大圆的半径
    int maxCircleRadius = SizeUtils.dp2px(6);

    private RectF mRectF;

    public SignExtRewardView(Context context) {
        this(context, null);
    }

    public SignExtRewardView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int width = 0;

        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        }
        setMeasuredDimension(width, maxCircleRadius * 2);
    }

    private void initView() {

        mLinePaint = new Paint();
        mLinePaint.setColor(UN_FILL_COLOR);
        mLinePaint.setStyle(Paint.Style.FILL);

        mRoundPaint = new Paint();
        mRoundPaint.setColor(MAX_FILL_UN_COLOR);
        mRoundPaint.setStyle(Paint.Style.FILL);
        mRoundPaint.setAntiAlias(true);

        mRectF = new RectF();
    }

    TestAA test;
    List<TestAA> list = new ArrayList<>();

    int centerHeight;

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //第一个圆点初始位置
        int firstPosition = SizeUtils.dp2px(40);
        //第二个圆点的位置
        int secondPosition = getWidth() / 2;
        //第三个圆点的位置
        int thirdPosition = getWidth() - SizeUtils.dp2px(40);
        //第四个圆点的位置
        int fourPosition = getWidth();
        //控件高度中间的位置
        centerHeight = getHeight() / 2;

        test = new TestAA();

        test.top = centerHeight - LineHeightRadius;
        test.bottom = centerHeight + LineHeightRadius;
        test.left = 0;
        test.right = firstPosition;
        test.paintColor = UN_FILL_COLOR;
        test.minColor = MIN_FILL_UN_COLOR;
        list.add(test);
        test = null;

        test = new TestAA();
        test.top = centerHeight - LineHeightRadius;
        test.bottom = centerHeight + LineHeightRadius;
        test.left = firstPosition;
        test.right = secondPosition;
        test.paintColor = UN_FILL_COLOR;
        test.minColor = MIN_FILL_UN_COLOR;
        list.add(test);
        test = null;

        test = new TestAA();
        test.top = centerHeight - LineHeightRadius;
        test.bottom = centerHeight + LineHeightRadius;
        test.left = secondPosition;
        test.right = thirdPosition;
        test.paintColor = UN_FILL_COLOR;
        test.minColor = MIN_FILL_UN_COLOR;
        list.add(test);
        test = null;

        test = new TestAA();
        test.top = centerHeight - LineHeightRadius;
        test.bottom = centerHeight + LineHeightRadius;
        test.left = thirdPosition;
        test.right = fourPosition;
        test.paintColor = UN_FILL_COLOR;
        list.add(test);
        test = null;
    }


    //设置当前已经签到第几天了
    public void setSignDay(int day) {
        if (day >= 3 && day < 5) {
            list.get(0).setPaintColor(FILL_COLOR);
            list.get(0).setMinColor(MIN_FILL_COLOR);
        }
        if (day >= 5 && day < 7) {
            for (int i = 0; i < 2; i++) {
                list.get(i).setPaintColor(FILL_COLOR);
                list.get(i).setMinColor(MIN_FILL_COLOR);
            }
        }
        if (day >= 7) {
            for (int i = 0; i < 4; i++) {
                list.get(i).setPaintColor(FILL_COLOR);
                list.get(i).setMinColor(MIN_FILL_COLOR);
            }
        }
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (int i = 0; i < list.size(); i++) {
            mRectF.left = list.get(i).getLeft();
            mRectF.top = list.get(i).getTop();
            mRectF.right = list.get(i).getRight();
            mRectF.bottom = list.get(i).getBottom();
            mLinePaint.setColor(list.get(i).getPaintColor());
            canvas.drawRoundRect(mRectF, SizeUtils.dp2px(4), SizeUtils.dp2px(4), mLinePaint);
        }
        for (int i = 0; i < 3; i++) {
            mLinePaint.setColor(list.get(i).getPaintColor());
            canvas.drawCircle(list.get(i).getRight(), centerHeight, maxCircleRadius, mLinePaint);
        }

        for (int i = 0; i < 3; i++) {
            mRoundPaint.setColor(list.get(i).getMinColor());
            canvas.drawCircle(list.get(i).getRight(), centerHeight, minCircleRadius, mRoundPaint);
        }
    }

    public static class TestAA {
        private float top;
        private float bottom;
        private float left;
        private float right;
        private int paintColor;
        private int minColor;

        public int getMinColor() {
            return minColor;
        }

        public void setMinColor(int minColor) {
            this.minColor = minColor;
        }

        public float getTop() {
            return top;
        }

        public void setTop(float top) {
            this.top = top;
        }

        public float getBottom() {
            return bottom;
        }

        public void setBottom(float bottom) {
            this.bottom = bottom;
        }

        public float getLeft() {
            return left;
        }

        public void setLeft(float left) {
            this.left = left;
        }

        public float getRight() {
            return right;
        }

        public void setRight(float right) {
            this.right = right;
        }

        public int getPaintColor() {
            return paintColor;
        }

        public void setPaintColor(int paintColor) {
            this.paintColor = paintColor;
        }
    }
}
