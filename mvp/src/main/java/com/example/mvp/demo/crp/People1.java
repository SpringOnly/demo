package com.example.mvp.demo.crp;


import com.blankj.utilcode.util.ToastUtils;
import com.example.commonlibrary.util.LogUtil;

public class People1 implements LeaveDay {

    @Override
    public boolean disposeLeave(int day) {
        if (day < 3) {
            LogUtil.e("People1:处理请假天数"+day);
            return true;
        }
        return false;
    }
}
