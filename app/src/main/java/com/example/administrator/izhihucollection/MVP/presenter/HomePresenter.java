package com.example.administrator.izhihucollection.MVP.presenter;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.administrator.izhihucollection.MVP.contract.HomeContract;
import com.example.administrator.izhihucollection.MVP.model.entity.ArticleListBean;

import java.util.ArrayList;
import java.util.List;

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

    Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what)
            {
                case 1:
                    mRootView.getTitle(msg.obj.toString());
                    break;
                case 2:
                    ArrayList<ArticleListBean> list = (ArrayList<ArticleListBean>)msg.obj;
                    mRootView.showData(list);
                    break;
                default:
                    break;
            }
        }
    };

    public void showData(String herf)
    {
        mModel.getData(handler,herf);
    }

}
