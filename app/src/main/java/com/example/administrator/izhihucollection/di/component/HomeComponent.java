package com.example.administrator.izhihucollection.di.component;

import com.example.administrator.izhihucollection.MVP.ui.fragment.HomeFragment;
import com.example.administrator.izhihucollection.di.module.HomeModule;
import com.example.administrator.izhihucollection.di.scope.ActivityScope;

import dagger.Component;

/**
 * Created by Administrator on 2017/8/3 0003.
 */
@ActivityScope
@Component(modules = HomeModule.class,dependencies = AppComponent.class)
public interface HomeComponent {
    void inject(HomeFragment homeFragment);
}
