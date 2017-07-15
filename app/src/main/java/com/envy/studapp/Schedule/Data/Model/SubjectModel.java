package com.envy.studapp.Schedule.Data.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ENVY on 06.06.2017.
 */

public class SubjectModel {

    @SerializedName("subjectId")
    int subjectId;

    @SerializedName("subjectDay")
    String subjectDay;

    @SerializedName("subjectName")
    String subjectName;

    @SerializedName("subjectTeacher")
    int subjectTeacher;

    @SerializedName("subjectTime")
    int subjectTime;

    @SerializedName("subjectRoom")
    int subjectRoom;

    @SerializedName("subjectStudGroup")
    int subjectStudGroup;


    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getSubjectTeacher() {
        return subjectTeacher;
    }

    public void setSubjectTeacher(int subjectTeacher) {
        this.subjectTeacher = subjectTeacher;
    }

    public int getSubjectTime() {
        return subjectTime;
    }

    public void setSubjectTime(int subjectTime) {
        this.subjectTime = subjectTime;
    }

    public int getSubjectRoom() {
        return subjectRoom;
    }

    public void setSubjectRoom(int subjectRoom) {
        this.subjectRoom = subjectRoom;
    }

    public int getSubjectStudGroup() {
        return subjectStudGroup;
    }

    public void setSubjectStudGroup(int subjectStudGroup) {
        this.subjectStudGroup = subjectStudGroup;
    }

    public String getSubjectDay() {
        return subjectDay;
    }

    public void setSubjectDay(String subjectDay) {
        this.subjectDay = subjectDay;
    }

}
