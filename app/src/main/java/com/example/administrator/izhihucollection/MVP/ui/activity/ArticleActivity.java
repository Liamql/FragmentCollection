package com.example.administrator.izhihucollection.MVP.ui.activity;

import android.content.Intent;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.izhihucollection.MVP.model.entity.ArticleListBean;
import com.example.administrator.izhihucollection.MVP.ui.adapter.HomeListAdapter;
import com.example.administrator.izhihucollection.MVP.ui.widget.RichText;
import com.example.administrator.izhihucollection.R;
import com.example.administrator.izhihucollection.app.base.LActivity;
import com.example.administrator.izhihucollection.di.component.AppComponent;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/8/3 0003.
 */
public class ArticleActivity extends LActivity {

    private ArticleListBean articleListBean;

    @BindView(R.id.tv_likecount)
    TextView tv_likecount;
    @BindView(R.id.tag_author) TextView tv_author;
    @BindView(R.id.tag_intro) TextView tv_intro;
    @BindView(R.id.tv_content)
    RichText tv_content;

    @Override
    protected View initView() {
        return LayoutInflater.from(this).inflate(R.layout.article_main,null,false);
    }

    @Override
    protected void initData() {
        tv_content.setMovementMethod(ScrollingMovementMethod.getInstance());
        articleListBean = (ArticleListBean) getIntent().
                getParcelableExtra(HomeListAdapter.TAG);
        getToolbar().setTitle(articleListBean.getTitle());
        tv_likecount.setText(articleListBean.getLikesCount());
        tv_author.setText(articleListBean.getAuthor());
        tv_intro.setText(articleListBean.getAuthor_des());
        tv_content.setRichText(articleListBean.getContent());

    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }
}
