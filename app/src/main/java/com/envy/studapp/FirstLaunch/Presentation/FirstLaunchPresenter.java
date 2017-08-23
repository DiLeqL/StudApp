package com.envy.studapp.FirstLaunch.Presentation;

import android.os.Bundle;

import com.envy.studapp.BasePresenter;
import com.envy.studapp.FirstLaunch.Domain.FirstLaunchUseCase;
import com.envy.studapp.Schedule.Domain.ScheduleResponse;
import com.envy.studapp.Schedule.Presentation.ScheduleView;

import java.util.List;

import javax.inject.Inject;

import rx.Observer;

/**
 * Created by ENVY on 22.08.2017.
 */

public class FirstLaunchPresenter extends BasePresenter<FirstLaunchView>{

    FirstLaunchUseCase firstLaunchUseCase;

    public FirstLaunchPresenter(FirstLaunchUseCase firstLaunchUseCase){
        this.firstLaunchUseCase = firstLaunchUseCase;
    }


    public Observer<List<String>> getGroupListObserver(){
        return new Observer<List<String>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<String> groupList) {
                view.updateGroupList(groupList);
            }
        };
    }

    @Override
    public void onCreateView(FirstLaunchView view, Bundle savedInstanceState) {
        super.onCreateView(view, savedInstanceState);
        Object observable = new Object();
        Observer<List<String>> subscriber = getGroupListObserver();
        firstLaunchUseCase.subscribe(observable, subscriber);
    }
}
