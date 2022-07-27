package com.example.maptest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RetrofitMap {

    @GET("v1/driving")
    Call<ResultPath> navigation(@Header("X-NCP-APIGW-API-KEY-ID")String apikeyID,
                                @Header("X-NCP-APIGW-API-KEY") String apikey,
                                @Query("start") String start,
                                @Query("goal") String goal);

    @Headers({"Content-Type: application/json"})
    @POST("/coury/getCouryToAddress")
    Call<Response_CouryToAddress> getCouryToAddress(@Body Request_CouryToAddress data);

}

