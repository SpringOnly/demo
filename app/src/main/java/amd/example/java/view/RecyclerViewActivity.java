package amd.example.java.view;

import android.annotation.SuppressLint;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;

import com.alibaba.android.arouter.facade.annotation.Route;

import amd.example.commonlibrary.base.BaseActivity;
import amd.example.commonlibrary.base.arouter.RouterPath;
import amd.example.demo.R;
import amd.example.demo.databinding.ActivityViewPagerTwoBinding;
import amd.example.java.bean.SnapHelperBean;
import amd.example.java.view.adapter.SnapHelperAdapter;
import amd.example.java.widget.GallerySnapHelper;
import amd.example.java.widget.TestSnapHelper;

@Route(path = RouterPath.RECYCLER)
public class RecyclerViewActivity extends BaseActivity<ActivityViewPagerTwoBinding> {

    @Override
    protected ActivityViewPagerTwoBinding getViewBinding() {
        return ActivityViewPagerTwoBinding.inflate(getLayoutInflater());
    }


    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void initView() {
        SnapHelperAdapter adapter = new SnapHelperAdapter();
//        TestSnapHelper snapHelper = new TestSnapHelper();
//        snapHelper.attachToRecyclerView(mBinding.recyclerView);
        new GallerySnapHelper().attachToRecyclerView(mBinding.recyclerView);

        mBinding.recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mBinding.recyclerView.setLayoutManager(layoutManager);

        for (int i = 0; i < 20; i++) {
            SnapHelperBean snapHelperBean = new SnapHelperBean();
            snapHelperBean.setBackground(R.mipmap.image);
            adapter.getData().add(snapHelperBean);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }
}