package com.envy.studapp.Model;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;

/**
 * Created by ENVY on 06.06.2017.
 */

public interface ScheduleAPI {

    @GET("/JSON/schedule.json")
    public Call<List<ScheduleModel>> getSchedule();
}
