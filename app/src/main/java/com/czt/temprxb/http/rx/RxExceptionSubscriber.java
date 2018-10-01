package com.czt.temprxb.http.rx;

import com.czt.temprxb.http.exception.RxApiExceptionRx;
import com.czt.temprxb.http.exception.RxBaseException;
import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.net.SocketException;
import java.net.SocketTimeoutException;

import cn.droidlover.xdroidmvp.mvp.IView;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;




/**
 * Created by {冯中萌} on 2017/6/6.
 */

public abstract class RxExceptionSubscriber<E> extends RxBaseSubscriber<E> {
    private Reference<IView> mIView;

    public RxExceptionSubscriber(IView iView) {
        mIView = new WeakReference<>(iView);
    }


    @Override
    public void onSubscribe(Disposable s) {

        if (isShowLoadingDialog()) {
            if (mIView.get() == null) {
                return;
            }
            mIView.get().showLoodingDialog(null);
        }
    }

    /**
     * 是否显示记载对话框
     *
     * @return
     */
    protected abstract boolean isShowLoadingDialog();


    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
//        RxBaseException baseException = new RxBaseException();

        if (e instanceof RxApiExceptionRx) {//是我们自己定义的异常
            if (mIView.get() == null) {
                return;
            }
            mIView.get().hideDiaLogView();
            RxApiExceptionRx mRxApiExceptionRx = (RxApiExceptionRx) e;
            if (mRxApiExceptionRx.getHttpcode() == RxBaseException.API_TOKENOUT_CODE//5000登陆超时
                    || mRxApiExceptionRx.getHttpcode() == RxBaseException.API_ERRORTOKEN_CODE) {//3000token验证失败
//                mIView.get().showLoginView(null);
//                RongIMLoginManager.getInstance().disConnectRongIM(true);//关闭融云
            } else {
                apiError(mRxApiExceptionRx.getHttpcode(), mRxApiExceptionRx.getErrorMsg());
            }
        } else if (e instanceof SocketException) {//网络异常
            if (mIView.get() == null) {
                return;
            }
            mIView.get().showNoNetworkView("网络异常");
        } else if (e instanceof SocketTimeoutException) {//网络超时
            if (mIView.get() == null) {
                return;
            }
            mIView.get().showTimeErrorView(null);
        } else if (e instanceof HttpException) {//请求异常
            if (mIView.get() == null) {
                return;
            }
            mIView.get().showErrorView("请求异常" + ((HttpException) e).message());
        } else if (e instanceof JsonParseException || e instanceof JSONException) {//gson解析异常
            if (mIView.get() == null) {
                return;
            }
            mIView.get().showErrorView("解析异常");
        } else {
            if (mIView.get() == null) {
                return;
            }
            mIView.get().showErrorView("亲非常抱歉，我们的攻城狮正在努力解决...");
        }

//        baseException.setErrorMsg(StringErrorMsgFactory.getRxErrorMsg(baseException.getErrorCode(),
//                baseException.getHttpcode(), baseException.getErrorMsg()));

    }

    protected abstract void apiError(int code, String errorMsg);//服务器返回的错误

    @Override
    public void onNext(E e) {
        if (mIView.get() == null) {
            return;
        }
        mIView.get().showContentView(null);
        onSuccess(e);
    }

    protected abstract void onSuccess(E string) ;

    @Override
    public void onComplete() {

    }


}
