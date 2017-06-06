package com.envy.studapp.Model;

import com.google.gson.Gson;

import java.util.List;

import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ENVY on 06.06.2017.
 */

public class Schedule {

    public static final String ROOT_URL = "/JSON/schedule.json";

    public static final String KEY_TEACHER_ID = "key_teacher_id";
    public static final String KEY_TEACHER_NAME = "key_teacher_name";

    public static final String KEY_GROUP_ID = "key_group_id";
    public static final String KEY_GROUP_NAME = "key_group_name";

    public static final String KEY_SUNJECT_ID = "key_subject_id";
    public static final String KEY_SUBJECT_NAME = "key_subject_name";
    public static final String KEY_SUBJECT_TEACHER = "key_subject_teacher";
    public static final String KEY_SUBJECT_TIME = "key_subject_time";
    public static final String KEY_SUBJECT_ROOM = "key_subject_room";
    public static final String KEY_SUBJECT_STUD_GROUP = "key_subject_stud_group";

    public static final String KEY_TIME_ID = "key_time_id";
    public static final String KEY_TIME = "key_time";

    public static final String KEY_ROOM_ID = "key_room_id";
    public static final String KEY_ROOM_NUM = "key_room_num";

    private Call<List<ScheduleModel>> schedule;

    private void getSchedule(){

        //Creating a rest adapter
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //Creating an object of our api interface
        ScheduleAPI api = retrofit.create(ScheduleAPI.class);

        //Defining the method
        api.getSchedule(new Callback<List<ScheduleModel>>() {
            @Override
            public void onResponse(Call<List<ScheduleModel>> call, retrofit2.Response<List<ScheduleModel>> response) {
                //Storing the data in our list
                schedule = call;
            }

            @Override
            public void onFailure(Call<List<ScheduleModel>> call, Throwable t) {

            }
        });
    }

}
