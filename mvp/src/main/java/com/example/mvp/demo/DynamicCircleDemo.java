package com.example.mvp.demo;


import android.view.MotionEvent;
import android.view.View;

import com.example.mvp.widget.DynamicCircleView;

public class DynamicCircleDemo {
    //记录点击的下标
    private static float clickY;
    //最大的滑动距离
    private static float maxHeight = 600;

    public static void setOnTouch(View rootLayout, DynamicCircleView dynamicCircle) {
        rootLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getActionMasked();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        clickY = event.getY();
                        return true;
                    case MotionEvent.ACTION_MOVE:
                        float y = event.getY();
                        if (y >= clickY) {
                            if (y - clickY >= maxHeight) {
                                dynamicCircle.setProgress(1);
                            } else {
                                float v1 = (y - clickY) / maxHeight <= 1 ? (y - clickY) / maxHeight : 1;
                                dynamicCircle.setProgress(v1);
                            }
                        }
                        return true;
                }
                return false;
            }
        });
    }
}
