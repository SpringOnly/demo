package com.example.mvp.widget;

import android.graphics.PointF;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

/**
 * itemView左对齐
 */
public class GallerySnapHelper extends SnapHelper {

    private OrientationHelper mHorizontalHelper;

    private static final float INVALID_DISTANCE = 1f;

    @Nullable
    @Override
    public int[] calculateDistanceToFinalSnap(@NonNull RecyclerView.LayoutManager layoutManager, @NonNull View targetView) {
        int[] out = new int[2];
        if (layoutManager.canScrollHorizontally()) {
            out[0] = distanceToStart(targetView, getHorizontalHelper(layoutManager));
        }
        return out;
    }

    //targetView和recyclerview padding start的间距
    private int distanceToStart(View targetView, OrientationHelper helper) {
        return helper.getDecoratedStart(targetView) - helper.getStartAfterPadding();
    }

    @Nullable
    @Override
    public View findSnapView(RecyclerView.LayoutManager layoutManager) {
        return findStartView(layoutManager, getHorizontalHelper(layoutManager));
    }

    //找到需要对齐的View
    private View findStartView(RecyclerView.LayoutManager layoutManager, OrientationHelper helper) {
        View childView;

        //判断是否是LinearLayoutManager
        if (!(layoutManager instanceof LinearLayoutManager)) {
            return null;
        }
        //如果是最后一个itemView 并且已经显示 那么不去做对齐操作
        int position = ((LinearLayoutManager) layoutManager).findLastCompletelyVisibleItemPosition();
        if (position == layoutManager.getItemCount() - 1) {
            return null;
        }
        //第一个可见itemView的位置   根据位置找到该View
        int firstVisibleItemPosition = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
        childView = layoutManager.findViewByPosition(firstVisibleItemPosition);
        if (childView == null) {
            return null;
        }
        //如果itemView没有被遮住一半 那就是返回当前view
        //如果到了一半 取下一个itemView

        //getDecoratedEnd = itemView总长度减去已经不可见的区域
        //如10-3=7    10/2=5 可见区域大于一半
        //10-8=2      10/2=5 可见区域小于一半 取下一个

        if (helper.getDecoratedEnd(childView) > helper.getDecoratedMeasurement(childView) / 2) {
            return childView;
        } else {
            return layoutManager.findViewByPosition(firstVisibleItemPosition + 1);
        }
    }

    //Fling操作的速率   需要滚动到那个位置
    @Override
    public int findTargetSnapPosition(RecyclerView.LayoutManager layoutManager, int velocityX, int velocityY) {
        //判断layoutManager是否实现了RecyclerView.SmoothScroller.ScrollVectorProvider接口
        if (!(layoutManager instanceof RecyclerView.SmoothScroller.ScrollVectorProvider)) {
            return RecyclerView.NO_POSITION;
        }
        final int itemCount = layoutManager.getItemCount();
        if (itemCount == 0) {
            return RecyclerView.NO_POSITION;
        }
        //snapView 需要对齐的View
        View snapView = findSnapView(layoutManager);
        if (snapView == null) {
            return RecyclerView.NO_POSITION;
        }
        //是否能找到该View的下标
        int currentPosition = layoutManager.getPosition(snapView);
        if (currentPosition == RecyclerView.NO_POSITION) {
            return RecyclerView.NO_POSITION;
        }
        RecyclerView.SmoothScroller.ScrollVectorProvider vectorProvider =
                (RecyclerView.SmoothScroller.ScrollVectorProvider) layoutManager;

        int xDeltaJump, yDeltaJump;
        //该方法最终确认layoutManage的布局方向
        PointF pointF = vectorProvider.computeScrollVectorForPosition(currentPosition);
        if (pointF == null) {
            return RecyclerView.NO_POSITION;
        }
        //layoutManager是横向布局 并且满一屏
        if (layoutManager.canScrollHorizontally()) {
            xDeltaJump = estimateNextPositionDiffForFling(layoutManager, getHorizontalHelper(layoutManager),
                    velocityX, velocityY);
        } else {
            xDeltaJump = 0;
        }
        if (xDeltaJump == 0) {
            return RecyclerView.NO_POSITION;
        }
        int targetPos = currentPosition + xDeltaJump;
        if (targetPos < 0) {
            targetPos = 0;
        }
        if (targetPos >= itemCount) {
            targetPos = itemCount - 1;
        }
        return targetPos;
    }

    //估算偏移量
    private int estimateNextPositionDiffForFling(RecyclerView.LayoutManager layoutManager,
                                                 OrientationHelper helper, int velocityX, int velocityY) {
        //计算滚动的总长度
        int[] distances = calculateScrollDistance(velocityX, velocityY);
        //计算每个item的长度
        float childLength = computeDistancePerChild(layoutManager, helper);
        if (childLength <= 0) {
            return 0;
        }
        //根据布局方向确定滑动的方向距离
        int distance;
        if (Math.abs(distances[0]) > Math.abs(distances[1])) {
            distance = distances[0];
        } else {
            distance = distances[1];
        }
        //如果滑动距离大于0 向上取整
        if (distance > 0) {
            return (int) Math.floor(distance / childLength);
        } else {
            return (int) Math.ceil(distance / childLength);
        }
    }

    //估算单个itemView的长度
    private float computeDistancePerChild(RecyclerView.LayoutManager layoutManager, OrientationHelper helper) {
        int maxPos = Integer.MIN_VALUE;
        int minPos = Integer.MAX_VALUE;
        int childCount = layoutManager.getChildCount();
        View maxChild = null;
        View minChild = null;
        for (int i = 0; i < childCount; i++) {
            View childAtView = layoutManager.getChildAt(i);
            if (childAtView == null) {
                return RecyclerView.NO_POSITION;
            }
            int position = layoutManager.getPosition(childAtView);
            if (position < minPos) {
                minPos = position;
                minChild = childAtView;
            }
            if (position > maxPos) {
                maxPos = position;
                maxChild = childAtView;
            }
        }
        if (minChild == null || maxChild == null) {
            return INVALID_DISTANCE;
        }
        //最小位置和最大位置肯定就是分布在layoutManager的两端，但是无法直接确定哪个在起点哪个在终点（因为有正反向布局）
        //所以取两者中起点坐标小的那个作为起点坐标
        //终点坐标的取值一样的道理
        int start = Math.min(helper.getDecoratedStart(minChild), helper.getDecoratedStart(maxChild));
        int end = Math.min(helper.getDecoratedEnd(minChild), helper.getDecoratedEnd(maxChild));
        //开始位置减去结束位置等于itemView的总宽度
        int distance = end - start;
        if (distance == 0) {
            return INVALID_DISTANCE;
        }
        //总长度/itemView的个数 = 单个itemView的宽度
        return 1f * distance / ((maxPos - minPos) + 1);
    }

    private OrientationHelper getHorizontalHelper(@NonNull RecyclerView.LayoutManager layoutManager) {
        if (mHorizontalHelper == null || mHorizontalHelper.getLayoutManager() != layoutManager) {
            mHorizontalHelper = OrientationHelper.createHorizontalHelper(layoutManager);
        }
        return mHorizontalHelper;
    }
}
