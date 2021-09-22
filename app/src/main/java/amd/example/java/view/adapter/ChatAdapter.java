package amd.example.java.view.adapter;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseViewHolder;

import amd.example.demo.R;
import amd.example.demo.databinding.TypeItemLeftBinding;
import amd.example.demo.databinding.TypeItemRightBinding;
import amd.example.java.base.BaseMultiAdapter;
import amd.example.java.bean.TypeLeftBean;
import amd.example.java.bean.TypeRightBean;

public class ChatAdapter extends BaseMultiAdapter<Object, BaseViewHolder> {

    private final int LEFT = 1;
    private final int RIGHT = 2;
    private static TypeItemLeftBinding mLeftBinding;
    private static TypeItemRightBinding mRightBinding;

    public ChatAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getItemType(int position) {
        Object o = getItem(position);
        if (o instanceof TypeLeftBean) {
            return LEFT;
        } else if (o instanceof TypeRightBean) {
            return RIGHT;
        }
        return LEFT;
    }

    @Override
    protected BaseViewHolder createHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case LEFT:
                return new LeftHolder(getItemView(R.layout.type_item_left, parent));
            case RIGHT:
                return new RightHolder(getItemView(R.layout.type_item_right, parent));
        }
        return null;
    }

    @Override
    protected void bindView(BaseViewHolder holder, Object item) {
        switch (holder.getItemViewType()) {
            case LEFT:
                TypeLeftBean typeLeftBean = (TypeLeftBean) item;
                mLeftBinding.leftTv.setText(typeLeftBean.getLeftMessage());
                break;
            case RIGHT:
                TypeRightBean typeRightBean = (TypeRightBean) item;
                mRightBinding.rightTv.setText(typeRightBean.getRightMessage());
                break;
        }
    }


    public static class LeftHolder extends BaseViewHolder {

        public LeftHolder(View view) {
            super(view);
            mLeftBinding = TypeItemLeftBinding.bind(view);
        }
    }

    public static class RightHolder extends BaseViewHolder {

        public RightHolder(View view) {
            super(view);
            mRightBinding = TypeItemRightBinding.bind(view);
        }
    }
}
