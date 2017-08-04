package com.example.administrator.izhihucollection.MVP.model.service;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Administrator on 2017/8/4 0004.
 */
public interface CollistService {

    @GET("/people/{sid}/collections")
    Call<ResponseBody> getCollist(@Path("sid") String sid);
}
