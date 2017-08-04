package com.example.administrator.izhihucollection.app.base;

import android.database.sqlite.SQLiteDatabase;

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
    private DBOpenHelper helper;

    @Inject
    public RepositoryManager(Lazy<Retrofit> retrofit,DBOpenHelper helper) {
        this.mRetrofit = retrofit;
        this.helper = helper;
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

    @Override
    public SQLiteDatabase obtainDBReadService() {
        return helper.getReadableDatabase();
    }

    @Override
    public SQLiteDatabase obtainDBWriteService() {
        return helper.getWritableDatabase();
    }
}
