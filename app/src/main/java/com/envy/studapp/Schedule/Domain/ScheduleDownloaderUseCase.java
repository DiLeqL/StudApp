package com.envy.studapp.Schedule.Domain;


import com.envy.studapp.BaseUseCase;
import com.envy.studapp.Schedule.Data.HttpAPIInterface.StudServiceAPI;

import javax.inject.Inject;

import rx.Observable;
import rx.Scheduler;


public class ScheduleDownloaderUseCase extends BaseUseCase<ScheduleResponse,
                                               Object>{

    StudServiceAPI studServiceAPI;
    //Observer<ScheduleResponse> subscriber;


    @Inject
    public ScheduleDownloaderUseCase(StudServiceAPI studServiceAPI, Scheduler backgroundScheduler,
                                     Scheduler uiScheduler) {

        super(backgroundScheduler, uiScheduler);
        this.studServiceAPI = studServiceAPI;
    }


    @Override
    public Observable<ScheduleResponse> buildObservable(Object object) {
        return studServiceAPI.getSchedule();
    }



}
