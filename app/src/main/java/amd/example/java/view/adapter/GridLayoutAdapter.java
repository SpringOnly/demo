package amd.example.java.view.adapter;

import android.content.Context;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseViewHolder;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import amd.example.demo.R;
import amd.example.java.base.BaseAdapter;
import amd.example.java.bean.GridlayoutBean;

public class GridLayoutAdapter extends BaseAdapter<GridlayoutBean, BaseViewHolder> {

    private List<GridlayoutBean> mGridlayoutBeans;

    public GridLayoutAdapter(@NonNull @NotNull Context context) {
        super(context, R.layout.item_text);
    }

    public void setData(List<GridlayoutBean> gridlayoutBeans) {
        this.mGridlayoutBeans = gridlayoutBeans;
        setNewData(mGridlayoutBeans);
    }

    @Override
    protected void bindView(BaseViewHolder helper, GridlayoutBean item) {

//        if (mGridlayoutBeans.size() < 5) {
//            if (helper.getAdapterPosition() == 0) {
//                helper.setGone(R.id.banner, true);
//                helper.setImageResource(R.id.banner, R.mipmap.ic_launcher);
//            } else {
//                helper.setGone(R.id.banner, false);
//            }
//        } else {
//            if (helper.getAdapterPosition() == 4) {
//                helper.setGone(R.id.banner, true);
//                helper.setImageResource(R.id.banner, R.mipmap.ic_launcher);
//            } else {
//                helper.setGone(R.id.banner, false);
//            }
//        }
//        helper.setText(R.id.item_text, item.getMessage());
    }
}
