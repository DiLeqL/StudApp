package com.envy.studapp.Schedule.Data.HttpAPIInterface;

import com.envy.studapp.Schedule.Data.BeginningTimeModel;
import com.envy.studapp.Schedule.Data.ClassroomModel;
import com.envy.studapp.Schedule.Data.GroupNumModel;
import com.envy.studapp.Schedule.Data.ScheduleResponse;
import com.envy.studapp.Schedule.Data.SubjectModel;
import com.envy.studapp.Schedule.Data.TeacherModel;

import java.util.List;

import dagger.Module;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by ENVY on 06.06.2017.
 */

@Module
public interface StudServiceAPI {

    /*@GET("/json/subjects.json")
    Call<SubjectModel> getSubject();

    @GET("/json/teachers.json")
    Observable<List<TeacherModel>> getTeacher();

    @GET("/json/beginningTime.json")
    Call<BeginningTimeModel> getBeginningTime();

    @GET("/json/classrooms.json")
    Call<ClassroomModel> getClassroom();

    @GET("/json/groups.json")
    Call<GroupNumModel> getGroup();*/

    @GET("/json/schedule.json")
    Observable<ScheduleResponse> getSchedule();

}
