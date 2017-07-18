package com.envy.studapp.Dagger.Schedule.Injection;

import com.envy.studapp.Dagger.Schedule.Module.AppModule;
import com.envy.studapp.Dagger.Schedule.Module.DBModule;
import com.envy.studapp.Dagger.Schedule.Module.ScheduleModule;
import com.envy.studapp.MainActivity;
import com.envy.studapp.Dagger.Schedule.Module.NetWorkModule;
import com.envy.studapp.Schedule.Data.DataBase.ScheduleSQLBrite;
import com.envy.studapp.Schedule.Domain.ScheduleDownloaderUseCase;
import com.envy.studapp.Schedule.Fragment.ScheduleFragment;
import com.envy.studapp.Schedule.Presentation.SchedulePresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by ENVY on 08.06.2017.
 */

@Singleton
@Component(modules = {NetWorkModule.class, ScheduleModule.class, AppModule.class, DBModule.class})
public interface ScheduleComponent {

    void inject(ScheduleFragment fragment);
}
