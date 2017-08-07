package com.envy.studapp.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.util.ArrayMap;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.allattentionhere.fabulousfilter.AAH_FabulousFragment;
import com.envy.studapp.Dagger.Schedule.Injection.DaggerScheduleComponent;
import com.envy.studapp.Dagger.Schedule.Module.AppModule;
import com.envy.studapp.Dagger.Schedule.Module.DBModule;
import com.envy.studapp.Filter.Data.FilterKey;
import com.envy.studapp.R;
import com.envy.studapp.Adapter.ScheduleRecycleViewAdapter;
import com.envy.studapp.Schedule.Data.Model.SubjectModel;
import com.envy.studapp.Schedule.Domain.ScheduleResponse;
import com.envy.studapp.Schedule.Presentation.SchedulePresenter;
import com.envy.studapp.Schedule.Presentation.ScheduleView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ScheduleFragment extends Fragment implements ScheduleView, AAH_FabulousFragment.Callbacks {


    static final String ARGUMENT_PAGE_NUMBER = "arg_page_number";

    @Inject
    SchedulePresenter schedulePresenter;

    @BindView(R.id.schedule_rv)
    RecyclerView rvSchedule;

    @BindView(R.id.schedule_progress_bar)
    ProgressBar progressBar;

    List<SubjectModel> fullSubjectModelList;

    @BindView(R.id.fab_filter)
    FloatingActionButton fabFilter;

    ScheduleRecycleViewAdapter adapter;

    FilterKey filterKey;

    private List<SubjectModel> subjectModelList;

    private ArrayMap<String, List<String>> appliedFilters = new ArrayMap<>();

    public ScheduleFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerScheduleComponent.builder().appModule(new AppModule(getContext()))
                .dBModule(new DBModule(getContext())).build().inject(this);
        Log.d("fragment", "onCreate");

    }

    public static Fragment newInstance()
    {
        ScheduleFragment scheduleFragment = new ScheduleFragment();
        return scheduleFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        Log.d("fragment", "onCreateView");

        View view = inflater.inflate(R.layout.fragment_schedule, container, false);

        schedulePresenter.onCreateView(this, null);

        ButterKnife.bind(this, view);

        showProgressBar();

        LinearLayoutManager llm = new LinearLayoutManager(this.getContext());
        rvSchedule.setLayoutManager(llm);

        adapter = new ScheduleRecycleViewAdapter(getContext(), subjectModelList);

        adapter.setSubjectList(subjectModelList);

        rvSchedule.setAdapter(adapter);

        fabFilter.setOnClickListener(v -> {
            FilterFabFragment dialogFrag = FilterFabFragment.newInstance();
            dialogFrag.setParentFab(fabFilter);
            dialogFrag.show(getFragmentManager(), dialogFrag.getTag());
        });

        return view;
    }

    @Override
    public void onResult(Object result) {
        Log.d("k9res", "onResult: " + result.toString());
        if (result.toString().equalsIgnoreCase("swiped_down")) {
            //do something or nothing
        } else {
            if (result != null) {
                fullSubjectModelList.addAll(subjectModelList);
                ArrayMap<String, List<String>> appliedFilters = (ArrayMap<String, List<String>>) result;
                if (appliedFilters.size() != 0) {
                    List<SubjectModel> filteredList = subjectModelList;
                    //iterate over arraymap
                    for (Map.Entry<String, List<String>> entry : appliedFilters.entrySet()) {
                        Log.d("k9res", "entry.key: " + entry.getKey());
                        switch (entry.getKey()) {
                            case "teacher":
                                filteredList = filterKey.getTeacherFilteredSubjectList(entry.getValue(), filteredList);
                                break;
                            case "weekday":
                                filteredList = filterKey.getWeekdayFilteredSubjectList(entry.getValue(), filteredList);
                                break;
                            case "group":
                                filteredList = filterKey.getGroupNumFilteredSubjectList(entry.getValue(), filteredList);
                                break;
                        }
                    }
                    Log.d("k9res", "new size: " + filteredList.size());
                    subjectModelList.clear();
                    subjectModelList.addAll(filteredList);
                    adapter.notifyDataSetChanged();

                } else {
                    subjectModelList.addAll(fullSubjectModelList);
                    adapter.notifyDataSetChanged();
                }
            }
            //handle result
        }
    }

    /*public ArrayMap<String, List<String>> getApplied_filters() {
        return appliedFilters;
    }*/

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
    public void showProgressBar() {
        progressBar.setVisibility(ProgressBar.VISIBLE);
    }

    @Override
    public void stopProgressBar() {
        progressBar.setVisibility(ProgressBar.INVISIBLE);
    }

    @Override
    public int getPageNum() {
        return getArguments().getInt(ARGUMENT_PAGE_NUMBER);
    }


    @Override
    public void setSubjectList(List<SubjectModel> subjectList) {
        this.subjectModelList = subjectList;
        adapter.notifyDataSetChanged();
        adapter.setSubjectList(subjectList);
    }
}
