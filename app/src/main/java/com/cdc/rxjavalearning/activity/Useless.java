package com.cdc.rxjavalearning.activity;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;


class Useless {
    Observer<String> observer = new Observer<String>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(String s) {

        }
    };
    Subscriber<String> subscriber = new Subscriber<String>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(String s) {

        }
    };

    private static final String[] words = new String[]{"Hello", "Hi", "Charlie"};
    /**
     * 被观察者
     */
    Observable observable = Observable.create(new Observable.OnSubscribe<String>() {
        @Override
        public void call(Subscriber<? super String> subscriber) {
            subscriber.onNext("Hello");
            subscriber.onNext("Hi");
            subscriber.onNext("Charlie");
            subscriber.onCompleted();
        }
    });

    // 将会依次调用：
    // onNext("Hello");
    // onNext("Hi");
    // onNext("Charlie");
    // onCompleted();
    Observable observable2 = Observable.just("Hello", "Hi", "Charlie");
    // 将会依次调用：
    // onNext("Hello");
    // onNext("Hi");
    // onNext("Charlie");
    // onCompleted();
    Observable observable3 = Observable.from(words);

}
