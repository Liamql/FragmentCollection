package com.example.administrator.izhihucollection.MVP.contract;

import com.example.administrator.izhihucollection.MVP.model.IModel;
import com.example.administrator.izhihucollection.MVP.model.entity.ArticleListBean;
import com.example.administrator.izhihucollection.MVP.ui.IView;

/**
 * Created by Administrator on 2017/8/3 0003.
 */
public interface HomeContract {

    interface View extends IView
    {
        void showData(ArticleListBean articleListBean);
    }

    interface Model extends IModel
    {
        ArticleListBean getData();
    }
}
