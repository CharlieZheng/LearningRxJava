package com.cdc.rxjavalearning.impl;

/**
 * Created by Charlie on 2016/7/28.
 *
 * @see {http://www.devwiki.net/2016/03/02/Retrofit-Use-Course-1/}
 */

import com.cdc.rxjavalearning.entity.PhoneResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface PhoneService {
    @GET("/apistore/mobilenumber/mobilenumber")
    Call<PhoneResult> getResult(@Header("apikey") String apikey, @Query("phone") String phone);
}