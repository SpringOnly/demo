package amd.example.commonlibrary.base.arouter;

import android.content.Context;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;
import amd.example.commonlibrary.util.CommonLog;

//@Interceptor(priority = 2, name = "拦截器")
//public class TestInterceptor2 implements IInterceptor {
//    int a = 10;
//
//    @Override
//    public void process(Postcard postcard, InterceptorCallback callback) {
//        int i = a - 5;
//        if (i == 5) {
//            callback.onContinue(postcard);
//        } else {
//            callback.onInterrupt(null);
//        }
//    }
//
//    @Override
//    public void init(Context context) {
//        CommonLog.e("priority = 2 ARouter 初始化");
//    }
//}
