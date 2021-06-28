package com.example.mvp.view.adapter;

import android.content.Context;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseViewHolder;
import com.example.mvc.R;
import com.example.mvp.bean.GridlayoutBean;
import com.example.mvp.base.BaseAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.List;


public class GridLayoutAdapter extends BaseAdapter<GridlayoutBean, BaseViewHolder> {

    private List<GridlayoutBean> mGridlayoutBeans;

    public GridLayoutAdapter(@NonNull @NotNull Context context) {
        super(context, R.layout.mmd_item_grid_image);
    }

    public void setData(List<GridlayoutBean> gridlayoutBeans) {
        this.mGridlayoutBeans = gridlayoutBeans;
        setNewData(mGridlayoutBeans);
    }

    @Override
    protected void bindView(BaseViewHolder helper, GridlayoutBean item) {

        if (mGridlayoutBeans.size() < 5) {
            if (helper.getAdapterPosition() == 0) {
                helper.setGone(R.id.banner, true);
                helper.setImageResource(R.id.banner, R.mipmap.ic_launcher);
            } else {
                helper.setGone(R.id.banner, false);
            }
        } else {
            if (helper.getAdapterPosition() == 4) {
                helper.setGone(R.id.banner, true);
                helper.setImageResource(R.id.banner, R.mipmap.ic_launcher);
            } else {
                helper.setGone(R.id.banner, false);
            }
        }
        helper.setText(R.id.item_text, item.getMessage());
    }
}
