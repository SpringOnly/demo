package amd.example.java.widget.component;

import androidx.recyclerview.widget.RecyclerView;

/**
 * @author Created by on LvJP 2022-01-06
 */
public class TestLayoutManager extends RecyclerView.LayoutManager {
    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return null;
    }


    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        super.onLayoutChildren(recycler, state);

    }
}
