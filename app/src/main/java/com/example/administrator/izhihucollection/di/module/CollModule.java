package com.example.administrator.izhihucollection.di.module;

import com.example.administrator.izhihucollection.MVP.contract.CollistContract;
import com.example.administrator.izhihucollection.MVP.model.CollModel;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/8/4 0004.
 */
@Module
public class CollModule {

    private CollistContract.View view;

    public CollModule(CollistContract.View view)
    {
        this.view = view;
    }

    @Provides
    CollistContract.View provideCollView()
    {
        return view;
    }

    @Provides
    CollistContract.Model provideCollModel(CollModel model)
    {
        return model;
    }

}
