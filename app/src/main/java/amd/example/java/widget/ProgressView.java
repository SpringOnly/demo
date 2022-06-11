package amd.example.java.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.ImageUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.SizeUtils;

import amd.example.commonlibrary.util.CommonLog;
import amd.example.demo.R;

/**
 * @author Created by on LvJP 2022/6/1
 */
public class ProgressView extends View {
    private Paint mPaint;
    private RectF mRect;
    private Bitmap bitmap;

    //进度条高度
    private float mProgressHeight = 30;
    //圆角的半径
    private float mRoundRadius = 30;
    //进度条的总长度
    private int mProgressWidget;
    //当前进度的颜色
    private int mCurrentProgressColor = Color.parseColor("#3B78FF");
    //当前进度
    private int mCurrentProgress = 0;

    public ProgressView(Context context) {
        this(context, null);
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        //右边距
        int endX = ScreenUtils.getScreenWidth() - SizeUtils.dp2px(10);
        //左边距
        int startX = SizeUtils.dp2px(10);
        mProgressWidget = endX - startX;

        //当前进度条的图片
        bitmap = ImageUtils.getBitmap(R.mipmap.ic_launcher);
        //根据图片位置计算进度条绘制的位置
        float startY = bitmap.getHeight() / 2f - (mProgressHeight / 2);
        float endY = bitmap.getHeight() / 2f + (mProgressHeight / 2);
        mRect = new RectF(startX, startY, endX, endY);
    }

    /**
     * 设置当前进度
     */
    public void setProgress(int progress) {
        mCurrentProgress = (mProgressWidget / progress);
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //宽度不变 高度采用图片高度
        setMeasuredDimension(getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec),
                bitmap.getHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制背景色
        mPaint.setColor(Color.WHITE);
        canvas.drawRoundRect(mRect, mRoundRadius, mRoundRadius, mPaint);
        //绘制当前进度
        mPaint.setColor(mCurrentProgressColor);
        mRect.right = mCurrentProgress;
        canvas.drawRoundRect(mRect, mRoundRadius, mRoundRadius, mPaint);
        canvas.save();
        //绘制图片
        float startX = mRect.right - (bitmap.getWidth() / 2f);
        canvas.drawBitmap(bitmap, startX, 0, mPaint);
    }
}
