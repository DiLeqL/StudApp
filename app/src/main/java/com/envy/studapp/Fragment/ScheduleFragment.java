package com.envy.studapp.Fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.envy.studapp.Dagger.Schedule.Injection.DaggerScheduleComponent;
import com.envy.studapp.Dagger.Schedule.Module.AppModule;
import com.envy.studapp.Dagger.Schedule.Module.DBModule;
import com.envy.studapp.R;
import com.envy.studapp.Adapter.ScheduleRecycleViewAdapter;
import com.envy.studapp.Schedule.Data.Model.SubjectModel;
import com.envy.studapp.Schedule.Domain.ScheduleResponse;
import com.envy.studapp.Schedule.Presentation.SchedulePresenter;
import com.envy.studapp.Schedule.Presentation.ScheduleView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ScheduleFragment extends Fragment implements ScheduleView{

    @Inject
    SchedulePresenter schedulePresenter;

    @BindView(R.id.schedule_rv)
    RecyclerView rvSchedule;

    private List<SubjectModel> subjectModelList;

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

        DaggerScheduleComponent.builder().appModule(new AppModule(getContext()))
                .dBModule(new DBModule(getContext())).build().inject(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_schedule, container, false);

        schedulePresenter.onCreateView(this, null);

        ButterKnife.bind(this, view);

        LinearLayoutManager llm = new LinearLayoutManager(this.getContext());
        rvSchedule.setLayoutManager(llm);
        //TODO second param should use from presenter and provide correct list
       // ScheduleRecycleViewAdapter adapter = new ScheduleRecycleViewAdapter(
        //        getContext(), subjectModelList);
        //rvSchedule.setAdapter(adapter);
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
