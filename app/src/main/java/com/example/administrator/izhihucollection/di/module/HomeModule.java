package com.example.administrator.izhihucollection.di.module;

import com.example.administrator.izhihucollection.MVP.contract.HomeContract;
import com.example.administrator.izhihucollection.MVP.model.HomeModel;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/8/3 0003.
 */
@Module
public class HomeModule {

    private HomeContract.View view;

    public HomeModule(HomeContract.View view)
    { this.view = view;}

    @Provides
    HomeContract.View provideHomeView()
    {
        return this.view;
    }

    @Provides
    HomeContract.Model provideHomeModel(HomeModel model)
    {
        return model;
    }

}
