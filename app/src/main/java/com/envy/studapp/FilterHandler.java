package com.envy.studapp;

import com.envy.studapp.Schedule.Data.Model.SubjectModel;

import java.util.List;

/**
 * Created by ENVY on 08.08.2017.
 */

public interface FilterHandler {

    void updateResult(List<SubjectModel> subjectModelList);
}
