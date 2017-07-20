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
                             ScheduleSQLBrite scheduleSQLBrite){
        this.scheduleDownloaderUseCase = scheduleDownloaderUseCase;
        this.scheduleSQLBrite = scheduleSQLBrite;
    }


    public Observer<ScheduleResponse> getScheduleObserver(){
        Observer<ScheduleResponse> observer = new Observer<ScheduleResponse>() {

            @Override
            public void onNext(ScheduleResponse value) {



                //scheduleSQLBrite.createSubjectModelList();

                //List<SubjectModel> scheduleList = scheduleSQLBrite.getSubjectModelList();

                /*if (scheduleList != null){
                    for (SubjectModel subjectModel: subjectModelList) {
                        Log.d("list on presenter", subjectModel.getSubjectTeacher());
                    }
                }*/

                /*for (SubjectModel subjectModel: subjectModelList) {
                    Log.d("list on presenter", subjectModel.getSubjectTeacher());
                }*/
                if (value == null){
                    Log.d("val", "value is null");
                }
                else {
                    Log.d("val", value.toString());
                }

                if (isVisibleView()){
                   view.updateSchedule(value);
                }
            }


            @Override
            public void onCompleted() {

                //scheduleSQLBrite.createSubjectModelList();
            }

            @Override
            public void onError(Throwable e) {
                Log.d("val", e.toString());
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
