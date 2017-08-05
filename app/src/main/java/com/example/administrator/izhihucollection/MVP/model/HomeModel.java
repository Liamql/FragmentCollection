package com.example.administrator.izhihucollection.MVP.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.administrator.izhihucollection.MVP.contract.HomeContract;
import com.example.administrator.izhihucollection.MVP.model.entity.ArticleListBean;
import com.example.administrator.izhihucollection.MVP.model.service.CollectionService;
import com.example.administrator.izhihucollection.app.base.IRepositoryManager;
import com.example.administrator.izhihucollection.di.module.HomeModule;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    public void getData(final Handler handler,final String herf) {

        String content = queryItem(herf);
        if(content!=null)
        {
            Log.e("getCollection","DB");
            trans(content, handler);
        }
        else {

            String herfid = herf.replace("/collection/", "");
            Log.e("getCollection","Network");
            getDataFromNetWork(herfid,handler);
        }

        //return new ArticleListBean("title","summary","author","description","100");
    }

    @Override
    public void updateData(Handler handler, String herf) {
        Log.e("updateCollection","Network");
        String herfid = herf.replace("/collection/", "");
        getDataFromNetWork(herfid,handler);
    }

    void getDataFromNetWork(final String url,final Handler handler)
    {
        Call<ResponseBody> call = mRepositoryManager
                .obtainRetrofitService(CollectionService.class)
                .getCollection(url);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String result = response.body().string();
                    trans(result, handler);
                    update(url, result);
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
        ArrayList<ArticleListBean> collist = new ArrayList<>();

        Document doc = Jsoup.parse(html);
        Elements list_title_container = doc.select("div.zg-section-title");
        Document TitleDoc = Jsoup.parse(list_title_container.toString());
        Elements collectionTitle = TitleDoc.select("h2");
        Message msg = Message.obtain();
        msg.what =1;
        msg.obj = collectionTitle.text();
        handler.sendMessage(msg);
        //Log.e("a",collectionTitle.text());

        Element item_container = doc.getElementById("zh-list-collection-wrap");
        Document itemCtrDoc = Jsoup.parse(item_container.toString());
        Elements clearfix = itemCtrDoc.select(".zm-item");  //选择器的形式
        for(Element zmItem : clearfix)
        {
            String title,author,author_des,summary,likecount,content;
            Document zmItemDoc = Jsoup.parse(zmItem.toString());
            Elements TitleEle = zmItemDoc.select("h2");
            title = TitleEle.text();
            //Log.e("title", title);

            Elements vote_count = zmItemDoc.select("a.zm-item-vote-count");
            likecount = vote_count.text();
            //Log.e("likecount", likecount);

            Elements author_link = zmItemDoc.select("span.author-link-line");
            author = author_link.text();
            //Log.e("author", author);

            Elements authorDes = zmItemDoc.select("span.bio");
            author_des = authorDes.attr("title");
            //Log.e("authorDes", author_des);


            Elements zm_item_answer = zmItemDoc.select("div.zm-item-answer");
            Document zm_item_answer_doc = Jsoup.parse(zm_item_answer.toString());
            Elements zm_item_rich_text = zm_item_answer_doc.select("div.zm-item-rich-text");
            //Log.e("c",zm_item_rich_text.attr("data-entry-url"));


            Elements summary_wrapper = zm_item_answer_doc.select("div.zh-summary");
            summary = summary_wrapper.text();
            //Log.e("summary",summary);

            Elements content_wrapper = zm_item_answer_doc.select("textarea.content");
            content = content_wrapper.text();
            //Log.e("content", content);


            collist.add(new ArticleListBean(title,summary,author,author_des,likecount,content));
        }
        Message msg2 = Message.obtain();
        msg2.what =2;
        msg2.obj = (Object) collist;
        handler.sendMessage(msg2);
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

    public void update(String url,String content)
    {
        SQLiteDatabase db = mRepositoryManager.obtainDBWriteService();
        if(db.isOpen())
        {
            db.execSQL("replace into collectionlist(url, content) values(?, ?);",
                    new Object[]{url,content});
            db.close();
        }
    }

}
