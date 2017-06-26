package com.envy.studapp.Schedule.Data;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ENVY on 14.06.2017.
 */

public class ScheduleResponse {

    @SerializedName("teachers")
    List<TeacherModel> teacherList;

    public void getTeacherNames(){
        for (TeacherModel teacherModel: teacherList) {
            Log.d("json", teacherModel.getTeacherName());
        }
    }

}
