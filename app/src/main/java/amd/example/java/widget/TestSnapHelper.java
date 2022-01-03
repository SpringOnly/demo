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
                                                 OrientationHelper helper,
                                                 int velocityX, int velocityY) {
        //计算滚动的总距离
        int[] distances = calculateScrollDistance(velocityX, velocityY);
        //计算itemView的平均长度
        float distancePerChild = computeDistancePerChild(layoutManager, helper);
        if (distancePerChild <= 0) {
            return 0;
        }
        int distance =
                Math.abs(distances[0]) > Math.abs(distances[1]) ? distances[0] : distances[1];
        //distance的正负值符号表示滚动方向，数值表示滚动距离。横向布局方式，内容从右往左滚动为正；竖向布局方式，内容从下往上滚动为正
        // 滚动距离/item的长度=滚动item的个数，这里取计算结果的整数部分
        if (distance > 0) {
            return (int) Math.floor(distance / distancePerChild);
        } else {
            return (int) Math.ceil(distance / distancePerChild);
        }
    }

    private float computeDistancePerChild(RecyclerView.LayoutManager layoutManager,
                                          OrientationHelper helper) {

        int itemCount = layoutManager.getItemCount();
        if (itemCount == 0) {
            return 0;
        }
        View startView;
        View endView;
        for (int i = 0; i < itemCount; i++) {
            View childAt = layoutManager.findViewByPosition(i);
            CommonLog.e("" + i + " " + childAt);
        }
        startView = layoutManager.getChildAt(0);
        endView = layoutManager.getChildAt(itemCount - 1);
//        int decoratedStart = helper.getDecoratedStart(startView);
//        int decoratedEnd = helper.getDecoratedEnd(endView);
        //最后一个item的结束位置-第一个item的开始位置
        //等于itemView的总长度
//        int recyclerLength = decoratedEnd - decoratedStart;
        //recyclerView的长度/item的总数=item的平均宽度
        return 0;
    }


    //横向滑动的帮助类
    private OrientationHelper getHorizontalHelper(RecyclerView.LayoutManager layoutManager) {
        if (mHorizontalHelper == null || mHorizontalHelper.getLayoutManager() != layoutManager) {
            mHorizontalHelper = OrientationHelper.createHorizontalHelper(layoutManager);
        }
        return mHorizontalHelper;
    }
}
