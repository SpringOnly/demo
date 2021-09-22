package amd.example.commonlibrary.base.arouter;

import android.content.Context;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;
import com.blankj.utilcode.util.ToastUtils;
import amd.example.commonlibrary.util.CommonLog;

//@Interceptor(priority = 1, name = "测试拦截器")
//public class TestInterceptor implements IInterceptor {
//    int a = 10;
//
//    @Override
//    public void process(Postcard postcard, InterceptorCallback callback) {
//        int i = a - 5;
//        if (i == 5) {
//            callback.onContinue(postcard);
//        } else {
//            ToastUtils.showShort("假如我是没登录");
//        }
//    }
//
//    @Override
//    public void init(Context context) {
//        CommonLog.e("priority = 1 ARouter 初始化");
//    }
//}
