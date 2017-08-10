package com.envy.studapp.Schedule.Domain;

import com.envy.studapp.BaseUseCase;
import com.envy.studapp.DataBase.ScheduleSQLBrite;

import rx.Observable;
import rx.Scheduler;

/**
 * Created by ENVY on 09.08.2017.
 */

public class ScheduleFromDbUseCase extends BaseUseCase<ScheduleResponse, Object>{

    ScheduleSQLBrite scheduleSQLBrite;

    public ScheduleFromDbUseCase(Scheduler backgroundScheduler,
                                     Scheduler uiScheduler, ScheduleSQLBrite scheduleSQLBrite){
        super(backgroundScheduler, uiScheduler);
        this.scheduleSQLBrite = scheduleSQLBrite;
    }


    @Override
    public Observable<ScheduleResponse> buildObservable(Object type) {
        if (scheduleSQLBrite.checkAvailabilityRecords()){
            return scheduleSQLBrite.getFromDatabaseObservable();
        }
        else return Observable.just(null);
    }
}
