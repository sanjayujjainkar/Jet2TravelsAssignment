package com.pof.jettravel.data.model;

import com.pof.jettravel.data.model.model.Article;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface RetrofitService {

    String BaseURL = "https://5e99a9b1bc561b0016af3540.mockapi.io/";

    @GET("jet2/api/v1/blogs")
    @Headers("Cache-control: no-cache")
    Call<List<Article>> getArticles(@Query("page") String page, @Query("limit") String limit);
}
