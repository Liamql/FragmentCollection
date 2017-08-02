package com.example.administrator.izhihucollection.app.base;


//import com.example.administrator.l_mvp.di.component.DaggerAppComponent;
//import com.example.administrator.l_mvp.di.component.DaggerAppComponent;


import com.example.administrator.izhihucollection.di.component.AppComponent;
import com.example.administrator.izhihucollection.di.component.DaggerAppComponent;

/**
 * Created by Administrator on 2017/7/30 0030.
 */
public class LApplication extends BaseApplication {

    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mAppComponent = DaggerAppComponent
                .builder()
                .appModule(getAppModule())//baseApplication提供
                .clientModule(getClientModule())//baseApplication提供
                .build();
    }

    /**
     * 将AppComponent返回出去,供其它地方使用, AppComponent接口中声明的方法返回的实例, 在getAppComponent()拿到对象后都可以直接使用
     *
     * @return
     */
    public AppComponent getAppComponent() {
        return mAppComponent;
    }

/*
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                if(activity instanceof IActivity)
                {
                    activity.setContentView(((IActivity) activity).initView());
                    ButterKnife.bind(activity);
                    ((IActivity) activity).initData();
                }
                else
                {
                    ButterKnife.bind(activity);
                }
                if (activity.findViewById(R.id.toolbar) != null) {
                    if (activity instanceof AppCompatActivity) {
                        Toolbar toolbar = (Toolbar) activity.findViewById(R.id.toolbar);
                        ((AppCompatActivity) activity).setSupportActionBar(toolbar);
                        ((AppCompatActivity) activity).getSupportActionBar().setDisplayShowTitleEnabled(false);
                        toolbar.setTitle(activity.getTitle().toString());
                    }
                }
            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });
    }*/
}
