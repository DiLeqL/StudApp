package com.envy.studapp.Entity;

import android.util.Log;

import com.envy.studapp.HTTPAPIInterface.StudServiceAPI;
import com.envy.studapp.Model.BeginningTimeModel;
import com.envy.studapp.Model.GroupModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ENVY on 07.06.2017.
 */

public class Group {

    public void getGroup() {

        StudServiceAPI studServiceApi = StudServiceAPI.retrofit.create(StudServiceAPI.class);
        final Call<GroupModel> call = studServiceApi.getGroup();

        call.enqueue(new Callback<GroupModel>() {
            @Override
            public void onResponse(Call<GroupModel> call, Response<GroupModel> response) {

            }

            @Override
            public void onFailure(Call<GroupModel> call, Throwable t) {

            }
        });
    }
}
