package com.envy.studapp.Dagger.Schedule.Module;

import com.envy.studapp.Schedule.Data.HttpAPIInterface.StudServiceAPI;
import com.envy.studapp.Schedule.Domain.ScheduleDownloaderUseCase;
import com.envy.studapp.Schedule.Presentation.SchedulePresenter;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;

/**
 * Created by ENVY on 19.06.2017.
 */

@Module
public class ScheduleModule {

    @Provides
    @Singleton
    public ScheduleDownloaderUseCase provideScheduleDownloaderUseCase(StudServiceAPI studServiceAPI,
                                                                      @Named("backgroundScheduler")Scheduler backgroundScheduler,
                                                                      @Named("mainScheduler")Scheduler mainScheduler){
        return new ScheduleDownloaderUseCase(studServiceAPI, backgroundScheduler, mainScheduler);
    }

    @Provides
    @Singleton
    public SchedulePresenter provideSchedulePresenter(ScheduleDownloaderUseCase scheduleDownloaderUseCase){
        return new SchedulePresenter(scheduleDownloaderUseCase);
    }
}
