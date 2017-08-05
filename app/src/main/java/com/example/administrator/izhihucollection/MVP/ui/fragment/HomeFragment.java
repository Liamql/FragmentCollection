package com.example.administrator.izhihucollection.MVP.ui.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.administrator.izhihucollection.MVP.contract.HomeContract;
import com.example.administrator.izhihucollection.MVP.model.entity.ArticleListBean;
import com.example.administrator.izhihucollection.MVP.presenter.HomePresenter;
import com.example.administrator.izhihucollection.MVP.ui.adapter.HomeListAdapter;
import com.example.administrator.izhihucollection.R;
import com.example.administrator.izhihucollection.app.base.LFragment;
import com.example.administrator.izhihucollection.di.component.AppComponent;
import com.example.administrator.izhihucollection.di.component.DaggerHomeComponent;
import com.example.administrator.izhihucollection.di.module.HomeModule;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/8/3 0003.
 */
public class HomeFragment extends LFragment<HomePresenter> implements HomeContract.View {

    @BindView(R.id.swiperefresh)
    SwipeRefreshLayout swiperefresh;
    @BindView(R.id.l_recyclerview)
    RecyclerView recycleview;

    private String herf;

    public static String TAG = "list";

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

        herf = getArguments().getString(TAG);

        homeListAdapter = new HomeListAdapter(mContext, listData);
        recycleview.setAdapter(homeListAdapter);

        swiperefresh.setColorSchemeResources(R.color.textBlueDark, R.color.textBlueDark, R.color.textBlueDark,
                R.color.textBlueDark);

        swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                mPresenter.updateData(herf);
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycleview.setLayoutManager(layoutManager);

        mPresenter.showData(herf);

    }

    @Override
    public void showData(ArrayList<ArticleListBean> articleListBean) {
        //listData = articleListBean;

        listData.clear();
        for(ArticleListBean a : articleListBean)
        {
            listData.add(a);
        }
        swiperefresh.setRefreshing(false);
        homeListAdapter.notifyDataSetChanged();

    }

    @Override
    public void getTitle(String title) {
        lActivity.getToolbar().setTitle(title);
    }
}
