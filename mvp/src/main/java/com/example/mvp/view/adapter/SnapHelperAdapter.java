package com.example.mvp.view.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.mvc.R;
import com.example.mvp.bean.SnapHelperBean;


public class SnapHelperAdapter extends BaseQuickAdapter<SnapHelperBean, BaseViewHolder> {

    public SnapHelperAdapter() {
        super(R.layout.item_text);
    }

    @Override
    protected void convert(BaseViewHolder helper, SnapHelperBean item) {
        helper.getAdapterPosition();
        helper.setImageResource(R.id.item_view, item.getImage());
    }
}
