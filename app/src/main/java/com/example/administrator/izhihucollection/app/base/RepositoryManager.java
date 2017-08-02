package com.example.administrator.izhihucollection.app.base;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Lazy;
import retrofit2.Retrofit;

/**
 * Created by Administrator on 2017/8/1 0001.
 */
@Singleton
public class RepositoryManager implements IRepositoryManager {
    private Lazy<Retrofit> mRetrofit;

    @Inject
    public RepositoryManager(Lazy<Retrofit> retrofit) {
        this.mRetrofit = retrofit;
    }

    /**
     * 根据传入的Class获取对应的Retrift service
     *
     * @param service
     * @param <T>
     * @return
     */
    @Override
    public <T> T obtainRetrofitService(Class<T> service) {
        T retrofitService;
        retrofitService = mRetrofit.get().create(service);

        return retrofitService;
    }


}
