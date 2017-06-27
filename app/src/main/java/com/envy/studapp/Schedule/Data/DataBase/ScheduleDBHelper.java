package com.envy.studapp.Schedule.Data.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ENVY on 27.06.2017.
 */

public class ScheduleDBHelper extends SQLiteOpenHelper {

    public ScheduleDBHelper(Context context) {

        super(context, "myDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        /*final String SQL_CREATE_TODO_TABLE = "CREATE TABLE " + TodoListContract.TodoEntry.TABLE_NAME + " (" +
                TodoListContract.TodoEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                TodoListContract.TodoEntry.COLUMN_DATE + " INTEGER, " +
                TodoListContract.TodoEntry.COLUMN_DESCRIPTION + " TEXT NOT NULL, " +
                TodoListContract.TodoEntry.COLUMN_DONE + " INTEGER, " +
                TodoListContract.TodoEntry.COLUMN_DUE_DATE_TEXT + " INTEGER, " +
                "UNIQUE (" + TodoListContract.TodoEntry.COLUMN_DESCRIPTION + ", " + TodoListContract.TodoEntry.COLUMN_DATE + ") ON " +
                "CONFLICT IGNORE" +
                " );";*/

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
