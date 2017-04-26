package com.cdc.rxjavalearning.util;


import com.cdc.rxjavalearning.impl.PhoneService;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class PhoneApi {
    public static final String JUHE_APP_KEY = "4105585905035f3e673ff269d598dde8";
    private static final String BASE_URL = "http://apis.juhe.cn";

    public static PhoneApi getApi() {
        return ApiHolder.phoneApi;
    }

    private static class ApiHolder {
        private static final PhoneApi phoneApi = new PhoneApi();
    }

    private final PhoneService service;

    private PhoneApi() {
        // 在创建Retrofit对象时添加一个RxJava对象的Adapter来使Retrofit中的Service角色可以返回Observable类型的对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(PhoneService.class);
    }

    public PhoneService getService() {
        return service;
    }
}