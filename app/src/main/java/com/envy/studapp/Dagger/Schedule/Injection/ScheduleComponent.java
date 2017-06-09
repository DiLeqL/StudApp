package com.envy.studapp.Dagger.Schedule.Injection;

import com.envy.studapp.MainActivity;
import com.envy.studapp.Dagger.Schedule.Module.NetWorkModule;
import com.envy.studapp.Schedule.Domain.ScheduleDownloaderUseCase;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by ENVY on 08.06.2017.
 */

@Singleton
@Component(modules = {NetWorkModule.class})
public interface ScheduleComponent {

    void inject(MainActivity activity);
}
