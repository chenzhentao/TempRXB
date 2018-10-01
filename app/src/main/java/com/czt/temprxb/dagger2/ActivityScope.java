package com.czt.temprxb.dagger2;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 *  Created by {冯中萌} on 2017/4/19
 */
@Scope//注明是scope
@Documented//标记在文档
@Retention(RUNTIME)//运行时级别 可以修改级别
public @interface ActivityScope {
}
