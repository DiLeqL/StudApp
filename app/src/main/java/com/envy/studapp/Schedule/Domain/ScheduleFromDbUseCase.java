package com.envy.studapp.Schedule.Domain;

import com.envy.studapp.BaseUseCase;
import com.envy.studapp.DataBase.ScheduleContract;
import com.envy.studapp.DataBase.ScheduleSQLBrite;
import com.envy.studapp.Schedule.ScheduleCalendarManager;

import rx.Observable;
import rx.Scheduler;

/**
 * Created by ENVY on 09.08.2017.
 */

public class ScheduleFromDbUseCase extends BaseUseCase<ScheduleResponse, Object>{

    ScheduleSQLBrite scheduleSQLBrite;

    ScheduleCalendarManager scheduleCalendarManager;

    public ScheduleFromDbUseCase(Scheduler backgroundScheduler,
                                     Scheduler uiScheduler, ScheduleSQLBrite scheduleSQLBrite,
                                 ScheduleCalendarManager scheduleCalendarManager){
        super(backgroundScheduler, uiScheduler);
        this.scheduleSQLBrite = scheduleSQLBrite;
        this.scheduleCalendarManager = scheduleCalendarManager;
    }


    @Override
    public Observable<ScheduleResponse> buildObservable(Object type) {
        if (scheduleSQLBrite.checkAvailabilityRecords()){
            if (scheduleCalendarManager.isNumerator()){
                return scheduleSQLBrite.getFromDatabaseObservable(
                        ScheduleContract.SubjectEntry.SQL_SELECT_NUMERATOR_SUBJECTS);
            }
            else return scheduleSQLBrite.getFromDatabaseObservable(
                    ScheduleContract.SubjectEntry.SQL_SELECT_NOT_NUMENATOR_SUBJECTSS);

        }
        else return Observable.just(null);
    }
}
