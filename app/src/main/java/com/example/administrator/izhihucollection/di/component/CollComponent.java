package com.example.administrator.izhihucollection.di.component;

import com.example.administrator.izhihucollection.MVP.ui.activity.CollectionListActivity;
import com.example.administrator.izhihucollection.di.module.CollModule;
import com.example.administrator.izhihucollection.di.scope.ActivityScope;

import dagger.Component;

/**
 * Created by Administrator on 2017/8/4 0004.
 */
@ActivityScope
@Component(modules = CollModule.class,dependencies = AppComponent.class)
public interface CollComponent {

    void inject(CollectionListActivity activity);
}
