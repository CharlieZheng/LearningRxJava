package com.cdc.rxjavalearning.impl;


import com.cdc.rxjavalearning.entity.CommitParam;
import com.cdc.rxjavalearning.entity.PhoneResult;
import com.cdc.rxjavalearning.entity.Responce;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

public interface PhoneService {
    @GET("/apistore/mobilenumber/mobilenumber")
    Call<PhoneResult> getResult(@Header("apikey") String apikey, @Query("phone") String phone);

    @FormUrlEncoded
    @POST("/posts/create.json")
    Call<CommitParam> createCommit(@Field("secret") String secret, @Field("short_name") String shortName, @Field("author_email") String authorEmail, @Field("author_name") String authorName, @Field("thread_key") String threadKey, @Field("author_url") String author_url, @Field("message") String message);

    @GET("/apistore/mobilenumber/mobilenumber")
    Observable<PhoneResult> getPhoneResult(@Header("apikey") String apikey, @Query("phone") String phone);

    @GET("/mobile/get")
    Observable<Responce> getPhoneResult(@Query("phone") int phone, @Query("key") String key, @Query("dtype") String dtype);

}