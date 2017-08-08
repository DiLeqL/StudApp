package com.envy.studapp.Filter.Presentation;

import com.envy.studapp.Filter.Data.FilterKey;
import com.envy.studapp.Schedule.Data.Model.SubjectModel;

import java.util.List;

/**
 * Created by ENVY on 02.08.2017.
 */

public interface FilterView {

    void setFilterKey(FilterKey filterKey);

    void updateSubjectList(List<SubjectModel> subjectList);

}
