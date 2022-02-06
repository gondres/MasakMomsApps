package com.projectpertama.masakmoms.service;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API {

    @GET("api/recipes")
    Call<GetList> getList();

    @GET("/api/recipes/1")
    Call<GetList> getPage();
}
