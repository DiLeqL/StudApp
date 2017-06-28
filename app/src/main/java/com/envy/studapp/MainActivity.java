package com.envy.studapp;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.envy.studapp.Dagger.Schedule.Injection.DaggerScheduleComponent;
import com.envy.studapp.Schedule.Data.DataBase.ScheduleContract;
import com.envy.studapp.Schedule.Data.ScheduleResponse;
import com.envy.studapp.Schedule.Domain.ScheduleDownloaderUseCase;
import com.envy.studapp.Dagger.Schedule.Injection.ScheduleComponent;
import com.envy.studapp.Schedule.Data.HttpAPIInterface.StudServiceAPI;
import com.envy.studapp.Schedule.Fragment.ScheduleFragment;
import com.envy.studapp.Schedule.Presentation.SchedulePresenter;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    Observable<ScheduleResponse> observable;
    Observer<ScheduleResponse> subscriber;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //nav menu
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        Log.d("sql", ScheduleContract.SubjectEntry.SQL_CREATE_SUBJECT_TABLE);
        Log.d("sql", ScheduleContract.TeachersEntry.SQL_CREATE_TEACHER_TABLE);
        Log.d("sql", ScheduleContract.GroupsEntry.SQL_CREATE_GROUP_TABLE);
        Log.d("sql", ScheduleContract.ClassroomsEntry.SQL_CREATE_CLASSROOM_TABLE);
        Log.d("sql", ScheduleContract.BeginningTimesEntry.SQL_CREATE_BEGINNIG_TIMES_TABLE);
        Log.d("sql", ScheduleContract.WeekdaysEntry.SQL_CREATE_WEEKDAYS_TABLE);
        showFragment(ScheduleFragment.class);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        item.setChecked(true);

        Class fragment = null;

        if (id == R.id.nav_schedule) {

            showFragment(ScheduleFragment.class);

        } else if (id == R.id.nav_teacher_schedule){

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    private void showFragment(Class fragmentClass) {
        Fragment fragment = null;
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
    }
}
