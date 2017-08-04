package com.example.administrator.izhihucollection.app.base;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by jess on 17/03/2017 11:15
 * Contact with jess.yan.effort@gmail.com
 */

public interface IRepositoryManager {

    <T> T obtainRetrofitService(Class<T> service);

    SQLiteDatabase obtainDBWriteService();

    SQLiteDatabase obtainDBReadService();

}
