package debug;


import com.example.commonlibrary.base.BaseApplication;
import com.kk.taurus.playerbase.config.PlayerLibrary;

public class MyApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        PlayerLibrary.init(this);
    }
}
