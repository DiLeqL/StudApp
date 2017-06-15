package com.envy.studapp.Schedule.Presentation;

import com.envy.studapp.Schedule.Data.ScheduleResponse;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by ENVY on 14.06.2017.
 */

public class SchedulePresenter extends BasePresenter{

    ScheduleResponse scheduleResponse;

    public Observer<ScheduleResponse> getScheduleObserver(){
        Observer<ScheduleResponse> scheduleResponseObserver = new Observer<ScheduleResponse>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ScheduleResponse value) {
                scheduleResponse = value;
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
        return scheduleResponseObserver;
    }

    public ScheduleResponse getScheduleResponse(){
        return scheduleResponse;
    }

}
