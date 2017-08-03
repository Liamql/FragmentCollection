package com.example.administrator.izhihucollection.MVP.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.izhihucollection.MVP.model.entity.ArticleListBean;
import com.example.administrator.izhihucollection.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/3 0003.
 */
public class HomeListAdapter extends RecyclerView.Adapter<HomeListAdapter.ViewHolder> {

    Context context;
    public ArrayList<ArticleListBean> data = null;

    public HomeListAdapter(Context context, ArrayList<ArticleListBean> data) {
        this.context = context;
        this.data = data;
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_summary;
        public TextView tv_likecount;
        public TextView tag_text;
        public TextView title_text;
        public TextView author_text;
        public LinearLayout content_layout;

        public ViewHolder(View view) {
            super(view);
            tv_summary = (TextView) view.findViewById(R.id.tv_summary);
            tv_likecount = (TextView) view.findViewById(R.id.tv_likecount);
            tag_text = (TextView) view.findViewById(R.id.tag_text);
            author_text = (TextView) view.findViewById(R.id.tag_author);
            title_text = (TextView) view.findViewById(R.id.title_text);
            content_layout = (LinearLayout) view.findViewById(R.id.content_layout);

        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_list_item, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tag_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent intent = new Intent(context, ArticleDetailActivity.class);
                intent.putExtra(ArticleDetailFragment.ARG_ITEM_INFO, data.get(position));
                context.startActivity(intent);*/
            }
        });

        holder.title_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent intent = new Intent(context, ArticleDetailActivity.class);
                intent.putExtra(ArticleDetailFragment.ARG_ITEM_INFO, data.get(position));
                context.startActivity(intent);*/
            }
        });
        holder.content_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent intent = new Intent(context, ArticleDetailActivity.class);
                intent.putExtra(ArticleDetailFragment.ARG_ITEM_INFO, data.get(position));
                context.startActivity(intent);*/
            }
        });

        ArticleListBean articleListBean = data.get(position);
        holder.title_text.setText(articleListBean.getTitle());
        holder.author_text.setText(articleListBean.getAuthor());
        holder.tag_text.setText(articleListBean.getAuthor_des());
        holder.tv_summary.setText(articleListBean.getSummary());
        holder.tv_likecount.setText(articleListBean.getLikesCount());

    }

    //获取数据的数量
    @Override
    public int getItemCount() {
        return data.size();
    }

}
