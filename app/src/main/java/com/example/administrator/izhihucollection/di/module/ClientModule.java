package com.example.administrator.izhihucollection.di.module;


import com.example.administrator.izhihucollection.app.base.AppManager;
import com.example.administrator.izhihucollection.app.base.BaseUrl;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/7/31 0031.
 */
@Module
public class ClientModule
{
    private AppManager mAppManager;
    private HttpUrl mApiUrl;
    private BaseUrl mBaseUrl;
    private static final int TIME_OUT = 10;

    public ClientModule(AppManager appManager) {
        this.mAppManager = appManager;
    }

    @Singleton
    @Provides
    Retrofit provideRetrofit(Retrofit.Builder builder, OkHttpClient client, HttpUrl httpUrl) {

        return builder
                .baseUrl(httpUrl)//域名
                .client(client)//设置okhttp
                .addConverterFactory(GsonConverterFactory.create())//使用Gson
                .build();
    }

    /**
     * 提供OkhttpClient
     *
     * @param okHttpClient
     * @return
     */
    @Singleton
    @Provides
    OkHttpClient provideClient(OkHttpClient.Builder okHttpClient) {
        OkHttpClient.Builder builder = okHttpClient
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(TIME_OUT, TimeUnit.SECONDS);
        return builder
                .build();
    }

    @Singleton
    @Provides
    Retrofit.Builder provideRetrofitBuilder() {
        return new Retrofit.Builder();
    }


    @Singleton
    @Provides
    OkHttpClient.Builder provideClientBuilder() {
        return new OkHttpClient.Builder();
    }

    @Singleton
    @Provides
    HttpUrl provideBaseUrl() {
        if (mBaseUrl != null) {
            HttpUrl httpUrl = mBaseUrl.url();
            if (httpUrl != null) {
                return httpUrl;
            }
        }
        return mApiUrl == null ? HttpUrl.parse("https://api.seniverse.com") : mApiUrl;
    }
}
