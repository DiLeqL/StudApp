package com.envy.studapp.Entity;

import android.util.Log;

import com.envy.studapp.HTTPAPIInterface.StudServiceAPI;
import com.envy.studapp.Model.BeginningTimeModel;
import com.envy.studapp.Model.ClassroomModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ENVY on 07.06.2017.
 */

public class Classroom {

    public void getBeginningTime() {

        StudServiceAPI studServiceApi = StudServiceAPI.retrofit.create(StudServiceAPI.class);
        final Call<ClassroomModel> call = studServiceApi.getClassroom();

        call.enqueue(new Callback<ClassroomModel>() {

            @Override
            public void onResponse(Call<ClassroomModel> call, Response<ClassroomModel> response) {

            }

            @Override
            public void onFailure(Call<ClassroomModel> call, Throwable t) {

            }
        });
    }
}
