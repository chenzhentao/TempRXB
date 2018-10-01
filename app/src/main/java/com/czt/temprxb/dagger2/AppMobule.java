package com.czt.temprxb.dagger2;

import com.czt.temprxb.entity.Bean;
import com.czt.temprxb.http.entity.RxApi;
import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 *  Created by {冯中萌} on 2017/4/19
 */
@Module
public class AppMobule {


    @Provides
    @Singleton
    public RxApi providerRxApi(OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Bean.url)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // 添加Rx适配器
                .addConverterFactory(GsonConverterFactory.create()) // 添加Gson转换器
                .client(okHttpClient)
                .build();
        RxApi rxApi = retrofit.create(RxApi.class);
        return rxApi;
    }


    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient() {
        LoggingInterceptor logging = new LoggingInterceptor(new Logger());
        logging.setLevel(LoggingInterceptor.Level.BODY);

        OkHttpClient.Builder builder = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(30 * 1000, TimeUnit.MILLISECONDS)
                .readTimeout(30 * 1000, TimeUnit.MILLISECONDS)
                .retryOnConnectionFailure(true) // 失败重发
                .addInterceptor(new HeaderInterceptor())
                .addInterceptor(logging);
        OkHttpClient okHttpClient = builder.build();
        return okHttpClient;
    }

    @Singleton
    @Provides
    public Gson provideGson() {
        Gson gson = new Gson();
        return gson;
    }

}
