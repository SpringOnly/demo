package com.example.mvp.widget;

import android.content.Context;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.ToastUtils;
import com.example.commonlibrary.base.arouter.ARouterConstant;
import com.example.commonlibrary.router_provider.HelloProvider;
import com.example.commonlibrary.util.AppContextUtil;

/**
 * ARouter跨模块调用
 */
@Route(path = ARouterConstant.HelloService)
public class HelloProviderImpl implements HelloProvider {
    @Override
    public void sayHello(String name) {
        ToastUtils.showShort("跨模块调用  hello   " + name);
    }

    @Override
    public void init(Context context) {

    }
}
