package com.envy.studapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.envy.studapp.HTTPAPIInterface.DaggerInjectorInterface;
import com.envy.studapp.HTTPAPIInterface.InjectorInterface;
import com.envy.studapp.HTTPAPIInterface.StudServiceAPI;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    StudServiceAPI studServiceAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Subject subject = new Subject();
        subject.getSchedule();*/

        InjectorInterface.inject(this);

    }
}
