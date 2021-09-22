package amd.example.java.view;
import android.view.GestureDetector;
import android.view.MotionEvent;

import amd.example.commonlibrary.base.BaseFragment;
import amd.example.commonlibrary.util.CommonLog;
import amd.example.demo.databinding.FragmentViewPagerBinding;

public class ViewPagerFragment extends BaseFragment<FragmentViewPagerBinding> {

    private String result;
    private GestureDetector mGestureDetector;
    private int mCurrPosition;

    public ViewPagerFragment(String data) {
        result = data;
        CommonLog.e(data);
    }

    public void setViewPagerChange(int position) {
        CommonLog.e("viewpager 发生改变了:" + position);
        CommonLog.e("原始数据：" + result);
    }

    @Override
    public void initView() {
        mBinding.textView.setText(result);
        mGestureDetector = new GestureDetector(getActivity(),
                new GestureDetector.SimpleOnGestureListener() {
                    @Override
                    public boolean onDown(MotionEvent e) {
                        CommonLog.e("action:onDown" + "当前fragment 下标：" + mCurrPosition);
                        return super.onDown(e);
                    }
                }
        );
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    public void exitFragment(int position) {
        CommonLog.e("下标为" + position + "调用销毁操作");
    }

    public boolean onTouchEvent(MotionEvent ev, int currPosition) {
        mCurrPosition = currPosition;
        return mGestureDetector.onTouchEvent(ev);
    }

    public void resetFragment(int position) {
        //所有的重置操作在这里 但是不销毁Fragment
        CommonLog.e("数据为:" + result + "的fragment重置了状态 下标为：" + position);
    }
}