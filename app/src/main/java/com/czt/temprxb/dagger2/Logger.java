package com.czt.temprxb.dagger2;


import cn.droidlover.xdroidmvp.log.XLog;


/**
 * @author yuyh.
 * @date 2016/12/13.
 */
public class Logger implements LoggingInterceptor.Logger {

    @Override
    public void log(String message) {
        XLog.w( message);
    }
}
