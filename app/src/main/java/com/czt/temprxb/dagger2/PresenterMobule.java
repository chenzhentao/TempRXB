package com.czt.temprxb.dagger2;


import com.czt.temprxb.MySendActivityPresenter;
import com.czt.temprxb.RegisterActivityPresenter;
import com.czt.temprxb.http.entity.RxApi;
import com.google.gson.Gson;

import dagger.Module;
import dagger.Provides;


/**
 *  Created by {冯中萌} on 2017/4/19
 */

@Module
public class PresenterMobule {


    @Provides
    public RegisterActivityPresenter gprovideRegisterActivityPresenter(RxApi mapi, Gson gson) {
        return new RegisterActivityPresenter(mapi, gson);
    }
    @Provides
    public MySendActivityPresenter gprovideMySendActivityPresenter(RxApi mapi, Gson gson) {
        return new MySendActivityPresenter(mapi, gson);
    }


}


