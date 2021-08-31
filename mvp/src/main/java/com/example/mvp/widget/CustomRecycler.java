package com.example.mvp.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.commonlibrary.util.CommonLog;

public class CustomRecycler extends RecyclerView {

    public CustomRecycler(@NonNull Context context) {
        this(context, null);
    }

    public CustomRecycler(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomRecycler(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //记录按下的坐标 不拦截
    float downX = 0;
    //记录移动的坐标
    float moveX = 0;


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int actionMasked = ev.getActionMasked();
        switch (actionMasked) {
            case MotionEvent.ACTION_DOWN:
                downX = ev.getRawX();
                CommonLog.e("ACTION_DOWN:" + downX);
                return super.dispatchTouchEvent(ev);
            case MotionEvent.ACTION_MOVE:
                //移动过程中的坐标
                moveX = ev.getRawX();
                return super.dispatchTouchEvent(ev);
            case MotionEvent.ACTION_UP:
                CommonLog.e("ACTION_UP");
                return true;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int actionMasked = ev.getActionMasked();
        switch (actionMasked) {
            case MotionEvent.ACTION_UP:
                //如果滑动的像素大于300 那么拦截
                //右滑
                CommonLog.e("result right:" + (moveX - downX));
                if (moveX - downX > 300) {
                    return true;
                }
                //左滑
                CommonLog.e("result left:" + (downX - moveX));
                if (downX - moveX > 300) {
                    return true;
                }
                return super.onTouchEvent(ev);
        }
        return super.onTouchEvent(ev);
    }
}
