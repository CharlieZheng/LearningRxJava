package com.cdc.rxjavalearning.impl;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Charlie on 2016/7/28.
 */
public interface GetBaidu {
    @GET("http://www.baidu.com/")
    Call<ResponseBody> get();
    //Call<T> get();//必须是这种形式,这是2.0之后的新形式
    //我这里需要返回网页内容,不需要转换成Json数据,所以用了ResponseBody;
    //你也可以使用Call<GsonBean> get();这样的话,需要添加Gson转换器...后续介绍
}