package com.example.day03_zuoye;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiServiec {
    String url="https://www.wanandroid.com/";
    @GET("project/list/{page}/json?cid=294")
    Call<InfoBean> getData(@Path("page") int page);
}
