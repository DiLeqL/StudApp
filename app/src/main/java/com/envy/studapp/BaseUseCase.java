package com.envy.studapp;

import io.reactivex.Scheduler;

/**
 * Created by ENVY on 13.06.2017.
 */

public abstract class BaseUseCase {

    Scheduler backgroundScheduler;
    Scheduler mainScheduler;

    public BaseUseCase(){
        
    }
}
