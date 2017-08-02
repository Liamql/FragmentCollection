package com.example.administrator.izhihucollection.app.base;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Administrator on 2017/7/31 0031.
 */
@Singleton
public class ActivityLifecycle implements Application.ActivityLifecycleCallbacks {

    private AppManager mAppManager;

    @Inject
    public ActivityLifecycle(AppManager appManager) {
        this.mAppManager = appManager;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        //如果intent包含了此字段,并且为true说明不加入到list
        // 默认为false,如果不需要管理(比如不需要在退出所有activity(killAll)时，退出此activity就在intent加此字段为true)
        Log.d("Lifecycle","oncreate:"+activity.getLocalClassName());
        mAppManager.addActivity(activity);
    }

    @Override
    public void onActivityStarted(Activity activity) {
        Log.d("Lifecycle","onstart:"+activity.getLocalClassName());
    }

    @Override
    public void onActivityResumed(Activity activity) {
        Log.d("Lifecycle","onresume:"+activity.getLocalClassName());
        mAppManager.setCurrentActivity(activity);
    }

    @Override
    public void onActivityPaused(Activity activity) {
        if (mAppManager.getCurrentActivity() == activity) {
            Log.d("Lifecycle","onpause:"+activity.getLocalClassName());
            mAppManager.setCurrentActivity(null);
        }
    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

        Log.d("Lifecycle","ondestroy:"+activity.getLocalClassName());
        mAppManager.removeActivity(activity);
    }
}
