package com.example.administrator.izhihucollection.MVP.ui.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;

import com.example.administrator.izhihucollection.MVP.contract.HomeContract;
import com.example.administrator.izhihucollection.MVP.model.entity.ArticleListBean;
import com.example.administrator.izhihucollection.MVP.presenter.HomePresenter;
import com.example.administrator.izhihucollection.MVP.ui.IView;
import com.example.administrator.izhihucollection.MVP.ui.activity.MainActivity;
import com.example.administrator.izhihucollection.MVP.ui.adapter.HomeListAdapter;
import com.example.administrator.izhihucollection.R;
import com.example.administrator.izhihucollection.app.base.BaseFragment;
import com.example.administrator.izhihucollection.app.base.LFragment;
import com.example.administrator.izhihucollection.di.component.AppComponent;
import com.example.administrator.izhihucollection.di.component.DaggerHomeComponent;
import com.example.administrator.izhihucollection.di.module.HomeModule;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/8/3 0003.
 */
public class HomeFragment extends LFragment<HomePresenter> implements HomeContract.View {

    @BindView(R.id.swiperefresh)
    SwipeRefreshLayout swiperefresh;
    @BindView(R.id.l_recyclerview)
    RecyclerView recycleview;

    private HomeListAdapter homeListAdapter;
    private ArrayList<ArticleListBean> listData = new ArrayList<>();
    private int page = 1;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.home_list_layout;
    }

    @Override
    public void setupFragmentComponent(AppComponent appComponent) {
        DaggerHomeComponent.builder()
                .appComponent(appComponent)
                .homeModule(new HomeModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void initData() {

        homeListAdapter = new HomeListAdapter(mContext, listData);
        recycleview.setAdapter(homeListAdapter);

        swiperefresh.setColorSchemeResources(R.color.textBlueDark, R.color.textBlueDark, R.color.textBlueDark,
                R.color.textBlueDark);

        swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                //homePresenter.loadList(page);
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycleview.setLayoutManager(layoutManager);

        mPresenter.showData();

    }

    @Override
    public void showData(ArrayList<ArticleListBean> articleListBean) {
        //listData = articleListBean;

        for(ArticleListBean a : articleListBean)
        {
            listData.add(a);
        }

        homeListAdapter.notifyDataSetChanged();
    }

    @Override
    public void getTitle(String title) {
        lActivity.getToolbar().setTitle(title);
    }
}
