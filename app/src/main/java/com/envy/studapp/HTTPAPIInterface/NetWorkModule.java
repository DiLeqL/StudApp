package com.envy.studapp.HTTPAPIInterface;

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
}