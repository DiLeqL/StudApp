package com.envy.studapp.Schedule.Presentation;

import android.app.Fragment;
import android.util.Log;

import com.envy.studapp.Schedule.Data.ScheduleResponse;
import com.envy.studapp.Schedule.Domain.ScheduleDownloaderUseCase;
import com.envy.studapp.Schedule.Fragment.ScheduleFragment;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by ENVY on 14.06.2017.
 */

public class SchedulePresenter extends BasePresenter<ScheduleView>{

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

                if (isVisibleView()){

                    view.updateSchedule(value);

                }

                //setScheduleResponse(value);
                //Log.d("onNext", scheduleResponse.toString());
                //scheduleResponse = value;
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

    public ScheduleResponse getScheduleResponse(){
        Observer<ScheduleResponse> subscriber = getScheduleObserver();
        scheduleDownloaderUseCase.subscribe(object, subscriber);
        return scheduleResponse;
    }

    public void setScheduleResponse(ScheduleResponse response){
        this.scheduleResponse = response;
    }

}
