package com.example.administrator.izhihucollection.app.base;

import com.example.administrator.izhihucollection.MVP.presenter.IPresenter;
import com.example.administrator.izhihucollection.di.component.AppComponent;

/**
 * Created by Administrator on 2017/8/3 0003.
 */
public abstract class LFragment<P extends IPresenter> extends BaseFragment<P> {

    protected LApplication mLApplication;

    @Override
    protected void ComponentInject() {
        mLApplication = (LApplication) mContext.getApplicationContext();
        setupFragmentComponent(mLApplication.getAppComponent());
    }
    //提供AppComponent(提供所有的单例对象)给子类，进行Component依赖
    protected abstract void setupFragmentComponent(AppComponent appComponent);
}
