package com.example.commonlibrary.router_provider;

import com.alibaba.android.arouter.facade.template.IProvider;

public interface HelloProvider extends IProvider {
    void sayHello(String name);
}
