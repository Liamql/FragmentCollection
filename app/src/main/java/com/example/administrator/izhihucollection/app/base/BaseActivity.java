package com.example.administrator.izhihucollection.app.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;


import com.example.administrator.izhihucollection.MVP.presenter.IPresenter;
import com.example.administrator.izhihucollection.R;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/7/30 0030.
 */
public abstract class BaseActivity<P extends IPresenter> extends AppCompatActivity {

    protected BaseApplication mApplication;

    @Inject
    protected P mPresenter;

    @Nullable
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApplication = (BaseApplication) getApplication();
        setContentView(initView());
        ButterKnife.bind(this);//绑定到butterknife
        if(findViewById(R.id.toolbar)!=null)
        {
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            toolbar.setTitle(getTitle().toString());
        }
        ComponentInject();//依赖注入
        initData();
    }

    /**
     * 依赖注入的入口
     *
     */
    protected abstract void ComponentInject();

    protected abstract View initView();

    protected abstract void initData();


}
