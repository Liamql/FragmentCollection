package com.example.administrator.izhihucollection.MVP.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.izhihucollection.MVP.model.entity.CList;
import com.example.administrator.izhihucollection.MVP.ui.activity.CollectionActivity;
import com.example.administrator.izhihucollection.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/8/4 0004.
 */
public class CollistAdapter extends RecyclerView.Adapter<CollistAdapter.ViewHolder> {

    Context context;
    public ArrayList<CList> data = null;

    public static String TAG = "list";


    public CollistAdapter(Context context, ArrayList<CList> data) {
        this.context = context;
        this.data = data;
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView title_text;


        public ViewHolder(View view) {
            super(view);
            title_text = (TextView) view.findViewById(R.id.title_text);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.coll_list_item, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.title_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, CollectionActivity.class);
                intent.putExtra(TAG, data.get(position).getHref());
                context.startActivity(intent);
            }
        });

        String collist_title = data.get(position).getTitle();
        holder.title_text.setText(collist_title);

    }

    //获取数据的数量
    @Override
    public int getItemCount() {
        return data.size();
    }
}
