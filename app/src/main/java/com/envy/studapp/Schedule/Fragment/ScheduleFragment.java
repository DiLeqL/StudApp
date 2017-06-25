package com.envy.studapp.Schedule.Fragment;


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
import com.envy.studapp.R;
import com.envy.studapp.Schedule.Data.ScheduleResponse;
import com.envy.studapp.Schedule.Presentation.SchedulePresenter;

import javax.inject.Inject;


public class ScheduleFragment extends Fragment {

    @Inject
    SchedulePresenter schedulePresenter;

    ScheduleResponse result;

    public ScheduleFragment() {
        // Required empty public constructor

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerScheduleComponent.builder().build().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        //Log.d("presenter", schedulePresenter.createRx().toString());

        View view = inflater.inflate(R.layout.fragment_schedule, container, false);
        TextView textView = (TextView) view.findViewById(R.id.textView2);
        textView.setText(schedulePresenter.getScheduleResponse().toString());
        return view;
    }

}
