package com.envy.studapp.Filter.Data;

import com.envy.studapp.BaseFilterKey;
import com.envy.studapp.Schedule.Data.Model.SubjectModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ENVY on 02.08.2017.
 */

public class FilterKey extends BaseFilterKey{

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

    public List<SubjectModel> getTeacherFilteredSubjectList(List<String> teacherKeyList, List<SubjectModel> mList) {
        List<SubjectModel> tempList = new ArrayList<>();
        for (SubjectModel subject : mList) {
            for (String g : teacherKeyList) {
                if (subject.getSubjectTeacher().equalsIgnoreCase(g)) {
                    tempList.add(subject);
                }
            }
        }
        return tempList;
    }

    public List<SubjectModel> getWeekdayFilteredSubjectList(List<String> weekdayKeyList, List<SubjectModel> mList) {
        List<SubjectModel> tempList = new ArrayList<>();
        for (SubjectModel subject : mList) {
            for (String g : weekdayKeyList) {
                if (subject.getSubjectDay().equalsIgnoreCase(g)) {
                    tempList.add(subject);
                }
            }
        }
        return tempList;
    }

    public List<SubjectModel> getGroupNumFilteredSubjectList(List<String> groupNumKeyList, List<SubjectModel> mList) {
        List<SubjectModel> tempList = new ArrayList<>();
        for (SubjectModel subject : mList) {
            for (String g : groupNumKeyList) {
                if (subject.getSubjectStudGroup().equalsIgnoreCase(g)) {
                    tempList.add(subject);
                }
            }
        }
        return tempList;
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
