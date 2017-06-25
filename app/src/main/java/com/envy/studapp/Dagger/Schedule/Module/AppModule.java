package com.envy.studapp.Dagger.Schedule.Module;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ENVY on 19.06.2017.
 */

@Module
public class AppModule {

    @Provides
    @Singleton
    @Named("mainScheduler")
    public Scheduler provideMainScheduler(){
        return AndroidSchedulers.mainThread();
    }

    @Provides
    @Singleton
    @Named("backgroundScheduler")
    public Scheduler provideBackgroundScheduler(){
        return Schedulers.io();
    }
}
