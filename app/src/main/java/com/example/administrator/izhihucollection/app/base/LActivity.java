package com.example.administrator.izhihucollection.app.base;


import com.example.administrator.izhihucollection.MVP.presenter.IPresenter;
import com.example.administrator.izhihucollection.di.component.AppComponent;

/**
 * Created by Administrator on 2017/7/31 0031.
 */
public abstract class LActivity<P extends IPresenter> extends BaseActivity<P> {

    protected LApplication mLApplication;

    @Override
    protected void ComponentInject() {
        mLApplication = (LApplication) getApplication();
        setupActivityComponent(mLApplication.getAppComponent());
    }

    //提供AppComponent(提供所有的单例对象)给子类，进行Component依赖
    protected abstract void setupActivityComponent(AppComponent appComponent);

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.mLApplication = null;
    }
}
