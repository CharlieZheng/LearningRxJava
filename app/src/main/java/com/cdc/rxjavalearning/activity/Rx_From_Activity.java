package com.cdc.rxjavalearning.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cdc.rxjavalearning.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;

/**
 * Created by Charlie on 2016/7/26.
 */
public class Rx_From_Activity extends AppCompatActivity {
    private Button bt;
    private TextView tv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt = (Button) findViewById(R.id.bt);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 一、创建Observable和Subscriber对象
                //通过create创建observable对象，在call中调用subscriber的onnext方法
                Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        for (int i = 0; i < 20; i++) {
                            subscriber.onNext("fuck i is " + i);
                        }
                        subscriber.onCompleted();
                    }
                });
                //上面的代码我们已经构建了一个观察者，我们接下来新建一个订阅者
                Subscriber<String> subscriber = new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        Log.i("rxjava", "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("rxjava", "error");
                    }

                    @Override
                    public void onNext(String o) {
                        Log.i("rxjava", o);
                    }
                };
                // 二、from操作符
                //通过调用subscribe方法使观察者和订阅者产生关联，一旦订阅就观察者就开始发送消息
                //                observable.subscribe(subscriber);
                //在下面的例子代码中，我们从一个已有的列表中创建一个Observable序列：
                List<String> items = new ArrayList<String>();
                items.add("1");
                items.add("10");
                items.add("100");
                items.add("200");
                Observable<String> observableString = Observable.from(items);
                //有了observable,再调用1中的subscribe方法即可开始打印
                observableString.subscribe(subscriber);
                // 三、just操作符
                //通过调用just方法，传入你想发送的数据源，当订阅者进行订阅的时候就开始打印数据
                observableString = Observable.just("i", "love", "you", "very", "much");
                observableString.subscribe(subscriber);
                //just()方法可以传入一到九个参数，它们会按照传入的参数的顺序
                // 四、repeat操作符
                //假如你想对一个Observable重复发射三次数据。例如，我们用just()例子中的Observable：
                observableString = Observable.just("i", "love", "you", "very", "much").repeat(3);
                observableString.subscribe(subscriber);//通过添加repeat(3)，just里面的内容会被打印3次
                // 五、range操作符
                // 从一个指定的数字x开发发射n个数字
                Observable.range(10, 3).subscribe(new Observer<Integer>() {

                    @Override
                    public void onCompleted() {
                        Toast.makeText(Rx_From_Activity.this, "Yeaaah!", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(Rx_From_Activity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(Integer number) {
                        Toast.makeText(Rx_From_Activity.this, "I say " + number, Toast.LENGTH_SHORT).show();
                    }
                });
                //range()函数用两个数字作为参数：第一个是起始点，第二个是我们想发射数字的个数。
                // 六、interval操作符
                //interval()函数在你需要创建一个轮询程序时非常好用。
                Observable.interval(3, TimeUnit.SECONDS).subscribe(new Observer<Long>() {

                    @Override
                    public void onCompleted() {
                        Toast.makeText(Rx_From_Activity.this, "Yeaaah!", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(Rx_From_Activity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(Long number) {
                        Toast.makeText(Rx_From_Activity.this, "I say " + number, Toast.LENGTH_SHORT).show();
                    }
                });
                //interval()函数的两个参数：一个指定两次发射的时间间隔，另一个是用到的时间单位。这个只会执行一次，添加subscribeOn(Schedulers.newThread())好像能够达到轮训的效果
                // 七、timer操作符
                //如果你需要一个一段时间之后才发射的Observable，你可以像下面的例子使用timer()：
                Observable.timer(3, TimeUnit.SECONDS).subscribe(new Observer<Long>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Long number) {
                        Log.d("RXJAVA", "I say " + number);
                    }
                });
                //它将3秒后发射0,然后就完成了。
            }
        });
        tv = (TextView) findViewById(R.id.tv);
    }

    //    private Context getActivity() {
    //        return Rx_From_Activity.this;
    //    }
}
