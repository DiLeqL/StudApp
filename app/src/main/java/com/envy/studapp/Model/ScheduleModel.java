package com.envy.studapp.Model;

/**
 * Created by ENVY on 06.06.2017.
 */

public class ScheduleModel {

    int teacherId;
    String teacherName;

    String groupId;
    String groupName;

    int subjectId;
    String subjectName;
    int subjectTeacher;
    int subjectTime;
    int subjectRoom;
    int subjectStudGroup;

    int timeId;
    String time;

    int roomId;
    String roomNum;

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

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

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

    public int getTimeId() {
        return timeId;
    }

    public void setTimeId(int timeId) {
        this.timeId = timeId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
    }

}
