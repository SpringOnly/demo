package amd.example.java.view.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import amd.example.demo.R;
import amd.example.java.bean.SnapHelperBean;

public class SnapHelperAdapter extends BaseQuickAdapter<SnapHelperBean, BaseViewHolder> {

    public SnapHelperAdapter() {
        super(R.layout.item_text);
    }

    @Override
    protected void convert(BaseViewHolder helper, SnapHelperBean item) {
        helper.setImageResource(R.id.item_view, item.getBackground());
    }
}