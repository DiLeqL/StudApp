package com.envy.studapp.Schedule.Data.Model;

import com.google.gson.annotations.SerializedName;


public class TeacherModel {

    @SerializedName("teacherId")
    int teacherId;

    @SerializedName("name")
    String teacherName;

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

}
