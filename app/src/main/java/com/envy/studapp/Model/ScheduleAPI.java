package com.envy.studapp.Model;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Created by ENVY on 06.06.2017.
 */

public interface ScheduleAPI {

    @GET("/json/schedule.json")
    Call<ResponseBody> getSchedule();

    public final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://10.0.3.2:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
