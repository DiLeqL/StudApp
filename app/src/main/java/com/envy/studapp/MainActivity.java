package com.envy.studapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.envy.studapp.Schedule.Domain.ReadingData;
import com.envy.studapp.HttpAPIInterface.DaggerInjectorInterface;
import com.envy.studapp.HttpAPIInterface.InjectorInterface;
import com.envy.studapp.HttpAPIInterface.StudServiceAPI;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    StudServiceAPI studServiceAPI;

    InjectorInterface injectorInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Subject subject = new Subject();
        subject.getSchedule();*/

        DaggerInjectorInterface.builder().build().inject(this);

        ReadingData readingData = new ReadingData(studServiceAPI);

        readingData.getTeacher();
    }
}
