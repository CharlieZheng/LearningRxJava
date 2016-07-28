package com.cdc.rxjavalearning.util;

/**
 * Created by Charlie on 2016/7/28.
 */

import com.cdc.rxjavalearning.impl.PhoneService;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class PhoneApi {

    public static final String BASE_URL = "http://apis.baidu.com";
    public static final String API_KEY = "8e13586b86e4b7f3758ba3bd6c9c9135";

    public static PhoneApi getApi() {
        return ApiHolder.phoneApi;
    }

    static class ApiHolder {
        private static PhoneApi phoneApi = new PhoneApi();
    }

    private PhoneService service;

    private PhoneApi() {
        // 在创建Retrofit对象时添加一个RxJava对象的Adapter来使Retrofit中的Service角色可以返回Observable类型的对象
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).addConverterFactory(GsonConverterFactory.create()).build();
        service = retrofit.create(PhoneService.class);
    }

    public PhoneService getService() {
        return service;
    }
}