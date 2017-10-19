package com.cdc.rxjavalearning.activity;

import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.cdc.rxjavalearning.R;
import com.cdc.rxjavalearning.entity.AppInfo;
import com.cdc.rxjavalearning.entity.BaseRequest;
import com.cdc.rxjavalearning.entity.CommitParam;
import com.cdc.rxjavalearning.entity.PhoneResult;
import com.cdc.rxjavalearning.entity.Responce;
import com.cdc.rxjavalearning.impl.PhoneService;
import com.cdc.rxjavalearning.util.PhoneApi;

import retrofit2.Call;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;
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
                query2();
            }
        });
    }

    public static void main(String[] args) {
        final Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                System.out.println(Thread.currentThread().getStackTrace()[2].getMethodName());
            }

            @Override
            public void onError(Throwable e) {
                System.out.println(e);
            }

            @Override
            public void onNext(String s) {
                System.out.println(s);
            }
        };
        /*final Observer<String> observer = new Observer<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {

            }
        };*/
        Observable<String> observable;
        Subscription subscription;
        String[] array;
        /*// 1 create
        observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onCompleted(); // 执行到此处不再往下执行了，神奇
                subscriber.onNext("onNext");
                subscriber.onError(new Throwable());
            }
        });
        subscription = observable.subscribe(subscriber);
        if (!subscription.isUnsubscribed()) subscription.unsubscribe();*/
        /*// 2 just
        observable = Observable.just("1", "2", "3", "4");
        subscription = observable.subscribe(subscriber);
        if (!subscription.isUnsubscribed()) subscription.unsubscribe();*/
        /*// 3 from
        array = new String[]{"a", "b", "c", "d"};
        observable = Observable.from(array);
        subscription = observable.subscribe(subscriber);
        if (!subscription.isUnsubscribed()) subscription.unsubscribe();*/
        /*// 4 使用Action接口代替Subscriber
        array = new String[]{"a", "b", "c", "d"};
        observable = Observable.from(array);
        subscription = observable.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(Thread.currentThread().getStackTrace()[2].getMethodName() + " " + s);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                System.out.println(Thread.currentThread().getStackTrace()[2].getMethodName());
            }
        }, new Action0() {
            @Override
            public void call() {
                System.out.println(Thread.currentThread().getStackTrace()[2].getMethodName());
            }
        });
        if (!subscription.isUnsubscribed()) subscription.unsubscribe();*/
        /*// 5 map
        final AppInfo[] appInfoArray = new AppInfo[]{new AppInfo("name1"), new AppInfo("name2"), new AppInfo("name3"), new AppInfo("name4")};
        subscription = Observable.from(appInfoArray)
                .map(new Func1<AppInfo, String>() {
                    @Override
                    public String call(AppInfo appInfo) {
                        return appInfo.getName();
                    }
                })
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        System.out.println("App's name is " + s);
                    }
                });
        if (!subscription.isUnsubscribed()) subscription.unsubscribe();*/
        // 6 map尝试处理一对多关系，在事件回调函数onNext中，形参只能是Observable<T>
        final AppInfo[] appInfoArray = new AppInfo[]{new AppInfo("name1", "abcd"),
                new AppInfo("name2", "efgh"),
                new AppInfo("name3", "ijkl"),
                new AppInfo("name4", "mnop")};
        subscription = Observable.from(appInfoArray)
                .map(new Func1<AppInfo, Observable<String>>() {
                    @Override
                    public Observable<String> call(AppInfo appInfo) {
                        return Observable.from(appInfo.moduleList);
                    }
                })
                .subscribe(new Subscriber<Observable<String>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Observable<String> stringObservable) {
                        System.out.println(stringObservable.toString());
                    }
                });
        if (!subscription.isUnsubscribed()) subscription.unsubscribe();
        // 7 flatMap登场
        /*final AppInfo[] appInfoArray = new AppInfo[]{new AppInfo("name1", "abcd"),
                new AppInfo("name2", "efgh"),
                new AppInfo("name3", "ijkl"),
                new AppInfo("name4", "mnop")};*/
        subscription = Observable.from(appInfoArray)
                .flatMap(new Func1<AppInfo, Observable<String>>() {
                    @Override
                    public Observable<String> call(AppInfo appInfo) {
                        return Observable.from(appInfo.moduleList);
                    }
                })
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        System.out.println(Thread.currentThread().getStackTrace()[2].getMethodName());
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println(Thread.currentThread().getStackTrace()[2].getMethodName());
                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println(Thread.currentThread().getStackTrace()[2].getMethodName() + " " + s);
                    }
                });
        if (!subscription.isUnsubscribed()) subscription.unsubscribe();
    }

