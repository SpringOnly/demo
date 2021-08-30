package com.example.mvp.widget;

import android.content.Context;
import android.graphics.PointF;
import android.util.DisplayMetrics;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;


public class GallerySnapHelper extends SnapHelper {

    private OrientationHelper mHorizontalHelper;

    private static final float INVALID_DISTANCE = 1f;

    @Nullable
    @Override
    public int[] calculateDistanceToFinalSnap(@NonNull RecyclerView.LayoutManager layoutManager, @NonNull View targetView) {
        return new int[0];
    }


    @Nullable
    @Override
    public View findSnapView(RecyclerView.LayoutManager layoutManager) {
        //找到需要对齐的View
        return null;
    }

    @Override
    public int findTargetSnapPosition(RecyclerView.LayoutManager layoutManager, int velocityX, int velocityY) {
        //Fling操作的速率   需要滚动到那个位置
        return 0;
    }
}
