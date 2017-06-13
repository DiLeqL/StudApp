package com.envy.studapp.Schedule.Domain;

import android.util.Log;

import com.envy.studapp.Schedule.Data.HttpAPIInterface.StudServiceAPI;
import com.envy.studapp.Schedule.Data.BeginningTimeModel;
import com.envy.studapp.Schedule.Data.ClassroomModel;
import com.envy.studapp.Schedule.Data.GroupModel;
import com.envy.studapp.Schedule.Data.SubjectModel;
import com.envy.studapp.Schedule.Data.TeacherModel;

import java.io.IOException;
import java.util.List;
import java.util.Observable;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


import static android.content.ContentValues.TAG;

/**
 * Created by ENVY on 08.06.2017.
 */


public class ScheduleDownloaderUseCase {

    StudServiceAPI studServiceAPI;

    io.reactivex.Observable<List<TeacherModel>> observable;
    List<TeacherModel> teacherModelList;

    @Named("backgroundScheduler") Scheduler backgroundScheduler;
    @Named("mainScheduler") Scheduler mainScheduler;

    @Inject
    public ScheduleDownloaderUseCase(StudServiceAPI studServiceAPI, Scheduler backgroundScheduler,
                                     Scheduler mainScheduler) {

        this.studServiceAPI = studServiceAPI;
        this.mainScheduler = mainScheduler;
        this.backgroundScheduler = backgroundScheduler;
    }

    public Observer<List<TeacherModel>> getObserver() {
        return new Observer<List<TeacherModel>>() {

            @Override
            public void onComplete() {

            }

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<TeacherModel> value) {
                teacherModelList = value;
            }

            @Override
            public void onError(Throwable e) {

            }

        };
    }

    public void getTeacher() {

        final io.reactivex.Observable<List<TeacherModel>> call = studServiceAPI.getTeacher();

        call.subscribeOn(backgroundScheduler)
            .observeOn(mainScheduler)
            .subscribe(getObserver());

    }



    public void getSubject() {

        final Call<SubjectModel> call = studServiceAPI.getSubject();

        call.enqueue(new Callback<SubjectModel>() {
            @Override
            public void onResponse(Call<SubjectModel> call, retrofit2.Response<SubjectModel> response) {

            }

            @Override
            public void onFailure(Call<SubjectModel> call, Throwable t) {
                Log.d("jsonLog", "failed");
            }
        });
    }

    public void getGroup() {

        final Call<GroupModel> call = studServiceAPI.getGroup();

        call.enqueue(new Callback<GroupModel>() {
            @Override
            public void onResponse(Call<GroupModel> call, Response<GroupModel> response) {

            }

            @Override
            public void onFailure(Call<GroupModel> call, Throwable t) {

            }
        });
    }

    public void getClassRoom() {

        final Call<ClassroomModel> call = studServiceAPI.getClassroom();

        call.enqueue(new Callback<ClassroomModel>() {

            @Override
            public void onResponse(Call<ClassroomModel> call, Response<ClassroomModel> response) {

            }

            @Override
            public void onFailure(Call<ClassroomModel> call, Throwable t) {

            }
        });
    }

    public void getBeginningTime() {

        final Call<BeginningTimeModel> call = studServiceAPI.getBeginningTime();

        call.enqueue(new Callback<BeginningTimeModel>() {
            @Override
            public void onResponse(Call<BeginningTimeModel> call, retrofit2.Response<BeginningTimeModel> response) {

            }

            @Override
            public void onFailure(Call<BeginningTimeModel> call, Throwable t) {
                Log.d("jsonLog", "failed");
            }
        });
    }

}
