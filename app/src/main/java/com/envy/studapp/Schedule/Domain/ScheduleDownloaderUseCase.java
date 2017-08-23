package com.envy.studapp.Schedule.Domain;


import android.util.Log;

import com.envy.studapp.BaseUseCase;
import com.envy.studapp.DataBase.ScheduleContract;
import com.envy.studapp.DataBase.ScheduleSQLBrite;
import com.envy.studapp.Schedule.Data.HttpAPIInterface.StudServiceAPI;
import com.envy.studapp.Schedule.ScheduleCalendarManager;


import javax.inject.Inject;

import rx.Observable;
import rx.Scheduler;


public class ScheduleDownloaderUseCase extends BaseUseCase<ScheduleResponse,
                                               Object>{

    StudServiceAPI studServiceAPI;

    ScheduleSQLBrite scheduleSQLBrite;

    ScheduleCalendarManager scheduleCalendarManager;

    @Inject
    public ScheduleDownloaderUseCase(StudServiceAPI studServiceAPI, Scheduler backgroundScheduler,
                                     Scheduler uiScheduler, ScheduleSQLBrite scheduleSQLBrite,
                                     ScheduleCalendarManager scheduleCalendarManager) {

        super(backgroundScheduler, uiScheduler);
        this.studServiceAPI = studServiceAPI;
        this.scheduleSQLBrite = scheduleSQLBrite;
        this.scheduleCalendarManager = scheduleCalendarManager;
    }


    @Override
    public Observable<ScheduleResponse> buildObservable(Object object) {
        return studServiceAPI.getSchedule().doOnNext(scheduleResponse ->
                scheduleSQLBrite.updateScheduleDB(scheduleResponse))
                .flatMap(scheduleResponse -> {
                    if (scheduleCalendarManager.isNumerator()){
                        return scheduleSQLBrite.getFromDatabaseObservable(
                                ScheduleContract.SubjectEntry.SQL_SELECT_NUMERATOR_SUBJECTS);
                    }
                    else return scheduleSQLBrite.getFromDatabaseObservable(
                            ScheduleContract.SubjectEntry.SQL_SELECT_NOT_NUMENATOR_SUBJECTSS);
                });
    }

}
