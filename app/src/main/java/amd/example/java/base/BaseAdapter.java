package amd.example.java.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import androidx.annotation.IntRange;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;


public abstract class BaseAdapter<E, VH extends BaseViewHolder> extends BaseQuickAdapter<E, BaseViewHolder> {

    //上下文对象
    protected final Context mContext;
    private int mMinEmptyHeight;

    /**
     * 构造方法
     *
     * @param context 上下文
     */
    public BaseAdapter(@NonNull Context context, @LayoutRes int layoutResId) {
        this(context, layoutResId, null);
        notifyDataSetChanged();
    }

    /**
     * 构造方法
     *
     * @param context 上下文
     */
    public BaseAdapter(@NonNull Context context, @LayoutRes int layoutResId, @Nullable List<E> list) {
        super(layoutResId, list);
        this.mContext = context;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected final void convert(BaseViewHolder holder, E item) {
        bindView((VH) holder, item);
    }

    @Override
    protected BaseViewHolder createBaseViewHolder(ViewGroup parent, int layoutResId) {
        return super.createBaseViewHolder(getItemView(layoutResId, parent));
    }

    @Override
    protected BaseViewHolder createBaseViewHolder(View view) {
        return new BaseViewHolder(view);
    }

    protected abstract void bindView(VH holder, E item);

    /**
     * 判断数据集是否为空
     */
    public boolean isEmpty() {
        return getData().isEmpty();
    }

    /**
     * 清空数据集
     */
    public void clear() {
        getData().clear();
    }

    /**
     * 添加数据到最后
     */
    public void add(E e) {
        getData().add(e);
    }

    /**
     * 添加数据到某个位置
     *
     * @param index 索引
     */
    public void add(E e, int index) {
        getData().add(index, e);
    }

    /**
     * 添加数组
     *
     * @param list 数组
     */
    public void addAll(List<E> list) {
        getData().addAll(list);
    }

    /**
     * 只删除数据
     *
     * @param position 序列
     */
    @Override
    public void remove(@IntRange(from = 0) int position) {
        mData.remove(position);
    }

    /**
     * 只删除数据
     *
     * @param bean 数据
     */
    public void remove(E bean) {
        mData.remove(bean);
    }

    /**
     * 删除数据同时刷新
     *
     * @param position 序列
     */
    public void removeData(@IntRange(from = 0) int position) {
        remove(position);
        int internalPosition = position + getHeaderLayoutCount();
        notifyItemRemoved(internalPosition);
        compatibilityDataSizeChanged(0);
        notifyItemRangeChanged(internalPosition, mData.size() - internalPosition);
    }

    /**
     * 替换数据
     *
     * @param bean     数据
     * @param position 索引
     */
    public void set(E bean, int position) {
        mData.set(position, bean);
    }

    /**
     * 是否存在数据
     *
     * @param bean 数据
     */
    public boolean contains(E bean) {
        return mData.contains(bean);
    }

    private void compatibilityDataSizeChanged(int size) {
        final int dataSize = mData == null ? 0 : mData.size();
        if (dataSize == size) {
            notifyDataSetChanged();
        }
    }

    public void bindToRecyclerView(RecyclerView recyclerView) {
        if (getRecyclerView() == null) {
            super.bindToRecyclerView(recyclerView);
        } else {
            getRecyclerView().setAdapter(this);
        }
    }

    @Override
    public int addHeaderView(View header, int index, int orientation) {
        int result = super.addHeaderView(header, index, orientation);
        refreshEmptyViewHeight();
        return result;
    }

    @Override
    public int setHeaderView(View header, int index, int orientation) {
        int result = super.setHeaderView(header, index, orientation);
        refreshEmptyViewHeight();
        return result;
    }

    @Override
    public void removeHeaderView(View header) {
        super.removeHeaderView(header);
        refreshEmptyViewHeight();
    }

    @Override
    public void removeAllHeaderView() {
        super.removeAllHeaderView();
        refreshEmptyViewHeight();
    }

    @Override
    public void setEmptyView(View view) {
        super.setEmptyView(view);
        refreshEmptyViewHeight();
    }

    /**
     * 清空EmptyView
     */
    public void removeAllEmptyView() {
        if (getEmptyViewCount() > 0) {
            ((ViewGroup) getEmptyView()).removeAllViews();
        }
    }

    private ViewTreeObserver.OnGlobalLayoutListener mOnGlobalLayoutListener;

    private void refreshEmptyViewHeight() {
        if (getEmptyViewCount() > 0) {
            View emptyLayout = getEmptyView();
            if (getHeaderLayoutCount() > 0) {
                if (mOnGlobalLayoutListener != null) {
                    return;
                }
                View headerLayout = getHeaderLayout();

                int height = headerLayout.getHeight();

                if (height == 0) {
                    mOnGlobalLayoutListener = () -> {
                        setEmptyViewHeight(emptyLayout, getRecyclerView().getHeight() - headerLayout.getHeight());
                        headerLayout.getViewTreeObserver().removeOnGlobalLayoutListener(mOnGlobalLayoutListener);
                        mOnGlobalLayoutListener = null;
                    };
                    headerLayout.getViewTreeObserver().addOnGlobalLayoutListener(mOnGlobalLayoutListener);
                } else {
                    setEmptyViewHeight(emptyLayout, getRecyclerView().getHeight() - height);
                }
            } else {
                setEmptyViewHeight(emptyLayout, ViewGroup.LayoutParams.MATCH_PARENT);
            }
        }
    }

    private void setEmptyViewHeight(View emptyLayout, int height) {
        if (height > 0 && height < mMinEmptyHeight) {
            height = mMinEmptyHeight;
        }
        ViewGroup.LayoutParams lp = emptyLayout.getLayoutParams();
        if (lp == null) {
            lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        }
        lp.height = height;
        emptyLayout.setLayoutParams(lp);
    }

    private boolean mLoadMoreEnd;
    private boolean mLoadMoreEndGone;

    @Override
    public void setEnableLoadMore(boolean enable) {
        super.setEnableLoadMore(enable);
        this.mLoadMoreEnd = false;
    }

    @Override
    public void loadMoreEnd(boolean gone) {
        super.loadMoreEnd(gone);
        this.mLoadMoreEndGone = gone;
        this.mLoadMoreEnd = true;
    }

    /**
     * 是否加载更多结束
     */
    public boolean isLoadMoreEnd() {
        return mLoadMoreEnd;
    }

    /**
     * 是否加载更多结束隐藏
     */
    public boolean isLoadMoreEndGone() {
        return mLoadMoreEndGone;
    }

    /**
     * 设置空状态最小高度
     *
     * @param minEmptyHeight 最小高度
     */
    public void setMinEmptyHeight(int minEmptyHeight) {
        this.mMinEmptyHeight = minEmptyHeight;
    }

}
