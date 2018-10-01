package com.czt.temprxb;

import android.util.Log;

import com.czt.temprxb.entity.User;
import com.czt.temprxb.framework.presenter.BasePresenter;
import com.czt.temprxb.framework.util.To;
import com.czt.temprxb.http.entity.RxApi;
import com.czt.temprxb.http.rx.RxExceptionSubscriber;
import com.czt.temprxb.http.rx.RxHttpRepouseCompat;
import com.czt.temprxb.persenter.IPresenter;
import com.google.gson.Gson;

import cn.droidlover.xdroidmvp.mvp.IView;

public class MySendActivityPresenter extends BasePresenter<IView.IMySendActivityView> implements IPresenter.IMySendPresenter {
    public MySendActivityPresenter(RxApi mApi, Gson mGson) {
        super(mApi, mGson);
    }

    @Override
    public void getMySendMessage(String page, String userId, String startDateStr) {
        String[] sort = CallPostUtils.newBuilder()
                .addParamt("page", page)
                .addParamt("userId", userId)
                .addParamt("startDateStr", startDateStr)

                .build().sort();
        if (sort == null) {
            To.ee("排序签名失败请重试");
            return;
        }

        mApi.sendMessage(page, userId, startDateStr,  "0", "2")
                .compose(RxHttpRepouseCompat.compatResult())
                .subscribe(new RxExceptionSubscriber<String>(getView()) {
                    @Override
                    protected boolean isShowLoadingDialog() {
                        return true;
                    }

                    @Override
                    protected void apiError(int code, String errorMsg) {
                        Log.e("tag", errorMsg);
                        if (getView() != null) {
                            getView().showContentView(null);
                            getView().getError(errorMsg);
                        }
                    }


                    @Override
                    protected void onSuccess(String user) {
                        Log.e("tag", user);
                        if (getView() != null) {
                            getView().getSuccess(mGson.fromJson(user, User.class));
                        }
                    }
                });
    }
    }

