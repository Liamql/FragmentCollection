package com.example.administrator.izhihucollection.di.module;

import android.app.Application;


import com.example.administrator.izhihucollection.app.base.IRepositoryManager;
import com.example.administrator.izhihucollection.app.base.RepositoryManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/7/30 0030.
 */
@Module
public class AppModule {
    private Application mApplication;

    public AppModule(Application application) {
        this.mApplication = application;
    }

    @Singleton
    @Provides
    public Application provideApplication() {
        return mApplication;
    }

    @Singleton
    @Provides
    public IRepositoryManager provideRepositoryManager(RepositoryManager repositoryManager) {
        return repositoryManager;
    }

}
