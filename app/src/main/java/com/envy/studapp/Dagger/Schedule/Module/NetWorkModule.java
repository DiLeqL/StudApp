package com.envy.studapp.Dagger.Schedule.Module;

import com.envy.studapp.Schedule.Data.HttpAPIInterface.StudServiceAPI;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ENVY on 08.06.2017.
 */

@Module
public class NetWorkModule {

    @Provides
    @Singleton
    public Retrofit provideRetrofit(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.3.2:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

    @Provides
    @Singleton
    public StudServiceAPI provideStudServiceAPI(Retrofit retrofit){
        return retrofit.create(StudServiceAPI.class);
    }
}
