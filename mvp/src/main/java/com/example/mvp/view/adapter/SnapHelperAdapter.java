package com.example.mvp.view.adapter;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.mvp.R;
import com.example.mvp.bean.SnapHelperBean;

import java.util.ArrayList;
import java.util.List;


public class SnapHelperAdapter extends BaseQuickAdapter<SnapHelperBean, BaseViewHolder> {

    public SnapHelperAdapter() {
        super(R.layout.item_text);
    }

    @Override
    protected void convert(BaseViewHolder helper, SnapHelperBean item) {
        helper.setImageResource(R.id.item_view, item.getImage());
    }

//    LinearSnapHelper pagerSnapHelper = new LinearSnapHelper();
//        pagerSnapHelper.attachToRecyclerView(Binding.recycler);
//    SnapHelperAdapter snapHelperAdapter = new SnapHelperAdapter();
//        Binding.recycler.setAdapter(snapHelperAdapter);
//    LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
//        Binding.recycler.setLayoutManager(layoutManager);
//
//    List<SnapHelperBean> beanList = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//        SnapHelperBean snapHelperBean = new SnapHelperBean();
//        snapHelperBean.setImage(R.mipmap.image);
//        beanList.add(snapHelperBean);
//    }
//        snapHelperAdapter.setNewData(beanList);
}
