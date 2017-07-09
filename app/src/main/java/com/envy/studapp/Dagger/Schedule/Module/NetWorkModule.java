package com.envy.studapp.Dagger.Schedule.Module;

import com.envy.studapp.Schedule.Data.HttpAPIInterface.StudServiceAPI;
import com.envy.studapp.Schedule.Domain.ScheduleDownloaderUseCase;
import com.envy.studapp.Schedule.Presentation.SchedulePresenter;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;



@Module
public class NetWorkModule {

    @Provides
    @Singleton
    public Retrofit provideRetrofit(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.3.2:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit;
    }

    @Provides
    @Singleton
    public StudServiceAPI provideStudServiceAPI(Retrofit retrofit){
        return retrofit.create(StudServiceAPI.class);
    }

}
