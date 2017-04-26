package com.cdc.rxjavalearning.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cdc.rxjavalearning.R;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Charlie on 2016/7/26.
 * 总结RxJava转换类型的操作符
 * http://www.jianshu.com/p/64aa976a46be?utm_campaign=haruki&utm_content=note&utm_medium=reader_share&utm_source=qq
 */
public class Rx_Map_Activity extends AppCompatActivity {
    private Button bt;
    private TextView tv;
    private static final String tag = Rx_Map_Activity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt = (Button) findViewById(R.id.bt);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flatMap();
                // 一、map操作符
                //                Observable.just(1, 2, 3, 4, 5).map(new Func1<Integer, String>() {
                //                    @Override
                //                    public String call(Integer integer) {
                //                        return "the position is" + integer;
                //                    }
                //                }).subscribe(new Observer<String>() {
                //                    @Override
                //                    public void onCompleted() {
                //
                //                    }
                //
                //                    @Override
                //                    public void onError(Throwable e) {
                //
                //                    }
                //
                //                    @Override
                //                    public void onNext(String s) {
                //                        Log.i(tag, s);
                //                    }
                //                });
                // 二、scan操作符
//                Observable.from(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8}).scan(new Func2<Integer, Integer, Integer>() {
//                    @Override
//                    public Integer call(Integer integer, Integer integer2) {
//                        return integer * integer2;
//                    }
//                }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Integer>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onNext(Integer integer) {
//                        Log.i(tag, "" + integer);
//                    }
//                });

                // 三、groupBy操作符
                //            调用例子如下：
                //                Observable.interval(1, TimeUnit.SECONDS).take(10).groupBy(new Func1<Long, Long>() {
                //                                                                              @Override
                //                                                                              public Long call(Long value) {
                //                                                                                  //按照key为0,1,2分为3组
                //                                                                                  return value % 3;
                //                                                                              }
                //                                                                          }
                //
                //                ).
                //
                //                        subscribe(new Action1<GroupedObservable<Long, Long>>() {
                //                                      @Override
                //                                      public void call(final GroupedObservable<Long, Long> result) {
                //                                          result.subscribe(new Action1<Long>() {
                //                                              @Override
                //                                              public void call(Long value) {
                //                                                  System.out.println("key:" + result.getKey() + ", value:" + value);
                //                                              }
                //                                          });
                //                                      }
                //                                  }
                //
                //                        );
                //
                //                // 四、buffer操作符
                //                List<String> list = new ArrayList<>();
                //                for (int i = 0; i < 30; i++) {
                //                    list.add("hello i:" + i);
                //                }
                //                Observable.from(list).buffer(4).subscribe(new Subscriber<List<String>>() {
                //                                                              @Override
                //                                                              public void onCompleted() {
                //
                //                                                              }
                //
                //                                                              @Override
                //                                                              public void onError(Throwable e) {
                //
                //                                                              }
                //
                //                                                              @Override
                //                                                              public void onNext(List<String> strings) {
                //                                                                  for (String s : strings) {
                //                                                                      Log.i(tag, s);
                //                                                                  }
                //                                                                  Log.i(tag, "\n next group");
                //                                                              }
                //                                                          }
                //
                //                );


                // 五、window操作符
                //                List<String> list = new ArrayList<>();
                //                for (int i = 0; i < 30; i++) {
                //                    list.add("hello i:" + i);
                //                }
                //                Observable.from(list).window(4).subscribe(new Subscriber<Observable<String>>() {
                //                    @Override
                //                    public void onCompleted() {
                //
                //                    }
                //
                //                    @Override
                //                    public void onError(Throwable e) {
                //
                //                    }
                //
                //                    @Override
                //                    public void onNext(Observable<String> stringObservable) {
                //                        stringObservable.subscribe(new Subscriber<String>() {
                //                            @Override
                //                            public void onCompleted() {
                //
                //                            }
                //
                //                            @Override
                //                            public void onError(Throwable e) {
                //
                //                            }
                //
                //                            @Override
                //                            public void onNext(String s) {
                //                                Log.i(tag, s);
                //                            }
                //                        });
                //                        Log.i(tag, "\n next group");
                //                    }
                //                });


            }
        });


        tv = (TextView) findViewById(R.id.tv);
    }

    // flatMap操作符开始
    private void flatMap() {
        Observable.just("南昌", "深圳", "天津", "北京").flatMap(new Func1<String, Observable<String>>() {
            @Override
            public Observable<String> call(String s) {
//                return getWeather(s);
                return Observable.from(getWeatherInfo(s).getWeather());
            }
        }).observeOn(AndroidSchedulers.mainThread()).// 更新ui一定要加这句
                subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String weatherInfo) {
                // 更新ui
                tv.append(weatherInfo + "\n");
            }
        });
    }

    //新建天气信息类
    private class WeatherInfo {
        private final String city;
        private final List<String> weather;

        public WeatherInfo(String city) {
            this.city = city;
            this.weather = new ArrayList<>();
            this.weather.add("8:00 19摄氏度");
            this.weather.add("12:00 27摄氏度");
            this.weather.add("14:00 33摄氏度");
            this.weather.add("17:00 25摄氏度");
            this.weather.add("22:00 17摄氏度");
        }

        public List<String> getWeather() {
            return weather;
        }

        @Override
        public String toString() {
            return "WeatherInfo{" +
                    "city='" + city + '\'' +
                    ", weather=" + weather +
                    '}';
        }
    }

    private Observable<WeatherInfo> getWeather(final String city) {
       return Observable.create(new Observable.OnSubscribe<WeatherInfo>() {
            @Override
            public void call(Subscriber<? super WeatherInfo> subscriber) {
                subscriber.onNext(getWeatherInfo(city));
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io());// 网络请求一定要加这句
    }

    private WeatherInfo getWeatherInfo(String city) {
        //模拟网络请求返回weatherinfo
        return new WeatherInfo(city);
    }

    //南昌－>能够获取南昌天气的observable->更新ui
    // flatMap操作符结束
    // cast操作符开始
    public void cast() {
        List<Person> list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list.add(new Male(true, "i" + i));
        }
        Observable.from(list).cast(Male.class).subscribe(new Subscriber<Male>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Male s) {
                Log.i(tag, "name:" + s.name + "\n hasJJ:" + s.hasJJ);
            }
        });
    }

    class Person {
        final String name;

        Person(String name) {
            this.name = name;
        }
    }

    private class Male extends Person {
        private final boolean hasJJ;

        Male(boolean hasJJ, String name) {
            super(name);
            this.hasJJ = hasJJ;
        }
    }
    //多态，我们用Person接收Male对象，然后通过cast转成Male类型。有点instanceof的意思。
    // cast操作符结束
}
