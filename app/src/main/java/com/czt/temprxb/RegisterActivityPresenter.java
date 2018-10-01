package com.czt.temprxb;

import android.util.Log;

import com.czt.temprxb.framework.presenter.BasePresenter;
import com.czt.temprxb.framework.util.To;
import com.czt.temprxb.http.entity.RxApi;
import com.czt.temprxb.http.rx.RxExceptionSubscriber;
import com.czt.temprxb.http.rx.RxHttpRepouseCompat;
import com.czt.temprxb.persenter.IPresenter;
import com.google.gson.Gson;

import cn.droidlover.xdroidmvp.mvp.IView;

public class RegisterActivityPresenter extends BasePresenter<IView.IRegisterActivityView> implements
        IPresenter.IRegisterPresenter {

    public RegisterActivityPresenter(RxApi mApi, Gson mGson) {
        super(mApi, mGson);
    }

    @Override
    public void registerUser(String mobile, String password, String password2, String code) {

        String[] sort = CallPostUtils.newBuilder()
                .addParamt("mobile", mobile)
                .addParamt("password", password)
                .addParamt("password2", password2)
                .addParamt("code", code)
                .build().sort();
        if (sort == null) {
            To.ee("排序签名失败请重试");
            return;
        }

        mApi.register(mobile, password, password2, code, "0", "2")
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
                            getView().registerError(errorMsg);
                        }
                    }


                    @Override
                    protected void onSuccess(String user) {
                        Log.e("tag", user);
                        if (getView() != null) {
                            getView().registerSuccess(user);
                        }
                    }
                });
    }
}