//        private void query(final EditText phoneView) {
//            BaseRequest<PhoneService, PhoneResult> request = new BaseRequest<PhoneService, PhoneResult>() {
//                @Override
//                protected Call<PhoneResult> request(PhoneService phoneService) {
//                    return phoneService.getResult(API_KEY, phoneView.getText().toString());
//                }
//
//                @Override
//                protected Class<PhoneService> getServiceClass() {
//                    return PhoneService.class;
//                }
//
//                @Override
//                protected String getBaseUrl() {
//                    return BASE_URL;
//                }
//
//                @Override
//                protected void onResponse(PhoneResult result) {
//                    tv.setText(result.getRetData().getCity());
//                }
//            };
//            request.doRequest();
//        }

//    private void query() {
//        BaseRequest<PhoneService, CommitParam> request = new BaseRequest<PhoneService, CommitParam>() {
//            @Override
//            protected Call<CommitParam> request(PhoneService phoneService) {
//                return phoneService.createCommit("secret", "shortName", "authorEmail", "authorName", "threadKey", "author_url", "message");
//            }
//
//            @Override
//            protected Class<PhoneService> getServiceClass() {
//                return PhoneService.class;
//            }
//
//            @Override
//            protected String getBaseUrl() {
//                return "http://api.duoshuo.com";
//            }
//
//            @Override
//            protected void onResponse(CommitParam commitParam) {
//                tv.append(commitParam.getMessage() + "\n");
//            }
//
//            @Override
//            protected void onFailure(Call<CommitParam> call, Throwable t) {
//                tv.append(t.getMessage() + "\n");
//            }
//        };
//        request.doRequest();
//    }

    private void query() {

        PhoneService phoneService = PhoneApi.getApi().getService();
        phoneService.getPhoneResult(PhoneApi.JUHE_APP_KEY, "13533361755").subscribeOn(Schedulers.newThread())    // 子线程访问网络
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

    private void query2() {
        PhoneService phoneService = PhoneApi.getApi().getService();
//        Call<Responce> call = phoneService.getPhoneResult(1353336, PhoneApi.JUHE_APP_KEY, "json");
//        call.enqueue(new Callback<Responce>() {
//            @Override
//            public void onResponse(Call<Responce> call, Response<Responce> response) {
//                Responce result = response.body();
//                if (result != null) {
//                    System.out.println(result);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Responce> call, Throwable t) {
//                System.out.println(t);
//
//            }
//        });
        phoneService.getPhoneResult(1353336, PhoneApi.JUHE_APP_KEY, "json").subscribeOn(Schedulers.newThread())    // 子线程访问网络
                .observeOn(AndroidSchedulers.mainThread())  // 回调到主线程
                .subscribe(new Observer<Responce>() {
                    @Override
                    public void onCompleted() {
                        tv.append("结束" + "\n");
                    }

                    @Override
                    public void onError(Throwable e) {
                        tv.append("异常：" + e.getMessage() + "\n");
                    }

                    @Override
                    public void onNext(Responce result) {
                        if (result != null) {
                            tv.append(result + "\n");
                        }
                    }
                });

    }
}
