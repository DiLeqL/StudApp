package com.envy.studapp;

import com.envy.studapp.Schedule.Data.TeacherModel;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;



/**
 * Created by ENVY on 13.06.2017.
 */

public abstract class BaseUseCase<OT, IT> {

    Scheduler backgroundScheduler;
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
    };

}
