package amd.example.mvp.widget;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.ToastUtils;
import amd.example.commonlibrary.base.arouter.RouterPath;
import amd.example.commonlibrary.router_provider.HelloProvider;

/**
 * ARouter跨模块调用
 */
@Route(path = RouterPath.HelloService)
public class HelloProviderImpl implements HelloProvider {
    @Override
    public void sayHello(String name) {
        ToastUtils.showShort("跨模块调用  hello   " + name);
    }

    @Override
    public void init(Context context) {

    }
}
