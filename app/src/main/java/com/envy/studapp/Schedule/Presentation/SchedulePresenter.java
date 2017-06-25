package com.envy.studapp.Schedule.Presentation;

import android.util.Log;

import com.envy.studapp.Dagger.Schedule.Injection.DaggerScheduleComponent;
import com.envy.studapp.Schedule.Data.ScheduleResponse;
import com.envy.studapp.Schedule.Domain.ScheduleDownloaderUseCase;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by ENVY on 14.06.2017.
 */

public class SchedulePresenter extends BasePresenter{

    ScheduleResponse scheduleResponse;

    ScheduleDownloaderUseCase scheduleDownloaderUseCase;

    public SchedulePresenter(ScheduleDownloaderUseCase scheduleDownloaderUseCase){
        this.scheduleDownloaderUseCase = scheduleDownloaderUseCase;
    }

    Object object;

    public Observer<ScheduleResponse> getScheduleObserver(){
        Observer<ScheduleResponse> observer = new Observer<ScheduleResponse>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ScheduleResponse value) {
                if (value == null){
                    Log.d("val", "value is null");
                }
                else {
                    Log.d("val", value.toString());
                }
                scheduleResponse = value;
                //Log.d("onNext", scheduleResponse.toString());
            }



            @Override
            public void onError(Throwable e) {
                Log.d("val", "check connection");
            }

            @Override
            public void onComplete() {

            }
        };
        return observer;
    }

    public Observer<ScheduleResponse> createRx(){
        Observer<ScheduleResponse> subscriber = getScheduleObserver();
        scheduleDownloaderUseCase.subscribe(object, subscriber);
        return subscriber;
    }

    public ScheduleResponse getScheduleResponse(){

        return scheduleResponse;
    }

}
