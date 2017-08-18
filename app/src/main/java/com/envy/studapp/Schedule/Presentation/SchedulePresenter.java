package com.envy.studapp.Schedule.Presentation;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.util.ArrayMap;
import android.util.Log;

import com.allattentionhere.fabulousfilter.AAH_FabulousFragment;
import com.envy.studapp.BasePresenter;
import com.envy.studapp.DataBase.ScheduleSQLBrite;
import com.envy.studapp.Filter.Data.FilterKey;
import com.envy.studapp.Schedule.Data.Model.SubjectModel;
import com.envy.studapp.Schedule.Domain.ScheduleFromDbUseCase;
import com.envy.studapp.Schedule.Domain.ScheduleResponse;
import com.envy.studapp.Schedule.Domain.ScheduleDownloaderUseCase;

import java.util.List;
import java.util.Map;

import rx.Observer;
import rx.subscriptions.CompositeSubscription;

public class SchedulePresenter extends BasePresenter<ScheduleView> {

    ScheduleDownloaderUseCase scheduleDownloaderUseCase;

    ScheduleFromDbUseCase scheduleFromDbUseCase;

    ScheduleSQLBrite scheduleSQLBrite;

    Object observable;

    ConnectivityManager connectivityManager;

    DialogCreator dialogCreator;

    CompositeSubscription compositeSubscription;

    public SchedulePresenter(ScheduleDownloaderUseCase scheduleDownloaderUseCase,
                             ScheduleFromDbUseCase scheduleFromDbUseCase,
                             ScheduleSQLBrite scheduleSQLBrite, ConnectivityManager cm,
                             DialogCreator dialogCreator) {
        this.scheduleDownloaderUseCase = scheduleDownloaderUseCase;
        this.scheduleFromDbUseCase = scheduleFromDbUseCase;
        this.scheduleSQLBrite = scheduleSQLBrite;
        this.connectivityManager = cm;
        this.dialogCreator = dialogCreator;
    }


    private boolean isConnected() {

        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        return (activeNetwork != null && activeNetwork.isConnected());
    }


    private Observer<ScheduleResponse> getScheduleObserver() {
        return new Observer<ScheduleResponse>() {

            @Override
            public void onNext(ScheduleResponse value) {

                for (SubjectModel subject: value.getSubjectListFromDb()) {
                    Log.d("numerator", subject.getNumerator());
                }
                Log.d("numerator", "real values");
                if (value != null) {
                    if (isVisibleView()) {
                        List<SubjectModel> subjectModelList = value.getSubjectListFromDb();
                        view.setSubjectList(subjectModelList);
                        subjectModelList = subjectModelList;
                        //view.updateSchedule(value);
                        view.stopProgressBar();
                    }
                }
            }


            @Override
            public void onCompleted() {


            }

            @Override
            public void onError(Throwable e) {

                Log.d("errorSchedule", e.getMessage());
            }

        };
    }

    @Override
    public void onCreateView(ScheduleView view, Bundle savedInstanceState) {
        super.onCreateView(view, savedInstanceState);
        Observer<ScheduleResponse> subscriber = getScheduleObserver();
        if (isConnected()) {
           scheduleDownloaderUseCase.subscribe(observable, subscriber);
           //compositeSubscription.add(scheduleDownloaderUseCase.getSubscription());
        } else {
            scheduleFromDbUseCase.subscribe(observable, subscriber);
            //compositeSubscription.add(scheduleFromDbUseCase.getSubscription());
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //compositeSubscription.unsubscribe();
    }

    public void openDialog(){
        dialogCreator.createDialog();
    }
}
