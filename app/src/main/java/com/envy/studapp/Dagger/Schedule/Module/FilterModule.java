package com.envy.studapp.Dagger.Schedule.Module;

import android.support.v7.widget.LinearLayoutCompat;

import com.envy.studapp.DataBase.ScheduleSQLBrite;
import com.envy.studapp.Filter.Domain.FilterKeyUseCase;
import com.envy.studapp.Filter.Presentation.FilterPresenter;
import com.envy.studapp.Schedule.Domain.ScheduleFromDbUseCase;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;

/**
 * Created by ENVY on 09.08.2017.
 */

@Module
public class FilterModule {

    @Provides
    @Singleton
    public FilterKeyUseCase provideFilterUseCase(@Named("backgroundScheduler") Scheduler backgroundScheduler,
                                                 @Named("mainScheduler")Scheduler mainScheduler,
                                                 ScheduleSQLBrite scheduleSQLBrite){
        return new FilterKeyUseCase(backgroundScheduler, mainScheduler, scheduleSQLBrite);
    }

    @Provides
    @Singleton
    public FilterPresenter provideFilterPresenter(FilterKeyUseCase filterKeyUseCase,
                                                  ScheduleFromDbUseCase scheduleFromDbUseCase) {
        return new FilterPresenter(filterKeyUseCase, scheduleFromDbUseCase);
    }
}
