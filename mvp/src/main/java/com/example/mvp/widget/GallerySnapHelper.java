package com.example.mvp.widget;


import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import org.jetbrains.annotations.NotNull;

public class GallerySnapHelper extends SnapHelper {

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public int[] calculateDistanceToFinalSnap(@NonNull @NotNull RecyclerView.LayoutManager layoutManager, @NonNull @NotNull View targetView) {
        return new int[0];
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View findSnapView(RecyclerView.LayoutManager layoutManager) {
        return null;
    }

    @Override
    public int findTargetSnapPosition(RecyclerView.LayoutManager layoutManager, int velocityX, int velocityY) {
        return 0;
    }
}
