package com.envy.studapp.Schedule.Data.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ENVY on 06.06.2017.
 */

public class SubjectModel {

    @SerializedName("subjectId")
    String subjectId;

    @SerializedName("subjectDay")
    String subjectDay;

    @SerializedName("subjectName")
    String subjectName;

    @SerializedName("subjectTeacher")
    String subjectTeacher;

    @SerializedName("subjectStudGroup")
    String subjectStudGroup;

    @SerializedName("subjectRoom")
    String subjectRoom;

    @SerializedName("subjectTime")
    String subjectTime;

    @SerializedName("numerator")
    String numerator;

    public SubjectModel(String subjectId, String subjectDay, String subjectName,
                        String subjectTeacher, String subjectStudGroup,
                        String subjectRoom, String subjectTime, String numerator) {
        this.subjectId = subjectId;
        this.subjectDay = subjectDay;
        this.subjectName = subjectName;
        this.subjectTeacher = subjectTeacher;
        this.subjectStudGroup = subjectStudGroup;
        this.subjectRoom = subjectRoom;
        this.subjectTime = subjectTime;
        this.numerator = numerator;
    }

    public String getNumerator() {
        return numerator;
    }

    public void setNumerator(String numerator) {
        this.numerator = numerator;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectDay() {
        return subjectDay;
    }

    public void setSubjectDay(String subjectDay) {
        this.subjectDay = subjectDay;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectTeacher() {
        return subjectTeacher;
    }

    public void setSubjectTeacher(String subjectTeacher) {
        this.subjectTeacher = subjectTeacher;
    }

    public String getSubjectTime() {
        return subjectTime;
    }

    public void setSubjectTime(String subjectTime) {
        this.subjectTime = subjectTime;
    }

    public String getSubjectRoom() {
        return subjectRoom;
    }

    public void setSubjectRoom(String subjectRoom) {
        this.subjectRoom = subjectRoom;
    }

    public String getSubjectStudGroup() {
        return subjectStudGroup;
    }

    public void setSubjectStudGroup(String subjectStudGroup) {
        this.subjectStudGroup = subjectStudGroup;
    }

}
