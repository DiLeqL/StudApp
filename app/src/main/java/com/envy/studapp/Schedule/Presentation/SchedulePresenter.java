package com.envy.studapp.Schedule.Presentation;

import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.envy.studapp.BasePresenter;
import com.envy.studapp.FirstLaunch.GroupSelectEvent;
import com.envy.studapp.Schedule.Data.Model.SubjectModel;
import com.envy.studapp.Schedule.Domain.ScheduleFromDbUseCase;
import com.envy.studapp.Schedule.Domain.ScheduleResponse;
import com.envy.studapp.Schedule.Domain.ScheduleDownloaderUseCase;
import com.envy.studapp.Schedule.ScheduleCalendarManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;
import rx.subjects.Subject;

public class SchedulePresenter extends BasePresenter<ScheduleView> {

    private ScheduleDownloaderUseCase scheduleDownloaderUseCase;

    private ScheduleFromDbUseCase scheduleFromDbUseCase;

    private ConnectivityManager connectivityManager;

    private DialogCreator dialogCreator;

    private SharedPreferences sharedPreferences;

    private ScheduleCalendarManager calendarManager;

    private boolean isFirstLaunch = true;

    //CompositeSubscription compositeSubscription;

    public SchedulePresenter(ScheduleDownloaderUseCase scheduleDownloaderUseCase,
                             ScheduleFromDbUseCase scheduleFromDbUseCase, ConnectivityManager cm,
                             DialogCreator dialogCreator, SharedPreferences sharedPreferences,
                             ScheduleCalendarManager scheduleCalendarManager) {
        this.scheduleDownloaderUseCase = scheduleDownloaderUseCase;
        this.scheduleFromDbUseCase = scheduleFromDbUseCase;
        this.connectivityManager = cm;
        this.dialogCreator = dialogCreator;
        this.sharedPreferences = sharedPreferences;
        this.calendarManager = scheduleCalendarManager;
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
                        view.updateSubjectList(filterScheduleByDay(subjectModelList));
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

    private List<SubjectModel> filterScheduleByDay(List<SubjectModel> subjectList){

        List<SubjectModel> filteredList = new ArrayList<>();

        for (SubjectModel subject: subjectList) {
            if (subject.getSubjectDay().equals(calendarManager.getCurrentDay())){
                filteredList.add(subject);
                Log.d("filteredSubjectsByDay", subject.getSubjectName());
            }
        }
        return filteredList;
    }

    public List<SubjectModel> filterScheduleByGroup(List<SubjectModel> subjectModelList,
                                                    String selectedGroup){
        List<SubjectModel> filteredList = new ArrayList<>();

        for (SubjectModel subject: subjectModelList) {
            if (subject.getSubjectStudGroup().equals(selectedGroup)){
                filteredList.add(subject);
                Log.d("filteredSubjects", subject.getSubjectName());
            }
        }
        return filteredList;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //compositeSubscription.unsubscribe();
    }

    private void openDialog(){

        dialogCreator.createDialog();

    }

    public String getCurrentDay(){
        return calendarManager.getCurrentDay();
    }

    public String getCurrentWeek(){
        if (calendarManager.isNumerator()){
            return "Numerator";
        }
        else{
            return "Not numerator";
        }
    }

    private void firstStartOpenDialog(SharedPreferences pref){

        openDialog();
        /*if(pref.getBoolean("firststart", true)){
            SharedPreferences.Editor editor = pref.edit();
            editor.putBoolean("firststart", false);
            editor.apply();

            openDialog();
        }*/
    }
}
