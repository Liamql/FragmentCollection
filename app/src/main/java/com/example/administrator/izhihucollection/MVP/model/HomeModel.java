package com.example.administrator.izhihucollection.MVP.model;

import com.example.administrator.izhihucollection.MVP.contract.HomeContract;
import com.example.administrator.izhihucollection.MVP.model.entity.ArticleListBean;
import com.example.administrator.izhihucollection.app.base.IRepositoryManager;
import com.example.administrator.izhihucollection.di.module.HomeModule;

import javax.inject.Inject;

/**
 * Created by Administrator on 2017/8/3 0003.
 */
public class HomeModel extends BaseModel implements HomeContract.Model {

    @Inject
    public HomeModel(IRepositoryManager repositoryManager)
    {
        super(repositoryManager);
    }

    @Override
    public ArticleListBean getData() {
        return new ArticleListBean("title","author",50);
    }
}
