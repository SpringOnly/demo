package amd.example.java.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;
import android.widget.Scroller;

import androidx.annotation.Nullable;

import amd.example.commonlibrary.util.CommonLog;

public class MyViewGroup extends LinearLayout {

    Scroller mScroller;

    public MyViewGroup(Context context) {
        this(context, null);
    }

    public MyViewGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mScroller = new Scroller(context, new LinearInterpolator());
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            int currX = mScroller.getCurrY();
            scrollTo(0, mScroller.getCurrY());
            postInvalidate();
        }
    }

    public void beginScroll() {
        mScroller.startScroll(0, 0, 0, -500, 2000);
        invalidate();
    }
}
