package com.example.administrator.izhihucollection.MVP.contract;

import android.os.Handler;

import com.example.administrator.izhihucollection.MVP.model.IModel;
import com.example.administrator.izhihucollection.MVP.model.entity.ArticleListBean;
import com.example.administrator.izhihucollection.MVP.ui.IView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/3 0003.
 */
public interface HomeContract {

    interface View extends IView
    {
        void showData(ArrayList<ArticleListBean> articleListBean);
        void getTitle(String title);
    }

    interface Model extends IModel
    {
        void getData(Handler handler);
    }
}
