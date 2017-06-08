package com.envy.studapp.Entity;

import android.util.Log;

import com.envy.studapp.HttpAPIInterface.NetWorkModule;
import com.envy.studapp.HttpAPIInterface.StudServiceAPI;
import com.envy.studapp.Model.BeginningTimeModel;
import com.envy.studapp.Model.ClassroomModel;
import com.envy.studapp.Model.GroupModel;
import com.envy.studapp.Model.SubjectModel;
import com.envy.studapp.Model.TeacherModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ENVY on 08.06.2017.
 */

public class ReadingData {

    StudServiceAPI studServiceAPI;

    public ReadingData(StudServiceAPI studServiceAPI){

        this.studServiceAPI = studServiceAPI;
    }

    public void getTeacher() {

        final Call<TeacherModel> call = studServiceAPI.getTeacher();

        call.enqueue(new Callback<TeacherModel>() {

            @Override
            public void onResponse(Call<TeacherModel> call, Response<TeacherModel> response) {

            }

            @Override
            public void onFailure(Call<TeacherModel> call, Throwable t) {

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
