package com.tom.memesretrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitMemes {

    @GET("photos")
    Call<List<Memes>> getMemes();
}
