package com.fadh.biodata.service;

import com.fadh.biodata.search.SearchMDB;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {
  @GET(".")
  Call<SearchMDB> getDetails(@Query("s") String tittle , @Query("apikey") String apiKey);
}
