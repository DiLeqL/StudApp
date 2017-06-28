package com.envy.studapp.Schedule.Data.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ENVY on 27.06.2017.
 */

public class ScheduleDBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Schedule.db";

    public ScheduleDBHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(ScheduleContract.SubjectEntry.SQL_CREATE_SUBJECT_TABLE);
        db.execSQL(ScheduleContract.TeachersEntry.SQL_CREATE_TEACHER_TABLE);
        db.execSQL(ScheduleContract.GroupsEntry.SQL_CREATE_GROUP_TABLE);
        db.execSQL(ScheduleContract.ClassroomsEntry.SQL_CREATE_CLASSROOM_TABLE);
        db.execSQL(ScheduleContract.BeginningTimesEntry.SQL_CREATE_BEGINNIG_TIMES_TABLE);
        db.execSQL(ScheduleContract.WeekdaysEntry.SQL_CREATE_WEEKDAYS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(ScheduleContract.SubjectEntry.SQL_DELETE_SCHEDULE_TABLE);
        db.execSQL(ScheduleContract.TeachersEntry.SQL_DELETE_TEACHER_TABLE);
        db.execSQL(ScheduleContract.GroupsEntry.SQL_DELETE_GROUP_TABLE);
        db.execSQL(ScheduleContract.ClassroomsEntry.SQL_DELETE_CLASSROOM_TABLE);
        db.execSQL(ScheduleContract.BeginningTimesEntry.SQL_DELETE_BEGINNING_TIMES_TABLE);
        db.execSQL(ScheduleContract.WeekdaysEntry.SQL_DELETE_WEEKDAYS_TABLE);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
