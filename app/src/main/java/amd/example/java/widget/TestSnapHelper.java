package amd.example.java.widget;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import amd.example.commonlibrary.util.CommonLog;

public class TestSnapHelper extends SnapHelper {

    private OrientationHelper mHorizontalHelper;
    final float INVALID_DISTANCE = 1f;


    //最后就是计算找到的view的坐标和需要对齐坐标之间的距离
    @Nullable
    @Override
    public int[] calculateDistanceToFinalSnap(@NonNull RecyclerView.LayoutManager layoutManager,
                                              @NonNull View targetView) {

        //out[0] 是水平轴上的距离，out[1] 是垂直轴上的距离
        //这里只关注水平方向
        int[] out = new int[2];
        if (layoutManager.canScrollHorizontally()) {
            out[0] = distanceToCenter(layoutManager, targetView,
                    getHorizontalHelper(layoutManager));
        }

        return out;
    }

    //找到中心位置 计算需要滑动的距离
    private int distanceToCenter(RecyclerView.LayoutManager layoutManager,
                                 View targetView, OrientationHelper helper) {
        int decoratedStart = helper.getDecoratedStart(targetView);
        int decoratedMeasurement = helper.getDecoratedMeasurement(targetView);
        //targetView开始位置+targetView中间位置 算出来这个targetView中心点
        int childCenter = decoratedStart + decoratedMeasurement / 2;
        //recyclerview屏幕开始位置
        int startAfterPadding = helper.getStartAfterPadding();
        //recyclerView总长度
        int totalSpace = helper.getTotalSpace();
        //屏幕开始位置+总长度/2 就是recyclerView中心的位置
        int recyclerCenter = startAfterPadding + totalSpace / 2;
        //这两个数之间的插值就是需要偏移的距离
        return childCenter - recyclerCenter;
    }

    //找到最接近对齐位置的view
    @Nullable
    @Override
    public View findSnapView(RecyclerView.LayoutManager layoutManager) {
        if (layoutManager.canScrollHorizontally()) {
            OrientationHelper helper = getHorizontalHelper(layoutManager);
            //recyclerView中心的位置
            int recyclerCenter = helper.getTotalSpace() + helper.getTotalSpace() / 2;

            int absClosest = Integer.MAX_VALUE;
            View absView = null;
            int childCount = layoutManager.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childView = layoutManager.getChildAt(i);
                //itemView的中心位置
                int childCenter = helper.getDecoratedStart(childView) +
                        helper.getDecoratedMeasurement(childView) / 2;
                //计算rv和itemView之前的偏移量
                int absDistance = Math.abs(childCenter - recyclerCenter);
                //遍历完以后   偏移量最小的就是需要对齐的view
                if (absClosest > absDistance) {
                    absClosest = absDistance;
                    absView = childView;
                }
            }
            return absView;
        }
        return null;
    }

    //松手之后 找到recyclerview需要滚动的位置 position
    //但是此时还不知道该位置的view是哪一个
    //根据findSnapView来查找
    @Override
    public int findTargetSnapPosition(RecyclerView.LayoutManager layoutManager,
                                      int velocityX, int velocityY) {

        View snapView = findSnapView(layoutManager);
        if (snapView == null) {
            return RecyclerView.NO_POSITION;
        }
        int currPosition = layoutManager.getPosition(snapView);
        if (currPosition == RecyclerView.NO_POSITION) {
            return RecyclerView.NO_POSITION;
        }

        int hDeltaJump = 0;
        hDeltaJump = estimateNextPositionDiffForFling(layoutManager,
                getHorizontalHelper(layoutManager), velocityX, 0);

        if (hDeltaJump == 0) {
            return RecyclerView.NO_POSITION;
        }
        //当前位置+偏移位置
        int targetPos = currPosition + hDeltaJump;
        if (targetPos < 0) {
            targetPos = 0;
        }

        if (targetPos >= layoutManager.getItemCount()) {
            targetPos = layoutManager.getChildCount() - 1;
        }

        return targetPos;
    }

    private int estimateNextPositionDiffForFling(RecyclerView.LayoutManager layoutManager,
                                                 OrientationHelper helper, int velocityX, int velocityY) {
        int[] distances = calculateScrollDistance(velocityX, velocityY);
        float distancePerChild = computeDistancePerChild(layoutManager, helper);
        if (distancePerChild <= 0) {
            return 0;
        }
        int distance =
                Math.abs(distances[0]) > Math.abs(distances[1]) ? distances[0] : distances[1];
        return (int) Math.round(distance / distancePerChild);
    }

    private float computeDistancePerChild(RecyclerView.LayoutManager layoutManager,
                                          OrientationHelper helper) {
        View minPosView = null;
        View maxPosView = null;
        int minPos = Integer.MAX_VALUE;
        int maxPos = Integer.MIN_VALUE;
        int childCount = layoutManager.getChildCount();
        if (childCount == 0) {
            return INVALID_DISTANCE;
        }

        for (int i = 0; i < childCount; i++) {
            View child = layoutManager.getChildAt(i);
            final int pos = layoutManager.getPosition(child);
            if (pos == RecyclerView.NO_POSITION) {
                continue;
            }
            if (pos < minPos) {
                minPos = pos;
                minPosView = child;
            }
            if (pos > maxPos) {
                maxPos = pos;
                maxPosView = child;
            }
        }
        if (minPosView == null || maxPosView == null) {
            return INVALID_DISTANCE;
        }
        int start = Math.min(helper.getDecoratedStart(minPosView),
                helper.getDecoratedStart(maxPosView));
        int end = Math.max(helper.getDecoratedEnd(minPosView),
                helper.getDecoratedEnd(maxPosView));
        int distance = end - start;
        if (distance == 0) {
            return INVALID_DISTANCE;
        }
        return 1f * distance / ((maxPos - minPos) + 1);
    }

    //横向滑动的帮助类
    private OrientationHelper getHorizontalHelper(RecyclerView.LayoutManager layoutManager) {
        if (mHorizontalHelper == null || mHorizontalHelper.getLayoutManager() != layoutManager) {
            mHorizontalHelper = OrientationHelper.createHorizontalHelper(layoutManager);
        }
        return mHorizontalHelper;
    }
}
