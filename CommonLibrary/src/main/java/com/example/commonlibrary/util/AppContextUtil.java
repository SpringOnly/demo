package com.example.commonlibrary.util;

import com.example.commonlibrary.base.BaseApplication;

public class AppContextUtil {
    public static BaseApplication getAppContext(){
        return BaseApplication.getInstance();
    }
}
