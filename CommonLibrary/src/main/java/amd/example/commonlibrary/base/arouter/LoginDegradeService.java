package amd.example.commonlibrary.base.arouter;

import android.content.Context;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.service.DegradeService;
import com.alibaba.android.arouter.launcher.ARouter;

///**
// * 找不到路由地址的降级策略
// */
//@Route(path = "/aaa/bbb")
//public class LoginDegradeService implements DegradeService {
//    @Override
//    public void onLost(Context context, Postcard postcard) {
//        ARouter.getInstance().build(RouterPath.MVC).navigation();
//    }
//
//    @Override
//    public void init(Context context) {
//
//    }
//}
