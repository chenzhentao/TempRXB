package com.czt.temprxb.dagger2;

import com.czt.temprxb.http.entity.RxApi;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Component;


/**
 *  Created by {冯中萌} on 2017/4/19
 */
@Singleton
@Component(modules = AppMobule.class)
public interface AppCompcoent {
    RxApi providerRxApi();
    Gson providerGson();
}
