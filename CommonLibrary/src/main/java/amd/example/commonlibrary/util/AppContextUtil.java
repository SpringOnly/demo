package amd.example.commonlibrary.util;

import amd.example.commonlibrary.base.BaseApplication;

public class AppContextUtil {
    public static BaseApplication getAppContext(){
        return BaseApplication.getInstance();
    }
}
