package com.cdc.rxjavalearning.activity;

import android.util.Log;

import rx.Observable;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 * Created by Charlie on 2016/7/27.
 */
public class Useless2 {
    private static final String TAG = Useless2.class.getSimpleName();
    Observable observable = Observable.just("Hello", "Hi", "Charlie");
    Action1<String> onNextAction = new Action1<String>() {
        // onNext()
        @Override
        public void call(String s) {
            Log.d(TAG, s);
        }
    };
    Action1<Throwable> onErrorAction = new Action1<Throwable>() {
        // onError()
        @Override
        public void call(Throwable throwable) {
            // Error handling
        }
    };
    Action0 onCompletedAction = new Action0() {
        // onCompleted()
        @Override
        public void call() {
            Log.d(TAG, "completed");
        }
    };

    private void useless() {
        // 自动创建 Subscriber ，并使用 onNextAction 来定义 onNext()
        observable.subscribe(onNextAction);
        // 自动创建 Subscriber ，并使用 onNextAction 和 onErrorAction 来定义 onNext() 和 onError()
        observable.subscribe(onNextAction, onErrorAction);
        // 自动创建 Subscriber ，并使用 onNextAction、 onErrorAction 和 onCompletedAction 来定义 onNext()、 onError() 和 onCompleted()
        observable.subscribe(onNextAction, onErrorAction, onCompletedAction);
    }
}
