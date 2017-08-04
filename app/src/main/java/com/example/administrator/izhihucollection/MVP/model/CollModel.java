package com.example.administrator.izhihucollection.MVP.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.administrator.izhihucollection.MVP.contract.CollistContract;
import com.example.administrator.izhihucollection.MVP.model.entity.CList;
import com.example.administrator.izhihucollection.MVP.model.service.CollectionService;
import com.example.administrator.izhihucollection.MVP.model.service.CollistService;
import com.example.administrator.izhihucollection.app.base.DBOpenHelper;
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

    private String userID = "su-can-43";

    @Inject
    CollModel(IRepositoryManager iRepositoryManager)
    {
        super(iRepositoryManager);
    }

    @Override
    public void getData(final Handler handler) {

        String content = queryItem(userID);
        if(content==null)
        {
            Log.e("getCollectionList","NetWork");
            Call<ResponseBody> call = mRepositoryManager
                    .obtainRetrofitService(CollistService.class)
                    .getCollist(userID);

            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    try {
                        String result = response.body().string();
                        insert(userID,result);
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
        else
        {
            Log.e("getCollectionList","DB");
            trans(content, handler);
        }

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

    public String queryItem(String url)
    {
        SQLiteDatabase db = mRepositoryManager.obtainDBReadService();
        if(db.isOpen())
        {
            Cursor cursor= db.rawQuery("select * from collectionlist where url= ?;", new String []{url});
            if(cursor!=null&&cursor.moveToFirst())
            {
                String res = cursor.getString(1);
                db.close();
                return res;
            }
            db.close();
        }
        return null;
    }

    public void insert(String url,String content)
    {
        SQLiteDatabase db = mRepositoryManager.obtainDBWriteService();
        if(db.isOpen())
        {
            db.execSQL("insert into collectionlist(url, content) values(?, ?);",
                    new Object[]{url,content});
            db.close();
        }
    }
}
