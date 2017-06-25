package com.envy.studapp.Schedule.Domain;

import android.util.Log;

import com.envy.studapp.BaseUseCase;
import com.envy.studapp.Schedule.Data.HttpAPIInterface.StudServiceAPI;
import com.envy.studapp.Schedule.Data.BeginningTimeModel;
import com.envy.studapp.Schedule.Data.ClassroomModel;
import com.envy.studapp.Schedule.Data.GroupNumModel;
import com.envy.studapp.Schedule.Data.ScheduleResponse;
import com.envy.studapp.Schedule.Data.SubjectModel;
import com.envy.studapp.Schedule.Data.TeacherModel;
import com.envy.studapp.Schedule.Presentation.SchedulePresenter;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;




public class ScheduleDownloaderUseCase extends BaseUseCase<ScheduleResponse,
                                               Object>{

    StudServiceAPI studServiceAPI;
    //Observer<ScheduleResponse> subscriber;


    @Inject
    public ScheduleDownloaderUseCase(StudServiceAPI studServiceAPI, Scheduler backgroundScheduler,
                                     Scheduler uiScheduler) {

        super(backgroundScheduler, uiScheduler);
        this.studServiceAPI = studServiceAPI;
    }


    @Override
    public Observable<ScheduleResponse> buildObservable(Object object) {
        return studServiceAPI.getSchedule();
    }

}
