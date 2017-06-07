package com.envy.studapp.Entity;

import android.util.Log;

import com.envy.studapp.HTTPAPIInterface.StudServiceAPI;
import com.envy.studapp.Model.BeginningTimeModel;
import com.envy.studapp.Model.BeginningTimeModel;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by ENVY on 07.06.2017.
 */

public class BeginningTime {

    public void getBeginningTime() {

        StudServiceAPI studServiceApi = StudServiceAPI.retrofit.create(StudServiceAPI.class);
        final Call<BeginningTimeModel> call = studServiceApi.getBeginningTime();

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
