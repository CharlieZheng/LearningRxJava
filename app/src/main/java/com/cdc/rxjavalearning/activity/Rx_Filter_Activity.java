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
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Charlie on 2016/7/26.
 * 总结RxJava过滤类型的操作符
 * http://www.jianshu.com/p/64aa976a46be?utm_campaign=haruki&utm_content=note&utm_medium=reader_share&utm_source=qq
 */
public class Rx_Filter_Activity extends AppCompatActivity {
    private Button bt;
    private TextView tv;
    private static final String tag = Rx_Filter_Activity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt = (Button) findViewById(R.id.bt);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 一、filter操作符
                //RxJava让我们使用filter()方法来过滤我们观测序列中不想要的值
//                Observable.just("H1", "h2", "h3", "h4", "h5").filter(new Func1<String, Boolean>() {
//                    @Override
//                    public Boolean call(String s) {
//                        return s.startsWith("H");
//                    }
//                }).subscribe(new Subscriber<String>() {
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
//                //通过设置filter，然后在call里面添加s.startsWith("H")，如果是H开头就返回true，否则返回false。从而能够过滤掉不是h开头的消息，打印出以H开头的消息。
//                // 二、take操作符
//                //take()函数用整数N来作为一个参数，从原始的序列中发射前N个元素，然后完成：
//                Observable.just("H1", "h2", "h3", "h4", "h5").take(3).subscribe(new Subscriber<String>() {
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
//                //通过设置take(3),就可以取出前3个消息，打印出H1,h2,h3
//                //                注：如果想从后面取数据，可以调用takeLast(3)取最后3条消息
//                // 三、distinct操作符
//                //我们可以对我们的序列使用distinct()函数去掉重复的。就像takeLast()一样，distinct()作用于一个完整的序列，然后得到重复的过滤项，它需要记录每一个发射的值。
//                Observable o = Observable.just("H1", "h2", "h3", "h4", "h5").repeat(3);//输入重复3遍
//                o.distinct().subscribe(new Subscriber<String>() {
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
//
//                // 四、skip和skipLast操作符
//                Observable.just("hello", "my", "world").skip(1).skipLast(1).subscribe(new Subscriber<String>() {
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
//                //跳过第一个hello和最后一个world，打印my
//                // 五、elementAt和elementAtOrDefault操作符
//                Observable.just("hello", "my", "world").elementAt(1).subscribe();
//                Observable.just("hello", "my", "world").elementAtOrDefault(10, "null").subscribe();

                // 六、sample操作符
                List<String> list = new ArrayList<>();
                for (int i = 0; i < 1000; i++) {
                    list.add("i = " + i);
                }
                Observable<String> observable = Observable.from(list);
                observable.sample(50, TimeUnit.MILLISECONDS).subscribe(new Subscriber<String>() {
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
                //50毫秒取一次最近的消息进行打印
            }
        });
        tv = (TextView) findViewById(R.id.tv);
    }

    //    private Context getActivity() {
    //        return Rx_From_Activity.this;
    //    }
}
