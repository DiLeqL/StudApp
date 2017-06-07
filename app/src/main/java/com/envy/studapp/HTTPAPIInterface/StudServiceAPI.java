package com.envy.studapp.HTTPAPIInterface;

import com.envy.studapp.Model.BeginningTimeModel;
import com.envy.studapp.Model.ClassroomModel;
import com.envy.studapp.Model.GroupModel;
import com.envy.studapp.Model.ScheduleModel;
import com.envy.studapp.Model.TeacherModel;

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

public interface StudServiceAPI {

    @GET("/json/schedule.json")
    Call<ScheduleModel> getSchedule();

    @GET("/json/teachers.json")
    Call<TeacherModel> getTeacher();

    @GET("/json/begginingTime.json")
    Call<BeginningTimeModel> getBeginningTime();

    @GET("/json/classrooms.json")
    Call<ClassroomModel> getClassroom();

    @GET("/json/groups.json")
    Call<GroupModel> getGroup();

    public final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://10.0.3.2:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
