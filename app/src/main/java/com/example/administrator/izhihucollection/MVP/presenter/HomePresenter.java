package com.example.administrator.izhihucollection.MVP.presenter;

import com.example.administrator.izhihucollection.MVP.contract.HomeContract;
import com.example.administrator.izhihucollection.MVP.model.entity.ArticleListBean;

import javax.inject.Inject;

/**
 * Created by Administrator on 2017/8/3 0003.
 */
public class HomePresenter extends BasePresenter<HomeContract.Model,HomeContract.View> {

    @Inject
    HomePresenter(HomeContract.Model model,HomeContract.View view)
    {
        super(model,view);
    }

    public void showData()
    {
        ArticleListBean articleListBean =mModel.getData();
        mRootView.showData(articleListBean);
    }

}
