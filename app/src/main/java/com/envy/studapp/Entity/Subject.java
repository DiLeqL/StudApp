package com.envy.studapp.Entity;

import android.util.Log;

import com.envy.studapp.HTTPAPIInterface.StudServiceAPI;
import com.envy.studapp.Model.SubjectModel;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by ENVY on 06.06.2017.
 */

public class Subject {

//    public static final String ROOT_URL = "http://10.0.3.2:3000/";

//    public static final String KEY_TEACHER_ID = "key_teacher_id";
//    public static final String KEY_TEACHER_NAME = "key_teacher_name";
//
//    public static final String KEY_GROUP_ID = "key_group_id";
//    public static final String KEY_GROUP_NAME = "key_group_name";
//
//    public static final String KEY_SUBJECT_ID = "key_subject_id";
//    public static final String KEY_SUBJECT_NAME = "key_subject_name";
//    public static final String KEY_SUBJECT_TEACHER = "key_subject_teacher";
//    public static final String KEY_SUBJECT_TIME = "key_subject_time";
//    public static final String KEY_SUBJECT_ROOM = "key_subject_room";
//    public static final String KEY_SUBJECT_STUD_GROUP = "key_subject_stud_group";
//
//    public static final String KEY_TIME_ID = "key_time_id";
//    public static final String KEY_TIME = "key_time";
//
//    public static final String KEY_ROOM_ID = "key_room_id";
//    public static final String KEY_ROOM_NUM = "key_room_num";


    public void getSchedule() {

        StudServiceAPI studServiceApi = StudServiceAPI.retrofit.create(StudServiceAPI.class);
        final Call<SubjectModel> call = studServiceApi.getSubject();

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

}
