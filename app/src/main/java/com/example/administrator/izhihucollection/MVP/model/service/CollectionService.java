package com.example.administrator.izhihucollection.MVP.model.service;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Administrator on 2017/8/3 0003.
 */
public interface CollectionService {

    @GET("/collection/{cid}")
    Call<ResponseBody> getCollection(@Path("cid") String cid);
}
