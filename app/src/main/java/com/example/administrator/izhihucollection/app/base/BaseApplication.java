package com.example.administrator.izhihucollection.app.base;

import android.app.Application;

import com.example.administrator.izhihucollection.di.component.DaggerBaseComponent;
import com.example.administrator.izhihucollection.di.module.AppModule;
import com.example.administrator.izhihucollection.di.module.ClientModule;

import javax.inject.Inject;

//import com.example.administrator.l_mvp.di.component.DaggerBaseComponent;

/**
 * Created by Administrator on 2017/7/31 0031.
 */
public class BaseApplication extends Application {

    static private BaseApplication mApplication;
    private AppModule mAppModule;
    private ClientModule mClientModule;
    @Inject
    protected AppManager mAppManager;
    @Inject
    protected ActivityLifecycle mActivityLifecycle;


    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        this.mAppModule = new AppModule(this);//提供application

        DaggerBaseComponent.builder()
                .appModule(mAppModule)
                .build()
                .inject(this);
        this.mClientModule = new ClientModule(mAppManager);
        registerActivityLifecycleCallbacks(mActivityLifecycle);

    }

    public AppModule getAppModule() {
        return mAppModule;
    }

    public AppManager getAppManager() {
        return mAppManager;
    }

    public ClientModule getClientModule() {
        return mClientModule;
    }

}
