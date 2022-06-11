package amd.example.java.view.adapter;

import android.util.ArrayMap;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import amd.example.demo.R;
import amd.example.java.bean.GridlayoutBean;

/**
 * @author Created by on LvJP 2022/6/11
 */
public class GiftRoseAdapter extends BaseQuickAdapter<GridlayoutBean, BaseViewHolder> {

    public GiftRoseAdapter() {
        super(R.layout.item_grid_view);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, GridlayoutBean item) {
        helper.setImageResource(R.id.image, item.getIcon());
    }
}