package amd.example.main.view;

import android.view.MotionEvent;

import androidx.viewpager2.widget.ViewPager2;

import com.alibaba.android.arouter.facade.annotation.Route;
import amd.example.commonlibrary.base.BaseActivity;
import amd.example.commonlibrary.base.arouter.RouterPath;
import amd.example.commonlibrary.util.CommonLog;
import com.example.main.databinding.ActivityViewPagerTwoBinding;
import amd.example.main.view.adapter.ViewPagerAdapter;

import java.util.List;

@Route(path = RouterPath.ViewPagerTwo)
public class ViewPagerTwoActivity extends BaseActivity<ActivityViewPagerTwoBinding> {

    //上一个选中的下标
    private int lastPosition = -1;
    //当前选中的下标
    private int currPosition = -1;

    //初始化的时候默认选中的下标
    private int selectPosition = -1;

    List<ViewPagerFragment> fragmentList;

    @Override
    protected ActivityViewPagerTwoBinding getViewBinding() {
        return ActivityViewPagerTwoBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView() {
//        selectPosition = 1;
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);
        mBinding.viewPager.setAdapter(viewPagerAdapter);
        fragmentList = viewPagerAdapter.getFragmentList();

        if (selectPosition != -1) {
            mBinding.viewPager.setCurrentItem(selectPosition, false);
            lastPosition = selectPosition;
            currPosition = selectPosition;
            fragmentList.get(selectPosition).setViewPagerChange(selectPosition);
        }
        mBinding.viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                currPosition = position;
                CommonLog.e("view pager change position:" + position);
                if (lastPosition == -1) {
                    lastPosition = position;
                } else {
                    fragmentList.get(lastPosition).resetFragment(lastPosition);
                }

                for (int i = 0; i < fragmentList.size(); i++) {
                    if (position == i) {
                        fragmentList.get(i).setViewPagerChange(position);
                        break;
                    }
                }
            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean isDispatch = fragmentList.get(currPosition).onTouchEvent(ev, currPosition);
        if (isDispatch) {
            return true;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        fragmentList.get(currPosition).exitFragment(currPosition);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }
}