package com.example.olskr.cplhm2;

import android.app.Application;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

public class MyApplication extends Application {

    private RxBus bus;

    @Override
    public void onCreate() {
        super.onCreate();
        bus = new RxBus();
    }

    public RxBus bus() {
        return bus;
    }

    public void sendAutoEvent() {
        Observable.timer(5, TimeUnit.SECONDS)
                .subscribe(aLong -> bus.send(new Events.AutoEvent()));
    }

}
