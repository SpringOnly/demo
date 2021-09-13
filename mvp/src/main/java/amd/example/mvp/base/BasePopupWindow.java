package amd.example.mvp.base;


import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

import androidx.annotation.CallSuper;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;

public abstract class BasePopupWindow  extends PopupWindow implements DialogInterface {

    protected final String TAG = getClass().getSimpleName();

    protected View mView;

    public BasePopupWindow(@NonNull Context context, int width, int height) {
        super(context);

        mView = LayoutInflater.from(context).inflate(getLayoutId(), null);
        setContentView(mView);

        setWidth(width);
        setHeight(height);
    }

    public View getView() {
        return mView;
    }

    /**
     * 返回布局Id
     */
    @LayoutRes
    protected abstract int getLayoutId();

    @Override
    public void showAsDropDown(View anchor, int xoff, int yoff, int gravity) {
        super.showAsDropDown(anchor, xoff, yoff, gravity);
        onShow();
    }

    @Override
    public void showAtLocation(View parent, int gravity, int x, int y) {
        super.showAtLocation(parent, gravity, x, y);
        onShow();
    }

    @CallSuper
    protected void onShow() {

    }

    /**
     * 显示PopupWindow
     *
     * @param v 附着对象
     */
    public void show(View v) {
        show(v, 0, 0);
    }

    /**
     * 显示PopupWindow
     *
     * @param v 附着对象
     * @param x 偏移量x
     * @param y 偏移量y
     */
    public void show(View v, int x, int y) {
        int[] location = new int[2];
        v.getLocationInWindow(location);

        View contentView = getContentView();
        if (contentView.getMeasuredHeight() == 0) {
            contentView.measure(0, 0);
        }

        showAtLocation(
                v, Gravity.NO_GRAVITY,
                location[0] + v.getWidth() / 2 - contentView.getMeasuredWidth() / 2 + x,
                location[1] - contentView.getMeasuredHeight() + y);
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }

    /**
     * 获取上下文
     */
    protected Context getContext() {
        return mView.getContext();
    }

    /**
     * 启动触摸后隐藏
     */
    protected void enableTouchHide() {
        setBackgroundDrawable(new BitmapDrawable());
        setOutsideTouchable(true);
        setFocusable(true);
    }

    @Override
    public void cancel() {
        dismiss();
    }
}
