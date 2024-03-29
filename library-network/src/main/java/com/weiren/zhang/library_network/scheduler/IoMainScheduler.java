package com.weiren.zhang.library_network.scheduler;


import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class IoMainScheduler<T> extends BaseScheduler<T> {

    public IoMainScheduler() {
        super(Schedulers.io(), AndroidSchedulers.mainThread());
    }
}
