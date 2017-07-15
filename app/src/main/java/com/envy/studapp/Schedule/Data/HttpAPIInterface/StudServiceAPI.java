package com.envy.studapp.Schedule.Data.HttpAPIInterface;


import com.envy.studapp.Schedule.Domain.ScheduleResponse;


import dagger.Module;
import retrofit2.http.GET;
import rx.Observable;

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
