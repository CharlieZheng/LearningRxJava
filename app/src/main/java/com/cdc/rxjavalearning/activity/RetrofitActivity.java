package com.cdc.rxjavalearning.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.cdc.rxjavalearning.R;
import com.cdc.rxjavalearning.entity.PhoneResult;
import com.cdc.rxjavalearning.impl.PhoneService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Charlie on 2016/7/26.
 *
 * @see {http://www.devwiki.net/2016/03/02/Retrofit-Use-Course-1/}
 */
public class RetrofitActivity extends AppCompatActivity {
    private static final String BASE_URL = "http://apis.baidu.com";
    private static final String API_KEY = "8e13586b86e4b7f3758ba3bd6c9c9135";
    private EditText phoneView;
    private TextView tv;
    private Button query;
    private static final String tag = RetrofitActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        tv = (TextView) findViewById(R.id.tv);
        phoneView = (EditText) findViewById(R.id.et);
        query = (Button) findViewById(R.id.query);
        query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                query(phoneView);
            }
        });
    }

    private void query(EditText phoneView) {
        Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL).build();

        PhoneService service = retrofit.create(PhoneService.class);
        Call<PhoneResult> call = service.getResult(API_KEY, phoneView.getText().toString());

        call.enqueue(new Callback<PhoneResult>() {
            @Override
            public void onResponse(Call<PhoneResult> call, Response<PhoneResult> response) {
                if (response.isSuccessful()) {
                    PhoneResult result = response.body();
                    if (result != null) {
                        PhoneResult.RetDataEntity entity = result.getRetData();
                        tv.setText("地址：" + entity.getCity());
                    }
                }
            }

            @Override
            public void onFailure(Call<PhoneResult> call, Throwable t) {

            }
        });
    }
}
