空壳App : 负责管理各个业务组件，和打包apk，没有具体的业务功能；
main组件 ：属于业务组件，指定APP启动页面、主界面
mvc组件 ：属于业务组件
mvp组件 ：属于业务组件
mvvm组件 ：属于业务组件
CommonLibrary(基类库) : 属于功能组件，提供业务组件需要的通用的功能，
例如：网络请求，图片加载，mvp框架，工具类，每个组件的数据实体等等。
组件化开关在config.gradle 

跨模块通信ARouter自带的IProvider可以参考CommonLibrary->router_provider
ARouter常量 ARouter拦截器 ARouter降级策略可参考CommonLibrary->ARouter
ARouter在线文档 https://github.com/alibaba/ARouter/blob/master/README_CN.md

key store password:66668888
key alias:key0
key password:66668888
