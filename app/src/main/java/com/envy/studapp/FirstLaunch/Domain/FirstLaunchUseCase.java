package com.envy.studapp.FirstLaunch.Domain;

import com.envy.studapp.BaseUseCase;
import com.envy.studapp.DataBase.ScheduleSQLBrite;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Scheduler;


public class FirstLaunchUseCase extends BaseUseCase<List<String>, Object>{

    private ScheduleSQLBrite scheduleSQLBrite;

    @Inject
    public FirstLaunchUseCase(Scheduler backgroundScheduler, Scheduler uiScheduler, ScheduleSQLBrite
                              scheduleSQLBrite){
        super(backgroundScheduler, uiScheduler);
        this.scheduleSQLBrite = scheduleSQLBrite;
    }


    @Override
    public Observable<List<String>> buildObservable(Object type) {
        return scheduleSQLBrite.getFilterKeyGroupNumListObservable();
    }
}
