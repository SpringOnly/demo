package com.example.mvp.demo.crp;


public class ApplyTestDemo {
    /**
     * 责任链模式
     * @param day  请假的天数
     */
    public static void init(int day) {
        ApplyDay applyDay = new ApplyDay();
        applyDay.add(new People1())
                .add(new People2())
                .add(new People3());
        applyDay.disposeLeave(day);
    }
}
