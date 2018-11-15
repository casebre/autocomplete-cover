package com.cover.technicalassessment.webservice;

import com.cover.technicalassessment.entities.CustomResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Webservice {
    @GET("json")
    Call<CustomResponse> getAddress(@Query("input") String addressKeyword,
                                    @Query("types") String type,
                                    @Query("key") String key);
}
