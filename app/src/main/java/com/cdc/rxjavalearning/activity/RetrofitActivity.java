package com.cdc.rxjavalearning.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.cdc.rxjavalearning.R;
import com.cdc.rxjavalearning.entity.BaseRequest;
import com.cdc.rxjavalearning.entity.CommitParam;
import com.cdc.rxjavalearning.entity.PhoneResult;
import com.cdc.rxjavalearning.impl.PhoneService;
import com.cdc.rxjavalearning.util.PhoneApi;

import retrofit2.Call;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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
                //                query(phoneView);
                query();
            }
        });
    }

    //    private void query(final EditText phoneView) {
    //        BaseRequest<PhoneService, PhoneResult> request = new BaseRequest<PhoneService, PhoneResult>() {
    //            @Override
    //            protected Call<PhoneResult> request(PhoneService phoneService) {
    //                return phoneService.getResult(API_KEY, phoneView.getText().toString());
    //            }
    //
    //            @Override
    //            protected Class<PhoneService> getServiceClass() {
    //                return PhoneService.class;
    //            }
    //
    //            @Override
    //            protected String getBaseUrl() {
    //                return BASE_URL;
    //            }
    //
    //            @Override
    //            protected void onResponse(PhoneResult result) {
    //                tv.setText(result.getRetData().getCity());
    //            }
    //        };
    //        request.doRequest();
    //    }

    private void query(final EditText phoneView) {
        BaseRequest<PhoneService, CommitParam> request = new BaseRequest<PhoneService, CommitParam>() {
            @Override
            protected Call<CommitParam> request(PhoneService phoneService) {
                return phoneService.createCommit("secret", "shortName", "authorEmail", "authorName", "threadKey", "author_url", "message");
            }

            @Override
            protected Class<PhoneService> getServiceClass() {
                return PhoneService.class;
            }

            @Override
            protected String getBaseUrl() {
                return "http://api.duoshuo.com";
            }

            @Override
            protected void onResponse(CommitParam commitParam) {
                tv.append(commitParam.getMessage() + "\n");
            }

            @Override
            protected void onFailure(Call<CommitParam> call, Throwable t) {
                tv.append(t.getMessage() + "\n");
            }
        };
        request.doRequest();
    }

    private void query() {

        PhoneService phoneService = PhoneApi.getApi().getService();
        phoneService.getPhoneResult(PhoneApi.API_KEY, "13533361755").subscribeOn(Schedulers.newThread())    // 子线程访问网络
                .observeOn(AndroidSchedulers.mainThread())  // 回调到主线程
                .subscribe(new Observer<PhoneResult>() {
                    @Override
                    public void onCompleted() {
                        tv.append("结束" + "\n");
                    }

                    @Override
                    public void onError(Throwable e) {
                        tv.append("异常：" + e.getMessage() + "\n");
                    }

                    @Override
                    public void onNext(PhoneResult result) {
                        if (result != null && result.getErrNum() == 0) {
                            PhoneResult.RetDataEntity entity = result.getRetData();
                            tv.append("地址：" + entity.getCity() + "\n");
                        }
                    }
                });
    }
}
