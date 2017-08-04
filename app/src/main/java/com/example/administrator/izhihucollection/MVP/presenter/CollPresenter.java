package com.example.administrator.izhihucollection.MVP.presenter;

import android.os.Handler;
import android.os.Message;

import com.example.administrator.izhihucollection.MVP.contract.CollistContract;
import com.example.administrator.izhihucollection.MVP.model.entity.CList;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * Created by Administrator on 2017/8/4 0004.
 */
public class CollPresenter extends BasePresenter<CollistContract.Model,CollistContract.View> {

    @Inject
    public CollPresenter(CollistContract.Model model,CollistContract.View view)
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
                    ArrayList<CList> list = (ArrayList)msg.obj;
                    mRootView.showData(list);
                    break;
                default:
                    break;
            }
        }
    };

    public void showData()
    {
        mModel.getData(handler);
    }

}
