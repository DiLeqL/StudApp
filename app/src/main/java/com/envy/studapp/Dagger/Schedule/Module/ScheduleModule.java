package com.envy.studapp.Dagger.Schedule.Module;

import android.content.Context;
import android.net.ConnectivityManager;
import android.support.v4.app.FragmentManager;

import com.envy.studapp.DataBase.ScheduleSQLBrite;
import com.envy.studapp.Filter.Domain.FilterKeyUseCase;
import com.envy.studapp.Fragment.FirstTimeOpenFragment;
import com.envy.studapp.Schedule.Data.HttpAPIInterface.StudServiceAPI;
import com.envy.studapp.Schedule.Domain.ScheduleDownloaderUseCase;
import com.envy.studapp.Filter.Presentation.FilterPresenter;
import com.envy.studapp.Schedule.Domain.ScheduleFromDbUseCase;
import com.envy.studapp.Schedule.Presentation.DialogCreator;
import com.envy.studapp.Schedule.Presentation.SchedulePresenter;
import com.envy.studapp.Schedule.ScheduleCalendarManager;

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

    FragmentManager fragmentManager;

    final String FIRST_TIME_OPEN_FRAGMENT_TAG = "FirstTimeOpen";

    public ScheduleModule(FragmentManager fragmentManager){
        this.fragmentManager = fragmentManager;
    }

    @Provides
    @Singleton
    public ScheduleCalendarManager provideScheduleCalendarManager(){
        return new ScheduleCalendarManager();
    }

    @Provides
    @Singleton
    public DialogCreator provideDialogCreator(){
        return () -> {
            FirstTimeOpenFragment firstTimeOpenFragment = new FirstTimeOpenFragment();
            firstTimeOpenFragment.show(fragmentManager, FIRST_TIME_OPEN_FRAGMENT_TAG);
        };
    }

    @Provides
    @Singleton
    public ScheduleDownloaderUseCase provideScheduleDownloaderUseCase(StudServiceAPI studServiceAPI,
                                                                      @Named("backgroundScheduler") Scheduler backgroundScheduler,
                                                                      @Named("mainScheduler")Scheduler mainScheduler,
                                                                      ScheduleSQLBrite scheduleSQLBrite,
                                                                      ScheduleCalendarManager scm){
        return new ScheduleDownloaderUseCase(studServiceAPI, backgroundScheduler, mainScheduler,
                scheduleSQLBrite, scm);
    }

    @Provides
    @Singleton
    public ScheduleFromDbUseCase provideScheduleFromDbUseCase(@Named("backgroundScheduler") Scheduler backgroundScheduler,
                                                              @Named("mainScheduler")Scheduler mainScheduler,
                                                                  ScheduleSQLBrite scheduleSQLBrite,
                                                              ScheduleCalendarManager scm){
        return new ScheduleFromDbUseCase(backgroundScheduler, mainScheduler,
                scheduleSQLBrite, scm);
    }

    @Provides
    @Singleton
    public SchedulePresenter provideSchedulePresenter(ScheduleDownloaderUseCase scheduleDownloaderUseCase,
                                                      ScheduleFromDbUseCase scheduleFromDbUseCase,
                                                      ScheduleSQLBrite scheduleSQLBrite, ConnectivityManager cm,
                                                      DialogCreator dialogCreator){
        return new SchedulePresenter(scheduleDownloaderUseCase, scheduleFromDbUseCase,
                scheduleSQLBrite, cm, dialogCreator);
    }
}
