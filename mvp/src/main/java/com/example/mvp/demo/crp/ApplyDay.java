package com.example.mvp.demo.crp;

import com.example.commonlibrary.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

//处理所有人的请假权限
public class ApplyDay implements LeaveDay {

    private List<LeaveDay> mDayList = new ArrayList<>();

    public ApplyDay add(LeaveDay day) {
        mDayList.add(day);
        return this;
    }

    public ApplyDay remove(LeaveDay day) {
        mDayList.remove(day);
        return this;
    }

    @Override
    public boolean disposeLeave(int day) {
        for (LeaveDay leaveDay : mDayList) {
            if (leaveDay.disposeLeave(day)) {
                return true;
            }
        }
        LogUtil.e("请假太多了 没得玩");
        return false;
    }
}
