package com.envy.studapp.Schedule.Data.HttpAPIInterface;


import com.envy.studapp.Schedule.Domain.ScheduleResponse;

import retrofit2.http.GET;
import rx.Observable;


public interface StudServiceAPI {

    @GET("/json/schedule.json")
    Observable<ScheduleResponse> getSchedule();

}
