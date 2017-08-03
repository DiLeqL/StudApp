package com.envy.studapp.Schedule.Presentation;

import android.os.Bundle;
import android.util.Log;

import com.envy.studapp.BasePresenter;
import com.envy.studapp.DataBase.ScheduleSQLBrite;
import com.envy.studapp.Schedule.Data.Model.SubjectModel;
import com.envy.studapp.Schedule.Domain.ScheduleResponse;
import com.envy.studapp.Schedule.Domain.ScheduleDownloaderUseCase;

import java.util.List;

import rx.Observer;

public class SchedulePresenter extends BasePresenter<ScheduleView> {

    ScheduleDownloaderUseCase scheduleDownloaderUseCase;

    ScheduleSQLBrite scheduleSQLBrite;

    Object observable;

    static final String ARGUMENT_PAGE_NUMBER = "arg_page_number";

    public SchedulePresenter(ScheduleDownloaderUseCase scheduleDownloaderUseCase,
                             ScheduleSQLBrite scheduleSQLBrite) {
        this.scheduleDownloaderUseCase = scheduleDownloaderUseCase;
        this.scheduleSQLBrite = scheduleSQLBrite;
    }


    private Observer<ScheduleResponse> getScheduleObserver() {
        return new Observer<ScheduleResponse>() {

            @Override
            public void onNext(ScheduleResponse value) {


                if (isVisibleView()) {
                    List<SubjectModel> subjectModelList = value.getSubjectListFromDb();
                    view.setSubjectList(subjectModelList);
                    //view.updateSchedule(value);
                    view.stopProgressBar();
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

    @Override
    public void onCreateView(ScheduleView view, Bundle savedInstanceState) {
        super.onCreateView(view, savedInstanceState);
        Observer<ScheduleResponse> subscriber = getScheduleObserver();
        scheduleDownloaderUseCase.subscribe(observable, subscriber);
    }
}
