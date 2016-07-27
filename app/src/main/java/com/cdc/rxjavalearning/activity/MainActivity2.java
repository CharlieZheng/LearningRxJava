package com.cdc.rxjavalearning.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.cdc.rxjavalearning.R;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Charlie on 2016/7/27.
 */
public class MainActivity2 extends AppCompatActivity {

    private Button bt;
    private ImageView iv;
    int drawableRes = R.mipmap.ic_launcher;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        bt = (Button) findViewById(R.id.bt);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Observable.create(new Observable.OnSubscribe<Drawable>() {
                    @Override
                    public void call(Subscriber<? super Drawable> subscriber) {
                        Drawable drawable = getTheme().getDrawable(drawableRes);
                        subscriber.onNext(drawable);
                        subscriber.onCompleted();
                    }
                }).subscribeOn(Schedulers.io()) // 指定 subscribe() 发生在 IO 线程
                        .observeOn(AndroidSchedulers.mainThread()) // 指定 Subscriber 的回调发生在主线程
                        .subscribe(new Observer<Drawable>() {
                            @Override
                            public void onNext(Drawable drawable) {
                                iv.setImageDrawable(drawable);
                            }

                            @Override
                            public void onCompleted() {
                            }

                            @Override
                            public void onError(Throwable e) {
                                Toast.makeText(MainActivity2.this, "Error!", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
        iv = (ImageView) findViewById(R.id.iv);
    }


}
