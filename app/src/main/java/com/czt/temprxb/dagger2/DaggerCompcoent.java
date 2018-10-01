package com.czt.temprxb.dagger2;


import com.czt.temprxb.MySendActivity;
import com.czt.temprxb.RegistActivity;

import dagger.Component;


/**
 *  Created by {冯中萌} on 2017/4/19
 */
@ActivityScope
@Component(modules = PresenterMobule.class, dependencies = AppCompcoent.class)
public interface DaggerCompcoent {
    void inject(RegistActivity activity);

    void inject(MySendActivity activity);
}
