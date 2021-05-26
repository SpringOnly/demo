package com.example.mvp.test;

import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.mvc.R;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TestAdapter extends BaseQuickAdapter<TestBean, BaseViewHolder> {
    public TestAdapter(int layoutResId, @Nullable List<TestBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, TestBean testBean) {
        TextView tv = baseViewHolder.getView(R.id.itemTvSelect);
        tv.setText(testBean.getNameType());
        if (testBean.isCheck()) {
            tv.setBackgroundResource(R.drawable.test_select_bg);
        } else {
            tv.setBackgroundResource(R.drawable.test_no_select_bg);
        }
    }
}
