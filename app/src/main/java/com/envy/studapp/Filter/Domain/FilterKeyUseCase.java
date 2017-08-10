package com.envy.studapp.Filter.Domain;


import com.envy.studapp.BaseUseCase;
import com.envy.studapp.DataBase.ScheduleSQLBrite;
import com.envy.studapp.Filter.Data.FilterKey;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Scheduler;
import rx.functions.Func3;


public class FilterKeyUseCase extends BaseUseCase<FilterKey, Object>{

    ScheduleSQLBrite scheduleSQLBrite;

    @Inject
    public FilterKeyUseCase(Scheduler backgroundScheduler,
                                     Scheduler uiScheduler, ScheduleSQLBrite scheduleSQLBrite) {
        super(backgroundScheduler, uiScheduler);
        this.scheduleSQLBrite = scheduleSQLBrite;
    }

    @Override
    public Observable<FilterKey> buildObservable(Object type) {
        return Observable.zip(
                scheduleSQLBrite.getFilterKeyGroupNumListObservable(),
                scheduleSQLBrite.getFilterKeyTeacherListObservable(),
                scheduleSQLBrite.getFilterKeyWeekdayListObservable(),
                (groupNumList, teacherList, weekdayList) -> {
                    FilterKey filterKey = new FilterKey();
                    filterKey.setGroupNumKeyList(groupNumList);
                    filterKey.setTeacherKeyList(teacherList);
                    filterKey.setWeekdayKeyList(weekdayList);
                    return filterKey;
                }
        );
    }
}
