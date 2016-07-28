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
import rx.functions.Func2;
import rx.schedulers.Schedulers;

/**
 * Created by Charlie on 2016/7/26.
 * 总结RxJava转换类型的操作符
 * http://www.jianshu.com/p/64aa976a46be?utm_campaign=haruki&utm_content=note&utm_medium=reader_share&utm_source=qq
 */
public class Rx_Merge_Activity extends AppCompatActivity {
    private Button bt;
    private TextView tv;
    private static final String tag = Rx_Merge_Activity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt = (Button) findViewById(R.id.bt);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 一、merge操作符
                List<String> list1 = new ArrayList<>();
                for (int i = 0; i < 30; i++) {
                    list1.add("hello i:" + i);
                }
                List<String> list = new ArrayList<>();
                for (int j = 0; j < 30; j++) {
                    list.add("world j:" + j);
                }
                List<String> list2 = new ArrayList<>();
                for (int m = 0; m < 30; m++) {
                    list2.add("fuck m:" + m);
                }
                Observable<String> world = Observable.from(list);
                Observable<String> hello = Observable.from(list1);
                Observable<String> fuck = Observable.from(list2);
                Observable.merge(world, hello, fuck).subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {
                        Log.i(tag, s);
                    }
                });
                // 二、zip操作符
                List<String> hellos = new ArrayList<>();
                for (int i = 0; i < 30; i++) {
                    hellos.add("hello i:" + i);
                }
                List<Integer> worlds = new ArrayList<>();
                for (int j = 0; j < 20; j++) {
                    worlds.add(j);
                }

                Observable.zip(Observable.from(hellos).subscribeOn(Schedulers.io()), Observable.from(worlds).subscribeOn(Schedulers.io()), new Func2<String, Integer, String>() {
                    @Override
                    public String call(String s, Integer integer) {
                        return "index:" + integer + "\t s:" + s;
                    }
                }).subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        Log.i(tag, "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(tag, "error");
                    }

                    @Override
                    public void onNext(String s) {
                        Log.i(tag, s);
                    }
                });
                //这里只会打印20条，而不是30条，应该是有个observable完成就完成


            }
        });


        tv = (TextView) findViewById(R.id.tv);
    }

}
