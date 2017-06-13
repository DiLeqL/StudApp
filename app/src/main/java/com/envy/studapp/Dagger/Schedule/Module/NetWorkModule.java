package com.envy.studapp.Dagger.Schedule.Module;

import com.envy.studapp.Schedule.Data.HttpAPIInterface.StudServiceAPI;
import com.envy.studapp.Schedule.Domain.ScheduleDownloaderUseCase;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



@Module
public class NetWorkModule {

    @Provides
    @Singleton
    public Retrofit provideRetrofit(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.3.2:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit;
    }

    @Provides
    @Singleton
    public StudServiceAPI provideStudServiceAPI(Retrofit retrofit){

        return retrofit.create(StudServiceAPI.class);
    }

    @Provides
    @Named("mainScheduler")
    public Scheduler provideMainScheduler(){
        return AndroidSchedulers.mainThread();
    }

    @Provides
    @Named("backgroundScheduler")
    public Scheduler provideBackgroundScheduler(){
        return Schedulers.io();
    }

    @Provides
    @Singleton
    public ScheduleDownloaderUseCase provideScheduleDownloaderUseCase(StudServiceAPI studServiceAPI,
                                                                      @Named("backgroundScheduler")Scheduler backgroundScheduler,
                                                                      @Named("mainScheduler")Scheduler mainScheduler){

        return new ScheduleDownloaderUseCase(studServiceAPI, backgroundScheduler, mainScheduler);
    }
}
