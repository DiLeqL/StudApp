package com.envy.studapp.Dagger.Schedule.Module;

import android.net.ConnectivityManager;

import com.envy.studapp.DataBase.ScheduleSQLBrite;
import com.envy.studapp.Filter.Domain.FilterKeyUseCase;
import com.envy.studapp.Schedule.Data.HttpAPIInterface.StudServiceAPI;
import com.envy.studapp.Schedule.Domain.ScheduleDownloaderUseCase;
import com.envy.studapp.Filter.Presentation.FilterPresenter;
import com.envy.studapp.Schedule.Presentation.SchedulePresenter;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;

/**
 * Created by ENVY on 19.06.2017.
 */

@Module
public class ScheduleModule {

    @Provides
    @Singleton
    public ScheduleDownloaderUseCase provideScheduleDownloaderUseCase(StudServiceAPI studServiceAPI,
                                                                      @Named("backgroundScheduler") Scheduler backgroundScheduler,
                                                                      @Named("mainScheduler")Scheduler mainScheduler,
                                                                      ScheduleSQLBrite scheduleSQLBrite,
                                                                      ConnectivityManager cm){
        return new ScheduleDownloaderUseCase(studServiceAPI, backgroundScheduler, mainScheduler,
                scheduleSQLBrite, cm);
    }

    @Provides
    @Singleton
    public SchedulePresenter provideSchedulePresenter(ScheduleDownloaderUseCase scheduleDownloaderUseCase,
                                                      ScheduleSQLBrite scheduleSQLBrite){
        return new SchedulePresenter(scheduleDownloaderUseCase, scheduleSQLBrite);
    }

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
                                                  ScheduleSQLBrite scheduleSQLBrite) {
        return new FilterPresenter(filterKeyUseCase, scheduleSQLBrite);
    }
}
