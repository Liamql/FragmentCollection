package com.example.administrator.izhihucollection.MVP.ui.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.TextView;

import com.example.administrator.izhihucollection.MVP.model.entity.ArticleListBean;
import com.example.administrator.izhihucollection.MVP.presenter.HomePresenter;
import com.example.administrator.izhihucollection.MVP.ui.adapter.HomeListAdapter;
import com.example.administrator.izhihucollection.R;
import com.example.administrator.izhihucollection.app.base.BaseFragment;
import com.example.administrator.izhihucollection.di.component.AppComponent;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/8/3 0003.
 */
public class HomeFragment extends BaseFragment {

    @BindView(R.id.swiperefresh)
    SwipeRefreshLayout swiperefresh;
    @BindView(R.id.l_recyclerview)
    RecyclerView recycleview;

    private HomePresenter homePresenter;
    private HomeListAdapter homeListAdapter;
    private ArrayList<ArticleListBean> listData = new ArrayList<>();
    private int page = 1;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.home_list_layout;
    }

    @Override
    public void setupFragmentComponent(AppComponent appComponent) {

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

        listData.add(new ArticleListBean("Title1","666",100));

        homeListAdapter.notifyDataSetChanged();

    }
}
