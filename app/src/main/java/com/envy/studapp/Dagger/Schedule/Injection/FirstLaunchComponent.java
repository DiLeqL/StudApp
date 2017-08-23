package com.envy.studapp.Dagger.Schedule.Injection;

import com.envy.studapp.Dagger.Schedule.Module.AppModule;
import com.envy.studapp.Dagger.Schedule.Module.DBModule;
import com.envy.studapp.Dagger.Schedule.Module.FirstLaunchModule;
import com.envy.studapp.Dagger.Schedule.Module.ScheduleModule;
import com.envy.studapp.Fragment.FirstLaunchFragment;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = {AppModule.class, FirstLaunchModule.class, DBModule.class})
public interface FirstLaunchComponent {

    void inject(FirstLaunchFragment fragment);
}
