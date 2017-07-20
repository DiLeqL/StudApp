package com.envy.studapp.Schedule.Domain;


import android.content.Context;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.envy.studapp.BaseUseCase;
import com.envy.studapp.Schedule.Data.DataBase.ScheduleSQLBrite;
import com.envy.studapp.Schedule.Data.HttpAPIInterface.StudServiceAPI;
import com.envy.studapp.Schedule.Data.Model.SubjectModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Scheduler;
import rx.functions.Action1;
import rx.functions.Func1;


public class ScheduleDownloaderUseCase extends BaseUseCase<ScheduleResponse,
                                               Object>{

    StudServiceAPI studServiceAPI;
    //Observer<ScheduleResponse> subscriber;
    Cursor cursor;

    ScheduleSQLBrite scheduleSQLBrite;

    ConnectivityManager connectivityManager;


    @Inject
    public ScheduleDownloaderUseCase(StudServiceAPI studServiceAPI, Scheduler backgroundScheduler,
                                     Scheduler uiScheduler, ScheduleSQLBrite scheduleSQLBrite,
                                     ConnectivityManager connectivityManager) {

        super(backgroundScheduler, uiScheduler);
        this.studServiceAPI = studServiceAPI;
        this.scheduleSQLBrite = scheduleSQLBrite;
        this.connectivityManager = connectivityManager;
    }


    @Override
    public Observable<ScheduleResponse> buildObservable(Object object) {

        if (isConnected()){
            return studServiceAPI.getSchedule().doOnNext(scheduleResponse ->
                    scheduleSQLBrite.updateScheduleDB(scheduleResponse));
        }
        else {
            if (scheduleSQLBrite.checkAvailabilityRecords()){
                return scheduleSQLBrite.getFromDatabaseObservable();
            }
            else{
                Log.d("connection", "Check connection or server problems");
                return null;
            }
        }
    }

    private boolean isConnected(){

        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        return activeNetwork.isConnectedOrConnecting();
    }


}
