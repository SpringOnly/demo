package amd.example.java;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.ArrayMap;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.ScreenUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

import amd.example.commonlibrary.base.BaseActivity;
import amd.example.commonlibrary.base.arouter.RouterPath;
import amd.example.commonlibrary.router_provider.HelloProvider;
import amd.example.commonlibrary.util.CommonLog;
import amd.example.demo.R;
import amd.example.demo.databinding.ActivityMainBinding;
import amd.example.java.bean.GridlayoutBean;
import amd.example.java.view.adapter.GiftRoseAdapter;

public class MainActivity extends BaseActivity<ActivityMainBinding> {

    @Autowired
    HelloProvider mProvider;

    @Override
    protected ActivityMainBinding getViewBinding() {
        return ActivityMainBinding.inflate(getLayoutInflater());
    }

    GridLayoutManager layoutManager;

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void initView() {
        layoutManager = new GridLayoutManager(this, 3);
        mBinding.testRecycler.setLayoutManager(layoutManager);
        GiftRoseAdapter adapter = new GiftRoseAdapter();
        mBinding.testRecycler.setAdapter(adapter);
        for (int i = 0; i < 6; i++) {
            GridlayoutBean gridlayoutBean = new GridlayoutBean();
            gridlayoutBean.setIcon(R.mipmap.image);
            adapter.addData(gridlayoutBean);
        }
        adapter.notifyDataSetChanged();
    }


    @Override
    protected void initListener() {

        mBinding.mvp.setOnClickListener(v ->
                ARouter.getInstance().build(RouterPath.DEMO).navigation()
        );

        mBinding.jumpMoudel.setOnClickListener(v ->
                mProvider.sayHello("jack"));

        //        ((HelloService)ARouter
//                .getInstance()
//                .build(ARouterConstant.HelloService)
//                .navigation())
//                .sayHello("jack name");

        mBinding.agora.setOnClickListener(v ->
                ARouter.getInstance().build(RouterPath.AGORA)
                        .navigation());

        mBinding.faceUnity.setOnClickListener(v ->
                ARouter.getInstance().build(RouterPath.FACE_UNITY)
                        .navigation());

        mBinding.recyclerView.setOnClickListener(v ->
                ARouter.getInstance().build(RouterPath.RECYCLER)
                        .navigation());
        mBinding.button.setOnClickListener(v -> {
            //飘心动画
//            mBinding.labeled.addLoveView();

            RecyclerView testRecycler = mBinding.testRecycler;
            //到顶部的距离 + recycler的一半
            int viewCenterY = testRecycler.getTop() + (testRecycler.getHeight()) / 2;
            //设置顶部停止的位置
            mBinding.gift.setYStopAnim(viewCenterY);
            //宽度占满全屏
            int width = testRecycler.getWidth();
            //recyclerview的高度
            int height = testRecycler.getBottom() - testRecycler.getTop();
            int oneWidth = width / 3;
            int oneHeight = height / 2;

            List<int[]> list = new ArrayList<>();

            int v10 = oneWidth / 2;
            int v20 = oneHeight / 2 + testRecycler.getTop();
            setPosition(list, v10, v20);


            int v11 = oneWidth * 2 - oneWidth / 2;
            int v21 = oneHeight / 2 + testRecycler.getTop();
            setPosition(list, v11, v21);

            int v12 = oneWidth * 3 - oneWidth / 2;
            int v22 = oneHeight / 2 + testRecycler.getTop();
            setPosition(list, v12, v22);

            int v13 = oneWidth / 2;
            int v23 = oneHeight * 2 - oneHeight / 2 + testRecycler.getTop();
            setPosition(list, v13, v23);

            int v14 = oneWidth * 2 - oneWidth / 2;
            int v24 = oneHeight * 2 - oneHeight / 2 + testRecycler.getTop();
            setPosition(list, v14, v24);

            int v15 = oneWidth * 3 - oneWidth / 2;
            int v25 = oneHeight * 2 - oneHeight / 2 + testRecycler.getTop();
            setPosition(list,v15,v25);
            for (int i = 0; i < list.size(); i++) {
                mBinding.gift.addGiftView(list.get(i));
            }
        });
    }

    private void setPosition(List<int[]> list, int x, int y) {
        int[] ints = new int[2];
        ints[0] = x;
        ints[1] = y;
        list.add(ints);
    }


    @Override
    protected void initData() {
//        int size = getData().size();
//        for (int i = 0; i < size; i++) {
//            ImageView imageView = (ImageView) getViewByPosition(i, R.id.image);
//            if (imageView == null) {
//                return null;
//            }
//            int[] pointList = new int[2];
//            imageView.getLocationOnScreen(pointList);
//            map.put(i, pointList);
//        }
    }


}