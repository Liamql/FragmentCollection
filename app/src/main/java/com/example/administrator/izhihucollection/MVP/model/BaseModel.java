package com.example.administrator.izhihucollection.MVP.model;


import com.example.administrator.izhihucollection.app.base.IRepositoryManager;

/**
 * Created by Administrator on 2017/8/1 0001.
 */
public class BaseModel implements IModel {
    protected IRepositoryManager mRepositoryManager;//用于管理网络请求层,以及数据缓存层

    public BaseModel(IRepositoryManager repositoryManager) {
        this.mRepositoryManager = repositoryManager;
    }

}
