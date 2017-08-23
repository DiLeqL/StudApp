package com.envy.studapp.Dagger.Schedule.Module;

import android.content.Context;
import android.net.ConnectivityManager;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


@Module
public class AppModule {

    Context context;

    public AppModule(Context context){
        this.context = context;
    }

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

    @Provides
    @Singleton
    public ConnectivityManager provideConnectivityManager(){
        ConnectivityManager cm =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm;
    }
}
