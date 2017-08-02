package com.example.administrator.izhihucollection.app.base;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.util.Log;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Administrator on 2017/7/31 0031.
 */
@Singleton
public class AppManager {
    protected final String TAG = this.getClass().getSimpleName();

    private Application mApplication;

    //管理所有activity
    public List<Activity> mActivityList;
    //当前在前台的activity
    private Activity mCurrentActivity;

    @Inject
    public AppManager(Application application) {
        this.mApplication = application;
    }

    /**
     * 让在前台的activity,打开下一个activity
     *
     * @param intent
     */
    public void startActivity(Intent intent) {
        if (getCurrentActivity() == null) {
            //如果没有前台的activity就使用new_task模式启动activity
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mApplication.startActivity(intent);
            return;
        }
        getCurrentActivity().startActivity(intent);
    }

    /**
     * 让在前台的activity,打开下一个activity
     *
     * @param activityClass
     */
    public void startActivity(Class activityClass) {
        startActivity(new Intent(mApplication, activityClass));
    }

    /**
     * 将在前台的activity保存
     *
     * @param currentActivity
     */
    public void setCurrentActivity(Activity currentActivity) {
        this.mCurrentActivity = currentActivity;
    }

    /**
     * 获得当前在前台的activity
     *
     * @return
     */
    public Activity getCurrentActivity() {
        return mCurrentActivity;
    }

    /**
     * 添加Activity到集合
     */
    public void addActivity(Activity activity) {
        if (mActivityList == null) {
            mActivityList = new LinkedList<>();
        }
        synchronized (AppManager.class) {
            if (!mActivityList.contains(activity)) {
                Log.d("AppManager", "add:"+activity.getLocalClassName());
                mActivityList.add(activity);
            }
        }
    }

    /**
     * 删除集合里的指定activity
     *
     * @param activity
     */
    public void removeActivity(Activity activity) {
        if (mActivityList == null) {
            return;
        }
        synchronized (AppManager.class) {
            if (mActivityList.contains(activity)) {
                Log.d("AppManager", "remove:"+activity.getLocalClassName());
                mActivityList.remove(activity);
            }
        }
    }


}
