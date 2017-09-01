package com.envy.studapp.Dagger.Schedule.Module;

import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.support.v4.app.FragmentManager;

import com.envy.studapp.DataBase.ScheduleSQLBrite;
import com.envy.studapp.Fragment.FirstLaunchFragment;
import com.envy.studapp.Schedule.Data.HttpAPIInterface.StudServiceAPI;
import com.envy.studapp.Schedule.Domain.ScheduleDownloaderUseCase;
import com.envy.studapp.Schedule.Domain.ScheduleFromDbUseCase;
import com.envy.studapp.Schedule.Presentation.DialogCreator;
import com.envy.studapp.Schedule.Presentation.SchedulePresenter;
import com.envy.studapp.Schedule.ScheduleCalendarManager;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;


@Module
public class ScheduleModule {

    private FragmentManager fragmentManager;

    private SharedPreferences sharedPreferences;

    private final String FIRST_TIME_OPEN_FRAGMENT_TAG = "FirstTimeOpen";

    public ScheduleModule(FragmentManager fragmentManager, SharedPreferences sharedPreferences){
        this.fragmentManager = fragmentManager;
        this.sharedPreferences = sharedPreferences;
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
            FirstLaunchFragment firstLaunchFragment = new FirstLaunchFragment();
            firstLaunchFragment.show(fragmentManager, FIRST_TIME_OPEN_FRAGMENT_TAG);
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
                                                      ScheduleFromDbUseCase scheduleFromDbUseCase, ConnectivityManager cm,
                                                      DialogCreator dialogCreator,
                                                      ScheduleCalendarManager scm){
        return new SchedulePresenter(scheduleDownloaderUseCase, scheduleFromDbUseCase,
                                        cm, dialogCreator, sharedPreferences, scm);
    }
}
