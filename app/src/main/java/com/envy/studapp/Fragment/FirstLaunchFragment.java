package com.envy.studapp.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import com.envy.studapp.Adapter.GroupListAdapter;
import com.envy.studapp.Dagger.Schedule.Injection.DaggerFirstLaunchComponent;
import com.envy.studapp.Dagger.Schedule.Module.AppModule;
import com.envy.studapp.Dagger.Schedule.Module.DBModule;
import com.envy.studapp.Dagger.Schedule.Module.FirstLaunchModule;
import com.envy.studapp.FirstLaunch.Presentation.FirstLaunchPresenter;
import com.envy.studapp.FirstLaunch.Presentation.FirstLaunchView;
import com.envy.studapp.R;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FirstLaunchFragment extends DialogFragment implements FirstLaunchView{

    @BindView(R.id.spinner)
    Spinner spinner;

    @OnClick(R.id.button_accept)
    public void accept(){
        dismiss();
    }

    List<String> groupList;

    GroupListAdapter adapter;

    @Inject
    FirstLaunchPresenter firstLaunchPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //groupList = new ArrayList<>();

        DaggerFirstLaunchComponent.builder().appModule(new AppModule(getContext()))
                .firstLaunchModule(new FirstLaunchModule())
                .dBModule(new DBModule(getContext())).build().inject(this);

        //setStyle(DialogFragment.STYLE_NO_TITLE, R.style.MyDialog);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first_time_open, null);

        firstLaunchPresenter.onCreateView(this, savedInstanceState);

        ButterKnife.bind(this, view);

        adapter = new GroupListAdapter(getContext(), android.R.layout.simple_spinner_item, groupList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //adapter.setGroupList(groupList);
        spinner.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void updateGroupList(List<String> groupList) {
        this.groupList = groupList;
        adapter.setGroupList(groupList);
        adapter.notifyDataSetChanged();
        for (String group: groupList)  {
            Log.d("groups", group);
        }
    }
}
