package com.envy.studapp.Filter.Presentation;

import android.os.Bundle;
import android.support.v4.util.ArrayMap;
import android.util.Log;

import com.allattentionhere.fabulousfilter.AAH_FabulousFragment;
import com.envy.studapp.Filter.Data.FilterKey;
import com.envy.studapp.Filter.Domain.FilterKeyUseCase;
import com.envy.studapp.Schedule.Data.Model.SubjectModel;
import com.envy.studapp.BasePresenter;
import com.envy.studapp.Schedule.Domain.ScheduleFromDbUseCase;
import com.envy.studapp.Schedule.Domain.ScheduleResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import rx.Observer;


public class FilterPresenter extends BasePresenter<FilterView> implements AAH_FabulousFragment.Callbacks {

    private FilterKeyUseCase filterKeyUseCase;

    private ScheduleFromDbUseCase scheduleFromDbUseCase;

    private Object observable = new Object();

    private List<SubjectModel> subjectList;

    //private List<SubjectModel> fullSubjectModelList = new ArrayList<>();

    private FilterKey filterKey = new FilterKey();

    //CompositeSubscription compositeSubscription;

    public FilterPresenter(FilterKeyUseCase filterKeyUseCase,
                           ScheduleFromDbUseCase scheduleFromDbUseCase) {
        this.filterKeyUseCase = filterKeyUseCase;
        this.scheduleFromDbUseCase = scheduleFromDbUseCase;
    }

    private Observer<ScheduleResponse> getScheduleObserver() {
        return new Observer<ScheduleResponse>() {

            @Override
            public void onNext(ScheduleResponse value) {

                if (value != null){
                    Log.d("list", "list set");
                    subjectList = value.getSubjectListFromDb();
                   /* if (isVisibleView()) {
                        subjectList = value.getSubjectListFromDb();
                        //view.updateSubjectList(subjectModelList);
                        //subjectModelList = subjectModelList;
                        //view.updateSchedule(value);
                        //view.stopProgressBar();
                    }*/
                }
            }


            @Override
            public void onCompleted() {


            }

            @Override
            public void onError(Throwable e) {

                Log.d("error", e.getMessage());
            }

        };
    }

    private Observer<FilterKey> getFilterKeyObserver() {
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
                if (filterKeyModel != null){
                    view.setFilterKey(filterKeyModel);

                    for (String teacher :
                            filterKeyModel.getTeacherKeyList()) {
                        Log.d("filterKey", teacher);
                    }
                    for (String day :
                            filterKeyModel.getWeekdayKeyList()) {
                        Log.d("filterKey", day);
                    }
                    for (String group :
                            filterKeyModel.getGroupNumKeyList()) {
                        Log.d("filterKey", group);
                    }
                }
            }
        };
    }

    @Override
    public void onCreateView(FilterView view, Bundle savedInstanceState) {
        super.onCreateView(view, savedInstanceState);
        Observer<FilterKey> filterKeySubscriber = getFilterKeyObserver();
        filterKeyUseCase.subscribe(observable, filterKeySubscriber);
        //compositeSubscription.
        Observer<ScheduleResponse> scheduleResponseSubscriber = getScheduleObserver();
        scheduleFromDbUseCase.subscribe(observable, scheduleResponseSubscriber);
    }


    @Override
    public void onResult(Object result) {
        Log.d("k9res", "onResult: " + result.toString());
        if (result.toString().equalsIgnoreCase("swiped_down")) {
            Log.d("Swipe", "swiped down");
        } else {
            Log.d("list", "list use");
            @SuppressWarnings("unchecked")
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
                        case "day":
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
//                    view.updateSubjectList(subjectList);
//                    view.notifyAdapter();
            } else {
                view.updateSubjectList(subjectList);
//                    view.updateSubjectList(subjectList);
//                    view.notifyAdapter();
            }
            //handle result
        }
    }
}
