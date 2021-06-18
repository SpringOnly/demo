package com.example.mvp.widget;


import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

public class FatArrowView extends RelativeLayout {
    public FatArrowView(Context context) {
        super(context);
    }

    public FatArrowView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {

        }
        return super.onTouchEvent(event);
    }
}
