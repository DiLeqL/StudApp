package com.envy.studapp.Schedule.Presentation;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;

import com.envy.studapp.Schedule.Data.DataBase.ScheduleSQLBrite;
import com.envy.studapp.Schedule.Data.ScheduleResponse;
import com.envy.studapp.Schedule.Domain.ScheduleDownloaderUseCase;
import com.envy.studapp.Schedule.Fragment.ScheduleFragment;

import javax.inject.Inject;

import rx.Observer;

/**
 * Created by ENVY on 14.06.2017.
 */

public class SchedulePresenter extends BasePresenter<ScheduleView>{

    ScheduleResponse scheduleResponse;

    ScheduleDownloaderUseCase scheduleDownloaderUseCase;

    ScheduleSQLBrite scheduleSQLBrite;

    Object object;

    public SchedulePresenter(ScheduleDownloaderUseCase scheduleDownloaderUseCase,
                             ScheduleSQLBrite scheduleSQLBrite){
        this.scheduleDownloaderUseCase = scheduleDownloaderUseCase;
        this.scheduleSQLBrite = scheduleSQLBrite;
    }


    public Observer<ScheduleResponse> getScheduleObserver(){
        Observer<ScheduleResponse> observer = new Observer<ScheduleResponse>() {

            @Override
            public void onNext(ScheduleResponse value) {

                scheduleSQLBrite.updateScheduleDB(value);
                scheduleSQLBrite.getTeacherModelList();

                if (value == null){
                    Log.d("val", "value is null");
                }
                else {
                    Log.d("val", value.toString());
                }

                if (isVisibleView()){
                   // view.updateSchedule(value);
                }
            }


            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.d("val", "check connection");
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
