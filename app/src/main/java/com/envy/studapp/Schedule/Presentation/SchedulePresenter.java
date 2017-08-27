package com.envy.studapp.Schedule.Presentation;

import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;

import com.envy.studapp.BasePresenter;
import com.envy.studapp.Schedule.Data.Model.SubjectModel;
import com.envy.studapp.Schedule.Domain.ScheduleFromDbUseCase;
import com.envy.studapp.Schedule.Domain.ScheduleResponse;
import com.envy.studapp.Schedule.Domain.ScheduleDownloaderUseCase;

import java.util.List;

import rx.Observer;

public class SchedulePresenter extends BasePresenter<ScheduleView> {

    private ScheduleDownloaderUseCase scheduleDownloaderUseCase;

    private ScheduleFromDbUseCase scheduleFromDbUseCase;

    private ConnectivityManager connectivityManager;

    private DialogCreator dialogCreator;

    private SharedPreferences sharedPreferences;

    private boolean isFirstLaunch = true;

    //CompositeSubscription compositeSubscription;

    public SchedulePresenter(ScheduleDownloaderUseCase scheduleDownloaderUseCase,
                             ScheduleFromDbUseCase scheduleFromDbUseCase, ConnectivityManager cm,
                             DialogCreator dialogCreator, SharedPreferences sharedPreferences) {
        this.scheduleDownloaderUseCase = scheduleDownloaderUseCase;
        this.scheduleFromDbUseCase = scheduleFromDbUseCase;
        this.connectivityManager = cm;
        this.dialogCreator = dialogCreator;
        this.sharedPreferences = sharedPreferences;
    }


    private boolean isConnected() {

        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        return (activeNetwork != null && activeNetwork.isConnected());
    }


    private Observer<ScheduleResponse> getScheduleObserver() {
        return new Observer<ScheduleResponse>() {

            @Override
            public void onNext(ScheduleResponse value) {

                if (value != null) {
                    if (isVisibleView()) {
                        List<SubjectModel> subjectModelList = value.getSubjectListFromDb();
                        view.updateSubjectList(subjectModelList);
                        subjectModelList = subjectModelList;
                        //view.updateSchedule(value);
                        view.stopProgressBar();

                        firstStartOpenDialog(sharedPreferences);
                    }
                }
            }


            @Override
            public void onCompleted() {
                //Log.d("onComplete", "times");
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
        Object observable = new Object();
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

    private void openDialog(){

        dialogCreator.createDialog();

    }

    private void firstStartOpenDialog(SharedPreferences pref){

        if(pref.getBoolean("firststart", true)){
            SharedPreferences.Editor editor = pref.edit();
            editor.putBoolean("firststart", false);
            editor.apply();

            openDialog();
        }
    }
}
