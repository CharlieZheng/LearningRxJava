package com.cdc.rxjavalearning.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cdc.rxjavalearning.R;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;


public class MainActivity extends AppCompatActivity {
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
                String[] names = new String[]{"广州大学", "数学与信息科学", "郑汉荣"};
                Observable.from(names).map(new Func1<String, User>() {
                    @Override
                    public User call(String s) {

                        return new User(s);
                    }
                }).flatMap(new Func1<User, Observable<Stu>>() {
                    @Override
                    public Observable<Stu> call(User user) {
                        return Observable.from(new Stu[]{new Stu(user.name)});
                    }
                    //                            @Override
                    //                            public Observable<String> call(String s) {
                    //                                return Observable.from(new  String[]{s+"Hello"});
                    //                            }
                }).subscribe(new Action1<Stu>() {
                    @Override
                    public void call(Stu stu) {
                        tv.append(stu.name + "\n");
                    }
                });
            }
        });
        tv = (TextView) findViewById(R.id.tv);
    }

    private class User {
        private final String name;
        User(String name) {
            this.name = name;
        }

    }

    private class Stu {
        private final String name;
        Stu(String name) {
            this.name = name;
        }

    }
}
