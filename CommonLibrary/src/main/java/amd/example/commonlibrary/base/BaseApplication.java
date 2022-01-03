package amd.example.commonlibrary.base;

import android.app.Application;

import com.alibaba.android.arouter.BuildConfig;
import com.alibaba.android.arouter.launcher.ARouter;

public class BaseApplication extends Application {
    private static BaseApplication instance;


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initRouter();
    }

    public static BaseApplication getInstance() {
        return instance;
    }

    private void initRouter() {
        if (BuildConfig.DEBUG) {
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(this);
    }
}
