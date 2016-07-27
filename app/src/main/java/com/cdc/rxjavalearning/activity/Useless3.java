package com.cdc.rxjavalearning.activity;

import rx.Observable;

/**
 * Created by Charlie on 2016/7/27.
 */
public class Useless3 {
    private static final String[] words = new String[]{"Hello", "Hi", "Charlie"};
    private void useless () {
        Observable.from(words).subscribe();
    }
}
