package com.envy.studapp.Schedule.Domain;

import android.util.Log;

import com.envy.studapp.Schedule.Data.HttpAPIInterface.StudServiceAPI;
import com.envy.studapp.Schedule.Data.BeginningTimeModel;
import com.envy.studapp.Schedule.Data.ClassroomModel;
import com.envy.studapp.Schedule.Data.GroupModel;
import com.envy.studapp.Schedule.Data.SubjectModel;
import com.envy.studapp.Schedule.Data.TeacherModel;

import java.util.List;

import javax.inject.Inject;

import dagger.Provides;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ENVY on 08.06.2017.
 */


public class ScheduleDownloaderUseCase {

    StudServiceAPI studServiceAPI;

    @Inject
    public ScheduleDownloaderUseCase(StudServiceAPI studServiceAPI){

        this.studServiceAPI = studServiceAPI;
    }

    public void getTeacher() {

        final Call<List<TeacherModel>> call = studServiceAPI.getTeacher();

        call.enqueue(new Callback<List<TeacherModel>>() {

            @Override
            public void onResponse(Call<List<TeacherModel>> call, Response<List<TeacherModel>> response) {

                Log.d("json", response.body().toString());
                List<TeacherModel> teacherList = response.body();
                for (TeacherModel teacherModel: teacherList) {
                    Log.d("json", teacherModel.getTeacherName());
                }

            }

            @Override
            public void onFailure(Call<List<TeacherModel>> call, Throwable t) {
                Log.d("json", "something went wrong or u forgot create localhost");
            }
        });
    }

    public void getSubject() {

        final Call<SubjectModel> call = studServiceAPI.getSubject();

        call.enqueue(new Callback<SubjectModel>() {
            @Override
            public void onResponse(Call<SubjectModel> call, retrofit2.Response<SubjectModel> response) {

            }

            @Override
            public void onFailure(Call<SubjectModel> call, Throwable t) {
                Log.d("jsonLog", "failed");
            }
        });
    }

    public void getGroup() {

        final Call<GroupModel> call = studServiceAPI.getGroup();

        call.enqueue(new Callback<GroupModel>() {
            @Override
            public void onResponse(Call<GroupModel> call, Response<GroupModel> response) {

            }

            @Override
            public void onFailure(Call<GroupModel> call, Throwable t) {

            }
        });
    }

    public void getClassRoom() {

        final Call<ClassroomModel> call = studServiceAPI.getClassroom();

        call.enqueue(new Callback<ClassroomModel>() {

            @Override
            public void onResponse(Call<ClassroomModel> call, Response<ClassroomModel> response) {

            }

            @Override
            public void onFailure(Call<ClassroomModel> call, Throwable t) {

            }
        });
    }

    public void getBeginningTime() {

        final Call<BeginningTimeModel> call = studServiceAPI.getBeginningTime();

        call.enqueue(new Callback<BeginningTimeModel>() {
            @Override
            public void onResponse(Call<BeginningTimeModel> call, retrofit2.Response<BeginningTimeModel> response) {

            }

            @Override
            public void onFailure(Call<BeginningTimeModel> call, Throwable t) {
                Log.d("jsonLog", "failed");
            }
        });
    }

}
