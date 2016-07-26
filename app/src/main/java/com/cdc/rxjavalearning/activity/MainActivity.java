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

/**
 * Created by Charlie on 2016/7/26.
 */
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
                Observable.from(names).subscribe(new Action1<String>() {
                    @Override
                    public void call(String name) {
                        tv.append(name + "\n");
                    }
                });
            }
        });
        tv = (TextView) findViewById(R.id.tv);
    }
}
