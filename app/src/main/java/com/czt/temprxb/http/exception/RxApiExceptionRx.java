package com.czt.temprxb.http.exception;

/**
 * Created by {冯中萌} on 2017/6/6.
 */

public class RxApiExceptionRx extends RxBaseException {
    public RxApiExceptionRx(int errorCode, String errorMsg) {
        super(errorCode, errorMsg);
    }

    public RxApiExceptionRx(int errorCode, int httpcode, String errorMsg) {
        super(errorCode, httpcode, errorMsg);
    }

    public RxApiExceptionRx() {
    }
}
