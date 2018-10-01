package com.czt.temprxb.http.rx;

import android.text.TextUtils;

import com.czt.temprxb.http.cconstant.RxHttpConstant;
import com.czt.temprxb.http.exception.RxApiExceptionRx;
import com.czt.temprxb.http.exception.RxBaseException;
import com.google.gson.JsonObject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by {冯中萌} on 2017/6/6.
 */

public class RxHttpRepouseCompat {
    public interface GsonListener<T> {
        T gsonBean(JsonObject jsonObject);
    }


    public static ObservableTransformer<JsonObject, String> compatResult() {
        return new ObservableTransformer<JsonObject, String>() {
            @Override
            public Observable<String> apply(Observable<JsonObject> httpBaseBeanObservable) {

                return httpBaseBeanObservable.flatMap(new Function<JsonObject, Observable<String>>() {
                    @Override
                    public Observable<String> apply(final JsonObject tRxHttpBaseBean) {

                        if (!TextUtils.isEmpty(tRxHttpBaseBean.toString())) {
                            try {
                                int retcode = tRxHttpBaseBean.get(RxHttpConstant.RETCODE).getAsInt();
                                String msg = tRxHttpBaseBean.get(RxHttpConstant.MSG).getAsString();
                                if (retcode == RxHttpConstant.TOKEN_ERROR) {//token失败
                                    return Observable.error(new RxApiExceptionRx(
                                            RxBaseException.API_ERROR,
                                            RxBaseException.API_ERRORTOKEN_CODE, msg));
                                } else if (retcode == RxHttpConstant.TOKEN_TIMEOUT) {//token过期
                                    return Observable.error(new RxApiExceptionRx(
                                            RxBaseException.API_ERROR,
                                            RxBaseException.API_TOKENOUT_CODE, msg));
                                } else if (retcode == RxHttpConstant.SUCCESS) {// 成功
                                    return Observable.create(new ObservableOnSubscribe<String>() {
                                        @Override
                                        public void subscribe(ObservableEmitter<String> e) throws Exception {
                                            if (!e.isDisposed()) {

                                                e.onNext(tRxHttpBaseBean.toString());
                                            }
                                        }

                                    });

                                } else {
                                    return Observable.error(new RxApiExceptionRx(//请求失败
                                            RxBaseException.API_ERROR, msg));
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                return Observable.error(e);
                            }
                        } else {
                            return Observable.error(new RxApiExceptionRx(RxBaseException.API_ERROR,
                                    "服务器接口返回失败"));
                        }
                    }
                }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
            }
        };
    }
    //    public static <T> Observable.Transformer<RxHttpBaseBean<T>, T> compatResult() {
    //        return new Observable.Transformer<RxHttpBaseBean<T>, T>() {
    //            @Override
    //            public Observable<T> call(Observable<RxHttpBaseBean<T>> httpBaseBeanObservable) {
    //                return httpBaseBeanObservable.flatMap(new Func1<RxHttpBaseBean<T>, Observable<T>>() {
    //                    @Override
    //                    public Observable<T> call(final RxHttpBaseBean<T> tRxHttpBaseBean) {
    //                        if (tRxHttpBaseBean.isSuccess()) {
    //                           return Observable.create(new Observable.OnSubscribe<T>() {
    //                                @Override
    //                                public void call(Subscriber<? super T> subscriber) {
    //                                    try {
    //                                        subscriber.onNext(tRxHttpBaseBean.getData());
    //                                        subscriber.onCompleted();
    //                                    } catch (Exception e) {
    //                                        subscriber.onError(e);
    //                                    }
    //                                }
    //                            });
    //                        } else {
    //                            return Observable.error(new RxApiExceptionRx(tRxHttpBaseBean.getRetcode(),
    //                                    tRxHttpBaseBean.getMsg()));
    //                        }
    //                    }
    //                }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
    //            }
    //        };
    //    }
}
