package com.envy.studapp.Dagger.Schedule.Module;

import com.envy.studapp.DataBase.ScheduleSQLBrite;
import com.envy.studapp.Filter.Domain.FilterKeyUseCase;
import com.envy.studapp.Filter.Presentation.FilterPresenter;
import com.envy.studapp.FirstLaunch.Domain.FirstLaunchUseCase;
import com.envy.studapp.FirstLaunch.Presentation.FirstLaunchPresenter;
import com.envy.studapp.Schedule.Domain.ScheduleFromDbUseCase;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;


@Module
public class FirstLaunchModule {

    @Provides
    @Singleton
    public FirstLaunchUseCase provideFirstLaunchUseCase(@Named("backgroundScheduler") Scheduler backgroundScheduler,
                                                            @Named("mainScheduler")Scheduler mainScheduler,
                                                            ScheduleSQLBrite scheduleSQLBrite){
       return new FirstLaunchUseCase(backgroundScheduler, mainScheduler, scheduleSQLBrite);
    }

    @Provides
    @Singleton
    public FirstLaunchPresenter provideFirstLaunchPresenter(FirstLaunchUseCase firstLaunchUseCase) {
        return new FirstLaunchPresenter(firstLaunchUseCase);
    }
}
