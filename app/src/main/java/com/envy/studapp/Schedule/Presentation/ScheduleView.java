package com.envy.studapp.Schedule.Presentation;

import com.envy.studapp.Schedule.Data.Model.SubjectModel;
import com.envy.studapp.Schedule.Domain.ScheduleResponse;

import java.util.List;

/**
 * Created by ENVY on 14.06.2017.
 */

public interface ScheduleView {

    void showError();

    void showResult();

    void showProgressBar();

    void stopProgressBar();

    int getPageNum();

    void setSubjectList(List<SubjectModel> subjectList);

    //void showList(List<SubjectModel> subjectList, String day);

}
