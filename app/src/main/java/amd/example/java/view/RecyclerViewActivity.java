package amd.example.java.view;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.SizeUtils;

import amd.example.commonlibrary.base.BaseActivity;
import amd.example.commonlibrary.base.arouter.RouterPath;
import amd.example.commonlibrary.util.CommonLog;
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
        new PagerSnapHelper().attachToRecyclerView(mBinding.recyclerView);
        mBinding.recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent,
                                       @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                int childAdapterPosition = parent.getChildAdapterPosition(view);
                SnapHelperAdapter adapter1 = (SnapHelperAdapter) parent.getAdapter();
                if (adapter1 == null) return;
                if (adapter1.getData().size() - 1 == childAdapterPosition) {
                    outRect.right = SizeUtils.dp2px(20);
                }else if (childAdapterPosition == 0) {
                    outRect.left = SizeUtils.dp2px(20);
                } else {
                    outRect.left = SizeUtils.dp2px(10);
                    outRect.right = SizeUtils.dp2px(10);
                }
            }
        });

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