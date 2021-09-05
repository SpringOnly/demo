package com.example.main.view.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.main.view.ViewPagerFragment;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentStateAdapter {
    List<ViewPagerFragment> fragmentList = new ArrayList<>();
    List<String> dataList = new ArrayList<>();

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);

        init();
    }

    private void init() {
        for (int i = 0; i < 3; i++) {
            dataList.add("init data " + i);
        }
        for (int i = 0; i < dataList.size(); i++) {
            String data = dataList.get(i);
            ViewPagerFragment fragment = new ViewPagerFragment(data);
            fragmentList.add(fragment);
        }
    }

    public List<ViewPagerFragment> getFragmentList() {
        return fragmentList;
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getItemCount() {
        return fragmentList.size();
    }
}
