package amd.example.java;

import android.app.Application;

import com.alibaba.android.arouter.BuildConfig;
import com.alibaba.android.arouter.launcher.ARouter;
import com.facebook.drawee.backends.pipeline.Fresco;

import amd.example.commonlibrary.base.BaseApplication;

public class AppApplication extends Application {

    private static Application instance;


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initRouter();
    }

    public static Application getInstance() {
        return instance;
    }

    private void initRouter() {
        if (BuildConfig.DEBUG) {
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(this);
        Fresco.initialize(this);
    }
}
