package amd.example.mvp.demo;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;


import static android.content.Context.WINDOW_SERVICE;

import com.example.mvp.R;

public class FloatingServiceDemo {

    private static WindowManager windowManager;
    private static WindowManager.LayoutParams layoutParams;

    private static View ContentView;
    private static int x;
    private static int y;

    @SuppressLint("ClickableViewAccessibility")
    public static void showFloatingWindow(Context context) {
        windowManager = (WindowManager) context.getSystemService(WINDOW_SERVICE);
        ContentView = LayoutInflater.from(context).inflate(R.layout.floating_view, null);
        ContentView.setOnTouchListener((v, event) -> {
            int action = event.getActionMasked();
            switch (action) {
                case MotionEvent.ACTION_DOWN:
                    x = (int) event.getRawX();
                    y = (int) event.getRawY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    int actionX = (int) event.getRawX();
                    int actionY = (int) event.getRawY();
                    int moveX = actionX - x;
                    int moveY = actionY - y;
                    x = actionX;
                    y = actionY;
                    layoutParams.x = layoutParams.x + moveX;
                    layoutParams.y = layoutParams.y + moveY;
                    windowManager.updateViewLayout(ContentView, layoutParams);
                    break;
            }
            return false;
        });
        layoutParams = new WindowManager.LayoutParams();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            layoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else {
            layoutParams.type = WindowManager.LayoutParams.TYPE_PHONE;
        }
        layoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        layoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        windowManager.addView(ContentView, layoutParams);
    }
}
