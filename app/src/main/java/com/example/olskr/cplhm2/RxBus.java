package com.example.olskr.cplhm2;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class RxBus {

    private PublishSubject<Object> bus = PublishSubject.create();

    public RxBus() {
    }

    public void send(Object o) {
        bus.onNext(o);
    }

    public Observable<Object> toObservable() {
        return bus;
    }

//    public boolean hasObservers() {
//        return bus.hasObservers();
//    }
}
