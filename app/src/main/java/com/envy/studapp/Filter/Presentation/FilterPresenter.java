package com.envy.studapp.Filter.Presentation;

import android.os.Bundle;
import android.util.Log;

import com.envy.studapp.DataBase.ScheduleSQLBrite;
import com.envy.studapp.Filter.Data.FilterKey;
import com.envy.studapp.Filter.Domain.FilterKeyUseCase;
import com.envy.studapp.Schedule.Domain.ScheduleDownloaderUseCase;
import com.envy.studapp.BasePresenter;

import java.util.List;

import rx.Observable;
import rx.Observer;

/**
 * Created by ENVY on 02.08.2017.
 */

public class FilterPresenter extends BasePresenter<FilterView> {

    ScheduleSQLBrite scheduleSQLBrite;

    FilterKeyUseCase filterKeyUseCase;

    Object observable;

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
}
