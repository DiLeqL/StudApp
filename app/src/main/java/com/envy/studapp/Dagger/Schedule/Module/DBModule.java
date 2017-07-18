package com.envy.studapp.Dagger.Schedule.Module;

import android.content.ContentValues;
import android.content.Context;

import com.envy.studapp.Schedule.Data.DataBase.ScheduleDBHelper;
import com.envy.studapp.Schedule.Data.DataBase.ScheduleSQLBrite;
import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.SqlBrite;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;

/**
 * Created by ENVY on 10.07.2017.
 */

@Module
public class DBModule {

    Context context;

    public DBModule(Context context){
        this.context = context;
    }

    @Provides
    @Singleton
    public BriteDatabase provideBriteDatabase(@Named("backgroundScheduler") Scheduler backgroundScheduler){
        SqlBrite sqlBrite = new SqlBrite.Builder().build();
        ScheduleDBHelper scheduleDBHelper = new ScheduleDBHelper(context);
        BriteDatabase db = sqlBrite.wrapDatabaseHelper(scheduleDBHelper, backgroundScheduler);
        return db;
    }

    @Provides
    @Singleton
    public ContentValues provideContentValues(){
        return new ContentValues();
    }


    @Provides
    @Singleton
    public ScheduleSQLBrite provideScheduleSQLBrite(BriteDatabase briteDatabase,
                                                    ContentValues contentValues){
        return new ScheduleSQLBrite(briteDatabase, contentValues);
    }

}
