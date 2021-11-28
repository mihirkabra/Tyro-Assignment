package com.test.tyroassignment.API;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("all/")
    Call<Response> getData(@Query("category_id") int category_id);

}
