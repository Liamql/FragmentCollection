package com.example.administrator.izhihucollection.di.component;

import android.app.Application;

import com.example.administrator.izhihucollection.app.base.AppManager;
import com.example.administrator.izhihucollection.app.base.IRepositoryManager;
import com.example.administrator.izhihucollection.di.module.AppModule;
import com.example.administrator.izhihucollection.di.module.ClientModule;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;

/**
 * Created by Administrator on 2017/7/31 0031.
 */
@Singleton
@Component(modules = {AppModule.class,ClientModule.class})
public interface AppComponent {
    Application Application();

    //用于管理所有activity
    AppManager appManager();

    OkHttpClient okHttpClient();

    //用于管理网络请求层,以及数据缓存层
    IRepositoryManager repositoryManager();
}