package com.envy.studapp.Schedule.Fragment;


import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.envy.studapp.Dagger.Schedule.Injection.DaggerScheduleComponent;
import com.envy.studapp.MainActivity;
import com.envy.studapp.R;
import com.envy.studapp.Schedule.Data.DataBase.ScheduleDBHelper;
import com.envy.studapp.Schedule.Data.ScheduleResponse;
import com.envy.studapp.Schedule.Presentation.SchedulePresenter;
import com.envy.studapp.Schedule.Presentation.ScheduleView;
import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.SqlBrite;

import javax.inject.Inject;

import rx.schedulers.Schedulers;


public class ScheduleFragment extends Fragment implements ScheduleView{

    @Inject
    SchedulePresenter schedulePresenter;

    private static Context context;

    public ScheduleFragment() {
        // Required empty public constructor
    }

    public static ScheduleFragment newInstance()
    {
        ScheduleFragment myFragment = new ScheduleFragment();
        return myFragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ScheduleFragment.context = getContext();

        DaggerScheduleComponent.builder().build().inject(this);

        schedulePresenter.getScheduleResponse();
        //Log.d("response", scheduleResponse.toString());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_schedule, container, false);
        //TextView textView = (TextView) view.findViewById(R.id.textView2);
        //textView.setText(schedulePresenter.getScheduleResponse().toString());

        schedulePresenter.onCreateView(this, null);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        schedulePresenter.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        schedulePresenter.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        schedulePresenter.onDestroyView();
    }

    @Override
    public void showError() {

    }

    @Override
    public void showResult() {

    }

    @Override
    public void updateSchedule(ScheduleResponse scheduleResponse) {
        scheduleResponse.getTeacherNames();
    }

    public static Context getFragmentContext() {
        return ScheduleFragment.context;
    }
}
