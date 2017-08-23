package com.envy.studapp.Dagger.Schedule.Injection;

import com.envy.studapp.Dagger.Schedule.Module.AppModule;
import com.envy.studapp.Dagger.Schedule.Module.DBModule;
import com.envy.studapp.Dagger.Schedule.Module.FilterModule;
import com.envy.studapp.Dagger.Schedule.Module.NetWorkModule;
import com.envy.studapp.Dagger.Schedule.Module.ScheduleModule;
import com.envy.studapp.Fragment.FilterFabFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, ScheduleModule.class, DBModule.class,
        FilterModule.class})
public interface FilterComponent {

    void inject(FilterFabFragment fragment);
}
