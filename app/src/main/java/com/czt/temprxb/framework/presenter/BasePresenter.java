package com.czt.temprxb.framework.presenter;


import com.czt.temprxb.http.entity.RxApi;
import com.google.gson.Gson;

import cn.droidlover.xdroidmvp.mvp.XPresenter;

/**
 * Created by {冯中萌} on 2017/6/6.
 */

public abstract class BasePresenter<T> extends XPresenter<T> {

    protected RxApi mApi;

    public BasePresenter(RxApi mApi, Gson mGson) {
        super(mGson);
        this.mApi = mApi;


    }


}
