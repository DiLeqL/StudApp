package com.envy.studapp.Schedule.Data.DataBase;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.envy.studapp.Schedule.Data.ScheduleResponse;
import com.envy.studapp.Schedule.Data.SubjectModel;
import com.envy.studapp.Schedule.Data.TeacherModel;
import com.envy.studapp.Schedule.Fragment.ScheduleFragment;
import com.google.gson.annotations.SerializedName;
import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.SqlBrite;

import java.util.List;

import rx.Observable;
import rx.functions.Action1;
import rx.schedulers.Schedulers;


public class ScheduleSQLBrite {

    @SerializedName("subjects")
    List<SubjectModel> responseSubjectModelList;

    List<SubjectModel> dbSubjectModelList;

    SQLiteDatabase sqLiteDatabase;

    private Cursor cursor;

    BriteDatabase briteDatabase;



    public ScheduleSQLBrite(){
        this.briteDatabase = createBriteDatabase();
    }


    private BriteDatabase createBriteDatabase(){
        SqlBrite sqlBrite = new SqlBrite.Builder().build();
        ScheduleDBHelper scheduleDBHelper = new ScheduleDBHelper(ScheduleFragment.getFragmentContext());
        BriteDatabase db = sqlBrite.wrapDatabaseHelper(scheduleDBHelper, Schedulers.io());
        return db;
    }

    private void addTeachers(ScheduleResponse scheduleResponse){
        List<TeacherModel> teacherModelList = scheduleResponse.getTeacherList();

        for (int i = 0; i < teacherModelList.size(); i++) {
            ContentValues contentValues = new ContentValues();
            contentValues.clear();
            contentValues.put("teacherId", teacherModelList.get(i).getTeacherId());
            contentValues.put("name", teacherModelList.get(i).getTeacherName());
            briteDatabase.insert(ScheduleContract.TeachersEntry.TABLE_TEACHERS, contentValues);
        }
        Log.d("insert", "insert done");
    }


    public List<SubjectModel> getSubjectModelList(){
        //TODO change query
        Observable<SqlBrite.Query> subjects = briteDatabase.createQuery("users", "SELECT * FROM users");
        subjects.subscribe(new Action1<SqlBrite.Query>() {
            @Override public void call(SqlBrite.Query query) {
                cursor = query.run();
                if (cursor != null) {
                    try {
                        String str = "";
                        if (cursor.moveToFirst()) {
                            do {
                                for (String columnName : cursor.getColumnNames()) {
                                    str = str.concat(columnName + " = " +
                                            cursor.getString(cursor.getColumnIndex(columnName)) + "; ");
                                }
                                Log.d("bd", str);
                            } while (cursor.moveToNext());
                        }
                    } finally {
                        cursor.close();
                    }
                } else
                    Log.d("db", "Cursor is null");
            }
        });
        return dbSubjectModelList;
    }

    public void getTeacherModelList(){
        //TODO change query
        Observable<SqlBrite.Query> subjects = briteDatabase
                .createQuery("teachers", "SELECT * FROM teachers");
        subjects.subscribe(new Action1<SqlBrite.Query>() {
            @Override public void call(SqlBrite.Query query) {
                cursor = query.run();
                if (cursor != null) {
                    try {
                        String str = "";
                        if (cursor.moveToFirst()) {
                            do {
                                for (String columnName : cursor.getColumnNames()) {
                                    str = str.concat(columnName + " = " +
                                            cursor.getString(cursor.getColumnIndex(columnName)) + "; ");
                                }
                                Log.d("bd", str);
                            } while (cursor.moveToNext());
                        }
                    } finally {
                        cursor.close();
                    }
                } else
                    Log.d("db", "Cursor is null");
            }
        });
    }


    private void resetDB(){
        briteDatabase.execute(ScheduleContract.SubjectEntry.SQL_DELETE_SCHEDULE_TABLE);
        briteDatabase.execute(ScheduleContract.TeachersEntry.SQL_DELETE_TEACHER_TABLE);
        briteDatabase.execute(ScheduleContract.GroupsEntry.SQL_DELETE_GROUP_TABLE);
        briteDatabase.execute(ScheduleContract.ClassroomsEntry.SQL_DELETE_CLASSROOM_TABLE);
        briteDatabase.execute(ScheduleContract.BeginningTimesEntry.SQL_DELETE_BEGINNING_TIMES_TABLE);
        briteDatabase.execute(ScheduleContract.WeekdaysEntry.SQL_DELETE_WEEKDAYS_TABLE);

        briteDatabase.execute(ScheduleContract.SubjectEntry.SQL_CREATE_SUBJECT_TABLE);
        briteDatabase.execute(ScheduleContract.TeachersEntry.SQL_CREATE_TEACHER_TABLE);
        briteDatabase.execute(ScheduleContract.GroupsEntry.SQL_CREATE_GROUP_TABLE);
        briteDatabase.execute(ScheduleContract.ClassroomsEntry.SQL_CREATE_CLASSROOM_TABLE);
        briteDatabase.execute(ScheduleContract.BeginningTimesEntry.SQL_CREATE_BEGINNIG_TIMES_TABLE);
        briteDatabase.execute(ScheduleContract.WeekdaysEntry.SQL_CREATE_WEEKDAYS_TABLE);
    }

    public void updateScheduleDB(ScheduleResponse scheduleResponseFromServer){
        BriteDatabase.Transaction transaction = briteDatabase.newTransaction();
        try {
            resetDB();
            addTeachers(scheduleResponseFromServer);
            transaction.markSuccessful();
        } finally {
            transaction.end();
        }
    }

}
