package com.czt.temprxb;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.czt.temprxb.dagger2.AppCompcoent;
import com.czt.temprxb.dagger2.DaggerAppCompcoent;
import com.czt.temprxb.framework.util.AppManager;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import cn.droidlover.xdroidmvp.log.XLog;


/**
 * Created by GA on 2018/4/23.
 */

public class MyApplication extends MultiDexApplication {
    private static Context context;
    private AppCompcoent mAppCompcoent;//全局app 依赖注入
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
    private RefWatcher setupLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return RefWatcher.DISABLED;
        }
        return LeakCanary.install(this);
    }

    public static RefWatcher getRefWatcher(Context context) {
        MyApplication application = (MyApplication) context.getApplicationContext();
        return application.refWatcher;
    }

    private RefWatcher refWatcher;

    private static Intent blueService;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        mAppCompcoent = DaggerAppCompcoent.create();
        AppManager.initApp(this);
        //初始化异常管理工具
//        CrashHandler.getInstance().init(context);
        refWatcher= setupLeakCanary();

    }
    
    public static Context getContext() {
        return context;
    }

    public AppCompcoent getAppCompcoent() {
        return mAppCompcoent;
    }

    @Override
    public void onTerminate() {
        XLog.d("程序终止的时候执行");
        appOut();
        super.onTerminate();
    }

    /**
     * 退出程序时调用
     */
    public static void appOut() {
        if (AppManager.getInstance() != null) {
            AppManager.getInstance().logOutApp();
        }
    }

    @Override
    public void onLowMemory() {
        XLog.d("低内存的时候执行");
        super.onLowMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        XLog.d("程序在内存清理的时候执行");
        super.onTrimMemory(level);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        XLog.d("onConfigurationChanged");
        super.onConfigurationChanged(newConfig);
    }


}
