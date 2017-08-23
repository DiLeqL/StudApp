package com.envy.studapp.Schedule.Presentation;

import com.envy.studapp.Schedule.Data.Model.SubjectModel;
import com.envy.studapp.Schedule.Domain.ScheduleResponse;

import java.util.List;


public interface ScheduleView {

    void showError();

    void showResult();

    void showProgressBar();

    void stopProgressBar();

    int getPageNum();

    void notifyAdapter();

    void updateSubjectList(List<SubjectModel> subjectList);

    //void showList(List<SubjectModel> subjectList, String day);

}
