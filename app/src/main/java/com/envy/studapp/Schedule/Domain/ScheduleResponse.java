package com.envy.studapp.Schedule.Domain;

import android.util.Log;

import com.envy.studapp.Schedule.Data.Model.BeginningTimeModel;
import com.envy.studapp.Schedule.Data.Model.ClassroomModel;
import com.envy.studapp.Schedule.Data.Model.GroupNumModel;
import com.envy.studapp.Schedule.Data.Model.SubjectModel;
import com.envy.studapp.Schedule.Data.Model.TeacherModel;
import com.envy.studapp.Schedule.Data.Model.WeekdayModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ENVY on 14.06.2017.
 */

public class ScheduleResponse {

    @SerializedName("teachers")
    List<TeacherModel> teacherList;

    @SerializedName("subjects")
    List<SubjectModel> subjectList;

    @SerializedName("groups")
    List<GroupNumModel> groupNumList;

    @SerializedName("classrooms")
    List<ClassroomModel> classroomList;

    @SerializedName("beginningTimes")
    List<BeginningTimeModel> beginningTimeList;

    @SerializedName("weekdays")
    List<WeekdayModel> weekdayList;

    @SerializedName("timestamp")
    String timestamp;

   public void getTeacherNames(){
        for (TeacherModel teacherModel: teacherList) {
            Log.d("json", teacherModel.getTeacherName());
        }
    }

    public List<TeacherModel> getTeacherList(){
        return teacherList;
    }

    public List<SubjectModel> getSubjectList(){
        return subjectList;
    }

    public List<GroupNumModel> getGroupNumList() {
        return groupNumList;
    }

    public List<ClassroomModel> getClassroomList() {
        return classroomList;
    }

    public List<BeginningTimeModel> getBeginningTimeList() {
        return beginningTimeList;
    }

    public List<WeekdayModel> getWeekdayList() {
        return weekdayList;
    }

    /*public String getTimestamp(){
        return timestamp;
    }*/

}
