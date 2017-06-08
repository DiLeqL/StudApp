package com.envy.studapp.HTTPAPIInterface;

import com.envy.studapp.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by ENVY on 08.06.2017.
 */


@Singleton
@Component(modules = {NetWorkModule.class})
public interface InjectorInterface {

    void inject(MainActivity activity);
}
