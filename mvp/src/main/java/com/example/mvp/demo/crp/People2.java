package com.example.mvp.demo.crp;


import com.blankj.utilcode.util.ToastUtils;
import com.example.commonlibrary.util.LogUtil;

public class People2 implements LeaveDay {

    @Override
    public boolean disposeLeave(int day) {
        if (day < 7) {
            LogUtil.e("People2:处理请假天数"+day);
            return true;
        }
        return false;
    }
}
