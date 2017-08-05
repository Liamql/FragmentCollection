package com.example.administrator.izhihucollection.MVP.ui.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.example.administrator.izhihucollection.MVP.contract.CollistContract;
import com.example.administrator.izhihucollection.MVP.model.entity.CList;
import com.example.administrator.izhihucollection.MVP.presenter.CollPresenter;
import com.example.administrator.izhihucollection.MVP.ui.adapter.CollistAdapter;
import com.example.administrator.izhihucollection.R;
import com.example.administrator.izhihucollection.app.base.LActivity;
import com.example.administrator.izhihucollection.di.component.AppComponent;
import com.example.administrator.izhihucollection.di.component.DaggerCollComponent;
import com.example.administrator.izhihucollection.di.module.CollModule;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/8/4 0004.
 */
public class CollectionListActivity extends LActivity<CollPresenter> implements CollistContract.View{

    @BindView(R.id.swiperefresh)
    SwipeRefreshLayout swiperefresh;
    @BindView(R.id.l_recyclerview)
    RecyclerView recycleview;

    private CollistAdapter collistAdapter;
    private ArrayList<CList> listdata = new ArrayList<>();
    private int page = 1;

    @Override
    protected View initView() {
        return LayoutInflater.from(this).inflate(R.layout.coll_list_layout,null,false);
    }

    @Override
    protected void initData() {
        collistAdapter = new CollistAdapter(this,listdata);
        recycleview.setAdapter(collistAdapter);
        swiperefresh.setColorSchemeResources(R.color.textBlueDark, R.color.textBlueDark, R.color.textBlueDark,
                R.color.textBlueDark);

        swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                //homePresenter.loadList(page);
                mPresenter.updateData();
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycleview.setLayoutManager(layoutManager);

        mPresenter.showData();

    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

        DaggerCollComponent.builder()
                .appComponent(appComponent)
                .collModule(new CollModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void showData(ArrayList<CList> mylist) {

        listdata.clear();
        for(CList coll : mylist)
        {
            listdata.add(coll);
        }
        swiperefresh.setRefreshing(false);

        collistAdapter.notifyDataSetChanged();
    }
}
