package com.envy.studapp.Filter.Presentation;

import android.os.Bundle;
import android.support.v4.util.ArrayMap;
import android.util.Log;

import com.allattentionhere.fabulousfilter.AAH_FabulousFragment;
import com.envy.studapp.DataBase.ScheduleSQLBrite;
import com.envy.studapp.Filter.Data.FilterKey;
import com.envy.studapp.Filter.Domain.FilterKeyUseCase;
import com.envy.studapp.Schedule.Data.Model.SubjectModel;
import com.envy.studapp.BasePresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import rx.Observer;


public class FilterPresenter extends BasePresenter<FilterView> implements AAH_FabulousFragment.Callbacks{

    ScheduleSQLBrite scheduleSQLBrite;

    FilterKeyUseCase filterKeyUseCase;

    Object observable;

    List<SubjectModel> subjectList;

    List<SubjectModel> fullSubjectModelList = new ArrayList<>();

    FilterKey filterKey;

    public FilterPresenter(FilterKeyUseCase filterKeyUseCase,
            ScheduleSQLBrite scheduleSQLBrite) {
        this.filterKeyUseCase = filterKeyUseCase;
        this.scheduleSQLBrite = scheduleSQLBrite;
    }


    private Observer<FilterKey> getFilterKeyObserver(){
        return new Observer<FilterKey>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.d("filterKey", e.toString());
            }

            @Override
            public void onNext(FilterKey filterKeyModel) {

                view.setFilterKey(filterKeyModel);

                for (String teacher:
                     filterKeyModel.getTeacherKeyList()) {
                    Log.d("filterKey", teacher);
                }
                for (String day:
                        filterKeyModel.getWeekdayKeyList()) {
                    Log.d("filterKey", day);
                }
                for (String group:
                        filterKeyModel.getGroupNumKeyList()) {
                    Log.d("filterKey", group);
                }
            }
        };
    }

    @Override
    public void onCreateView(FilterView view, Bundle savedInstanceState) {
        super.onCreateView(view, savedInstanceState);
        Observer<FilterKey> subscriber = getFilterKeyObserver();
        filterKeyUseCase.subscribe(observable, subscriber);
    }


    @Override
    public void onResult(Object result) {
        Log.d("k9res", "onResult: " + result.toString());
        if (result.toString().equalsIgnoreCase("swiped_down")) {
            //do something or nothing
        } else {
            if (result != null) {
                fullSubjectModelList.addAll(subjectList);
                ArrayMap<String, List<String>> appliedFilters = (ArrayMap<String, List<String>>) result;
                if (appliedFilters.size() != 0) {
                    List<SubjectModel> filteredList = subjectList;
                    //iterate over arraymap
                    for (Map.Entry<String, List<String>> entry : appliedFilters.entrySet()) {
                        Log.d("k9res", "entry.key: " + entry.getKey());
                        switch (entry.getKey()) {
                            case "teacher":
                                filteredList = filterKey.getTeacherFilteredSubjectList(entry.getValue(), filteredList);
                                break;
                            case "weekday":
                                filteredList = filterKey.getWeekdayFilteredSubjectList(entry.getValue(), filteredList);
                                break;
                            case "group":
                                filteredList = filterKey.getGroupNumFilteredSubjectList(entry.getValue(), filteredList);
                                break;
                        }
                    }
                    Log.d("k9res", "new size: " + filteredList.size());
                    subjectList.clear();
                    subjectList.addAll(filteredList);
                    view.updateSubjectList(subjectList);
                    //(MainActivity)
//                    view.setSubjectList(subjectList);
//                    view.notifyAdapter();

                } else {
                    subjectList.addAll(fullSubjectModelList);
                    view.updateSubjectList(subjectList);
//                    view.setSubjectList(subjectList);
//                    view.notifyAdapter();
                }
            }
            //handle result
        }
    }
}
