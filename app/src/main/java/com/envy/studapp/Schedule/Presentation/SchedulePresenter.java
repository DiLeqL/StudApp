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
import com.envy.studapp.Schedule.ScheduleCalendarManager;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;

public class SchedulePresenter extends BasePresenter<ScheduleView> {

    private ScheduleDownloaderUseCase scheduleDownloaderUseCase;

    private ScheduleFromDbUseCase scheduleFromDbUseCase;

    private ConnectivityManager connectivityManager;

    private DialogCreator dialogCreator;

    private String SAVED_GROUP = "saved group";

    private SharedPreferences sharedPreferences;

    private ScheduleCalendarManager calendarManager;

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
                        subjectModelList = filterScheduleByDay(subjectModelList);
                        List<SubjectModel> filteredList = filterScheduleByGroup(subjectModelList,
                                sharedPreferences.getString(SAVED_GROUP, ""));
                        view.updateSubjectList(filterScheduleByDay(filteredList));
                        view.stopProgressBar();
                        firstStartOpenDialog(sharedPreferences);
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
        Log.d("save", sharedPreferences.getString(SAVED_GROUP, "no save"));
        if (sharedPreferences.contains(SAVED_GROUP)){
            for (SubjectModel subject: subjectModelList) {
                if (subject.getSubjectStudGroup().equals(sharedPreferences.getString(SAVED_GROUP, ""))){
                    Log.d("save", sharedPreferences.getString(SAVED_GROUP, ""));
                    filteredList.add(subject);
                    Log.d("filteredSubjects", subject.getSubjectName());
                }
            }
        }
        else {
            for (SubjectModel subject: subjectModelList) {
                if (subject.getSubjectStudGroup().equals(selectedGroup)){
                    filteredList.add(subject);
                    Log.d("filteredSubjects", subject.getSubjectName());
                }
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

    public void saveGroup(String selectedGroup){

        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (sharedPreferences.contains(SAVED_GROUP)){
            editor.remove(SAVED_GROUP);
        }
        editor.putString(SAVED_GROUP, selectedGroup);
        editor.apply();
    }

    private void firstStartOpenDialog(SharedPreferences pref){

        //openDialog();

        if (!pref.contains(SAVED_GROUP)){
            openDialog();
        }
    }
}
