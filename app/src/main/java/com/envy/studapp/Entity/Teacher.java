package com.envy.studapp.Entity;

import android.util.Log;

import com.envy.studapp.HTTPAPIInterface.StudServiceAPI;
import com.envy.studapp.Model.GroupModel;
import com.envy.studapp.Model.TeacherModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ENVY on 07.06.2017.
 */

public class Teacher {

    StudServiceAPI studServiceApi = StudServiceAPI.retrofit.create(StudServiceAPI.class);
    final Call<TeacherModel> call = studServiceApi.getTeacher();

    public void getGroup() {

        StudServiceAPI studServiceApi = StudServiceAPI.retrofit.create(StudServiceAPI.class);
        final Call<TeacherModel> call = studServiceApi.getTeacher();

        call.enqueue(new Callback<TeacherModel>() {

            @Override
            public void onResponse(Call<TeacherModel> call, Response<TeacherModel> response) {

            }

            @Override
            public void onFailure(Call<TeacherModel> call, Throwable t) {

            }
        });
    }

}
