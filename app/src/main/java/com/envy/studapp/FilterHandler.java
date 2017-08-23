package com.envy.studapp;

import com.envy.studapp.Schedule.Data.Model.SubjectModel;

import java.util.List;


public interface FilterHandler {

    void updateResult(List<SubjectModel> subjectModelList);
}
