package com.envy.studapp;

import android.util.Log;

import javax.inject.Named;


import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.Subscription;


public abstract class BaseUseCase<OT, IT> {


    @Named("backgroundScheduler")
    Scheduler backgroundScheduler;

    @Named("mainScheduler")
    Scheduler uiScheduler;

    Subscription subscription;

    public BaseUseCase(Scheduler backgroundScheduler, Scheduler uiScheduler) {

        this.backgroundScheduler = backgroundScheduler;
        this.uiScheduler = uiScheduler;
    }

    public abstract Observable<OT> buildObservable(IT type);


    public void subscribe(IT type, Observer<OT> subscriber) {
        subscription = buildObservable(type)
                .subscribeOn(backgroundScheduler)
                .observeOn(uiScheduler)
                .subscribe(subscriber);
    }

    public Subscription getSubscription(){
        return subscription;
    }

}

