package com.envy.studapp.Filter.Data;

import java.util.List;

/**
 * Created by ENVY on 02.08.2017.
 */

public class FilterKey {


    private List<String> teacherKeyList;
    private List<String> weekdayKeyList;
    private List<String> groupNumKeyList;

    public List<String> getTeacherKeyList() {
        return teacherKeyList;
    }

    public List<String> getWeekdayKeyList() {
        return weekdayKeyList;
    }

    public List<String> getGroupNumKeyList() {
        return groupNumKeyList;
    }

    public void setTeacherKeyList(List<String> teacherKeyList) {
        this.teacherKeyList = teacherKeyList;
    }

    public void setWeekdayKeyList(List<String> weekdayKeyList) {
        this.weekdayKeyList = weekdayKeyList;
    }

    public void setGroupNumKeyList(List<String> groupNumKeyList) {
        this.groupNumKeyList = groupNumKeyList;
    }
}
