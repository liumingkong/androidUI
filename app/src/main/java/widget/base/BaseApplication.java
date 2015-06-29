package widget.base;

import android.app.Application;
import android.content.Context;

/**
 * Created by liumingkong on 15/6/24.
 */
public class BaseApplication extends Application {
    private static Application instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        DaggerInjector.init(new DaggerRootModules(), this); // 初始化注入
    }

    public static Context getAppContext() {
        return instance.getApplicationContext();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        instance = null;
    }
}
