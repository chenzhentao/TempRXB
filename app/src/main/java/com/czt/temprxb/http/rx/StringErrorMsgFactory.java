package com.czt.temprxb.http.rx;

import android.text.TextUtils;

import com.czt.temprxb.R;
import com.czt.temprxb.framework.util.AppManager;
import com.czt.temprxb.http.exception.RxBaseException;


/**
 * Created by {冯中萌} on 2017/6/6.
 */

public class StringErrorMsgFactory {
    /**
     * 通过状态吗 获取异常字符串
     *
     * @return
     */
    public static String getRxErrorMsg(int errorCode, int code, String msg) {
        String string = TextUtils.isEmpty(msg) ? AppManager.getInstance().getContext().getString(R.string.weizhicuowu) : msg;
        switch (errorCode) {
            case RxBaseException.API_ERROR://api错误
                break;
            case RxBaseException.HTTP_ERROR://http错误
                break;
            case RxBaseException.JSON_ERROR://gson错误
                string = AppManager.getInstance().getContext().getString(R.string.jiexichucuo);
                break;
            case RxBaseException.UNKNOPWN_ERROR://未知错误
                string = AppManager.getInstance().getContext().getString(R.string.weizhicuowu);
                break;
            case RxBaseException.SOCKET_TIMEOUT_ERROR://连接超时
                string = AppManager.getInstance().getContext().getString(R.string.lianjiechaoshi);
                break;
            case RxBaseException.SOCKET_NO_ERROR://无网络连接
                string = AppManager.getInstance().getContext().getString(R.string.wuwangluolianjie);
                break;
            default:break;
        }

        return string;
    }
}
