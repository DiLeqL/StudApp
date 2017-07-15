package com.envy.studapp.Schedule.Fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.envy.studapp.Dagger.Schedule.Injection.DaggerScheduleComponent;
import com.envy.studapp.Dagger.Schedule.Module.DBModule;
import com.envy.studapp.R;
import com.envy.studapp.Schedule.Domain.ScheduleResponse;
import com.envy.studapp.Schedule.Presentation.SchedulePresenter;
import com.envy.studapp.Schedule.Presentation.ScheduleView;

import javax.inject.Inject;


public class ScheduleFragment extends Fragment implements ScheduleView{

    @Inject
    SchedulePresenter schedulePresenter;

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

        DaggerScheduleComponent.builder().dBModule(new DBModule(getContext())).build().inject(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_schedule, container, false);

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
}
