package com.envy.studapp;

import javax.inject.Named;


import rx.Observable;
import rx.Observer;
import rx.Scheduler;


public abstract class BaseUseCase<OT, IT> {


    @Named("backgroundScheduler")
    Scheduler backgroundScheduler;

    @Named("mainScheduler")
    Scheduler uiScheduler;

    public BaseUseCase(Scheduler backgroundScheduler, Scheduler uiScheduler){

        this.backgroundScheduler = backgroundScheduler;
        this.uiScheduler = uiScheduler;
    }

    public abstract Observable<OT> buildObservable(IT type);


    public void subscribe(IT type, Observer<OT> subscriber){
        buildObservable(type)
                .subscribeOn(backgroundScheduler)
                .observeOn(uiScheduler)
                .subscribe(subscriber);
    }

}
