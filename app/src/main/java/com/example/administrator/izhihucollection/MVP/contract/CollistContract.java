package com.example.administrator.izhihucollection.MVP.contract;

import android.os.Handler;

import com.example.administrator.izhihucollection.MVP.model.IModel;
import com.example.administrator.izhihucollection.MVP.model.entity.ArticleListBean;
import com.example.administrator.izhihucollection.MVP.model.entity.CList;
import com.example.administrator.izhihucollection.MVP.ui.IView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/8/4 0004.
 */
public interface CollistContract {

    interface View extends IView
    {
        void showData(ArrayList<CList> mylist);
    }

    interface Model extends IModel
    {
        void getData(Handler handler);
    }
}
