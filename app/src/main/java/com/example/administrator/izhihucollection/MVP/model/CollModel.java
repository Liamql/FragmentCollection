package com.example.administrator.izhihucollection.MVP.model;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.administrator.izhihucollection.MVP.contract.CollistContract;
import com.example.administrator.izhihucollection.MVP.model.entity.CList;
import com.example.administrator.izhihucollection.MVP.model.service.CollectionService;
import com.example.administrator.izhihucollection.MVP.model.service.CollistService;
import com.example.administrator.izhihucollection.app.base.IRepositoryManager;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/8/4 0004.
 */
public class CollModel extends BaseModel implements CollistContract.Model {

    @Inject
    CollModel(IRepositoryManager iRepositoryManager)
    {
        super(iRepositoryManager);
    }

    @Override
    public void getData(final Handler handler) {

        Call<ResponseBody> call = mRepositoryManager
                .obtainRetrofitService(CollistService.class)
                .getCollist("su-can-43");

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String result = response.body().string();
                    trans(result, handler);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }

    public void trans(String html,Handler handler)
    {
        ArrayList<CList> list = new ArrayList<>();

        Document doc = Jsoup.parse(html);
        Element main_container = doc.getElementById("Profile-collections");
        Document main_doc = Jsoup.parse(main_container.toString());
        //Log.e("Main",main_doc.toString());
        Elements clearfix = main_doc.select(".List-item");  //选择器的形式
        for(Element zmItem : clearfix) {

            Document zmItemDoc = Jsoup.parse(zmItem.toString());
            Elements TitleEle = zmItemDoc.select(".FavlistItem-title a");
            //Log.e("TAG",TitleEle.attr("href"));
            list.add(new CList(TitleEle.text(),TitleEle.attr("href")));
        }

        Message msg = Message.obtain();
        msg.what = 1;
        msg.obj = list;
        handler.sendMessage(msg);
    }
}
