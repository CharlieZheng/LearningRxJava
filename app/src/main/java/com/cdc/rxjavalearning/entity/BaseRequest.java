package com.cdc.rxjavalearning.entity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class BaseRequest<Service, Result> {

    public void doRequest() {
        Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(getBaseUrl()).build();

        Service service = retrofit.create(getServiceClass());
        Call<Result> call = request(service);

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                if (response.isSuccessful()) {
                    Result result = response.body();
                    if (result != null) {
                        BaseRequest.this.onResponse(result);
                    }
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                BaseRequest.this.onFailure(call, t);
            }
        });
    }

    protected abstract Call<Result> request(Service service);

    protected abstract Class<Service> getServiceClass();

    protected abstract String getBaseUrl();

    protected abstract void onResponse(Result result);

    protected abstract void onFailure(Call<Result> call, Throwable t);


}
