package amd.example.java.widget.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.ToastUtils;

import amd.example.commonlibrary.util.CommonLog;

/**
 * @author Created by on LvJP 2021-11-29
 */
public class MyTest extends View {

    public MyTest(Context context) {
        super(context);
    }

    public MyTest(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        ToastUtils.showShort("拦截touch事件");
        return true;
    }
}
