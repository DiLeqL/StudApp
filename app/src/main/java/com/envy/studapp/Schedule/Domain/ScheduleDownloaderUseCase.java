package com.envy.studapp.Schedule.Domain;


import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.envy.studapp.BaseUseCase;
import com.envy.studapp.Schedule.Data.DataBase.ScheduleSQLBrite;
import com.envy.studapp.Schedule.Data.HttpAPIInterface.StudServiceAPI;


import javax.inject.Inject;

import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.functions.Action1;
import rx.functions.Func1;


public class ScheduleDownloaderUseCase extends BaseUseCase<ScheduleResponse,
                                               Object>{

    StudServiceAPI studServiceAPI;

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
                    scheduleSQLBrite.updateScheduleDB(scheduleResponse)
            ).flatMap(new Func1<ScheduleResponse, Observable<ScheduleResponse>>() {
                @Override
                public Observable<ScheduleResponse> call(ScheduleResponse scheduleResponse) {
                    return scheduleSQLBrite.getFromDatabaseObservable();
                }
            });
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
