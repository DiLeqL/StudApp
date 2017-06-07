package com.envy.studapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.envy.studapp.Model.Schedule;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Schedule schedule = new Schedule();
        schedule.start();
    }
}
