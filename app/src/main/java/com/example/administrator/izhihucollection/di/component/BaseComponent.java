package com.example.administrator.izhihucollection.di.component;


import com.example.administrator.izhihucollection.app.base.BaseApplication;
import com.example.administrator.izhihucollection.di.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Administrator on 2017/7/31 0031.
 */
@Singleton
@Component(modules={AppModule.class})
public interface BaseComponent {
    void inject(BaseApplication application);
}