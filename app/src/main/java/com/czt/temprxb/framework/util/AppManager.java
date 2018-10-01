package com.czt.temprxb.framework.util;

//import android.app.ActivityManager;

import android.app.ActivityManager;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by {冯中萌} on 2017/4/19.
 */

public class AppManager {
    private static AppManager ourInstance;//管理类 对象
    private Context mContext;




    public static AppManager getInstance() {
        return ourInstance;
    }

   /* *
     * 初始化 app 管理类
    */
    public static void initApp(Context context) {
        if (ourInstance == null) {
            ourInstance = new AppManager(context);
        }
    }

   /* *
     * 私有构造器
     */
    private AppManager(android.content.Context context) {
        this.mContext = context;
        //Fresco加载图片框架

        Fresco.initialize(mContext);
    }


   /* *
     * 退出程序
     */
    public void logOutApp() {
        ourInstance = null;
    }




    /**
     * 获得当前进程的名字
     *
     * @param context
     * @return*/

    public String getCurProcessName(Context context) {

        int pid = android.os.Process.myPid();

        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);

        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager
                .getRunningAppProcesses()) {

            if (appProcess.pid == pid) {

                return appProcess.processName;
            }
        }
        return null;
    }

    public Context getContext() {
        return mContext;
    }

    public void setContext(Context context) {
        mContext = context;
    }


}
