package com.envy.studapp.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ENVY on 07.06.2017.
 */

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
