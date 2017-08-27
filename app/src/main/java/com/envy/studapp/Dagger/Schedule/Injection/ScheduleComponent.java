package com.envy.studapp.Dagger.Schedule.Injection;

import com.envy.studapp.Dagger.Schedule.Module.AppModule;
import com.envy.studapp.Dagger.Schedule.Module.DBModule;
import com.envy.studapp.Dagger.Schedule.Module.FilterModule;
import com.envy.studapp.Dagger.Schedule.Module.ScheduleModule;
import com.envy.studapp.Dagger.Schedule.Module.NetWorkModule;
import com.envy.studapp.Fragment.FilterFabFragment;
import com.envy.studapp.Fragment.ScheduleFragment;
import com.envy.studapp.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by ENVY on 08.06.2017.
 */

@Singleton
@Component(modules = {NetWorkModule.class, ScheduleModule.class, AppModule.class, DBModule.class,
        FilterModule.class})
public interface ScheduleComponent {

    void inject(ScheduleFragment fragment);

    void inject(MainActivity activity);

    //void inject(FilterFabFragment fragment);
}
