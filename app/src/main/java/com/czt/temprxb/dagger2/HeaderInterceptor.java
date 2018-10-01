/**
 * Copyright 2016 JustWayward Team
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.czt.temprxb.dagger2;

import android.util.Log;

import java.io.IOException;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Retrofit2 Header拦截器。用于保存和设置Cookies
 *
 * @author yuyh.
 * @date 16/8/6.
 */
public final class HeaderInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();

        String url = original.url().toString();

        String method = original.method();//获取请求方式
        if (method.equals("GET")) {

            HttpUrl url1 = original.url();
            List<String> strings = url1.encodedPathSegments();
            for (int i = 0; i < strings.size(); i++) {
                Log.e("12345", "intercept: =" + strings.get(i));
            }

        } else if (method.equals("POST")) {
        }

//        if (url.contains("book/") ||
//                url.contains("book-list/") ||
//                url.contains("toc/") ||
//                url.contains("post/") ||
//                url.contains("user/")) {
//            Request request = original.newBuilder()
//                    .addHeader("User-Agent", "ZhuiShuShenQi/3.40[preload=false;locale=zh_CN;clientidbase=android-nvidia]") // 不能转UTF-8
//                    .addHeader("X-User-Agent", "ZhuiShuShenQi/3.40[preload=false;locale=zh_CN;clientidbase=android-nvidia]")
//                    .addHeader("X-Device-Id", DeviceUtils.getIMEI(AppUtils.getAppContext()))
//                    .addHeader("Host", "api.zhuishushenqi.com")
//                    .addHeader("Connection", "Keep-Alive")
//                    .addHeader("If-None-Match", "W/\"2a04-4nguJ+XAaA1yAeFHyxVImg\"")
//                    .addHeader("If-Modified-Since", "Tue, 02 Aug 2016 03:20:06 UTC")
//                    .build();
//            return chain.proceed(request);
//        }

//        original.newBuilder().addHeader()


        return chain.proceed(original);
    }
}
