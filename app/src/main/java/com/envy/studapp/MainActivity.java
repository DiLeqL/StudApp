package com.envy.studapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.envy.studapp.Dagger.Schedule.Injection.DaggerScheduleComponent;
import com.envy.studapp.Schedule.Domain.ScheduleDownloaderUseCase;
import com.envy.studapp.Dagger.Schedule.Injection.ScheduleComponent;
import com.envy.studapp.Schedule.Data.HttpAPIInterface.StudServiceAPI;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    ScheduleDownloaderUseCase scheduleDownloaderUseCase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DaggerScheduleComponent.builder().build().inject(this);

        scheduleDownloaderUseCase.getTeacher();
    }
}
