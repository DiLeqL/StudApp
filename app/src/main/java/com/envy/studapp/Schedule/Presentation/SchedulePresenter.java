package com.envy.studapp.Schedule.Presentation;

import android.os.Bundle;
import android.util.Log;

import com.envy.studapp.Schedule.Data.DataBase.ScheduleSQLBrite;
import com.envy.studapp.Schedule.Data.Model.SubjectModel;
import com.envy.studapp.Schedule.Domain.ScheduleResponse;
import com.envy.studapp.Schedule.Domain.ScheduleDownloaderUseCase;

import java.util.List;

import rx.Observer;

/**
 * Created by ENVY on 14.06.2017.
 */

public class SchedulePresenter extends BasePresenter<ScheduleView> {

    ScheduleDownloaderUseCase scheduleDownloaderUseCase;

    ScheduleSQLBrite scheduleSQLBrite;

    Object object;

    List<SubjectModel> subjectModelList;

    public SchedulePresenter(ScheduleDownloaderUseCase scheduleDownloaderUseCase,
                             ScheduleSQLBrite scheduleSQLBrite) {
        this.scheduleDownloaderUseCase = scheduleDownloaderUseCase;
        this.scheduleSQLBrite = scheduleSQLBrite;
    }


    public Observer<ScheduleResponse> getScheduleObserver() {
        Observer<ScheduleResponse> observer = new Observer<ScheduleResponse>() {

            @Override
            public void onNext(ScheduleResponse value) {

                if (value == null) {
                    Log.d("val", "value is null");
                } else {
                    Log.d("val", value.toString());
                }

                List<SubjectModel> subjectModelList = value.getSubjectListFromDb();
                for (SubjectModel subject: subjectModelList
                     ) {
                    Log.d("subjectList", subject.getSubjectName());

                }

                if (isVisibleView()) {
                    view.updateSchedule(value);
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
        return observer;
    }

    @Override
    public void onCreateView(ScheduleView view, Bundle savedInstanceState) {
        super.onCreateView(view, savedInstanceState);
        Observer<ScheduleResponse> subscriber = getScheduleObserver();
        scheduleDownloaderUseCase.subscribe(object, subscriber);
    }
}
