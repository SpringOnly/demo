package com.example.mvp.base;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public abstract class BaseMultiAdapter<E, VH extends BaseViewHolder> extends BaseAdapter<E, VH> {

    /**
     * 构造方法
     *
     * @param context 上下文
     */
    public BaseMultiAdapter(Context context) {
        this(context, null);
    }

    /**
     * 构造方法
     *
     * @param context 上下文
     */
    public BaseMultiAdapter(Context context, @Nullable List<E> list) {
        super(context, 0, list);
    }

    @Override
    protected int getDefItemViewType(int position) {
        return getItemType(position);
    }

    @Override
    protected VH onCreateDefViewHolder(ViewGroup parent, int viewType) {
        return createHolder(parent, viewType);
    }

    protected abstract VH createHolder(ViewGroup parent, int viewType);

    protected abstract int getItemType(int position);
}