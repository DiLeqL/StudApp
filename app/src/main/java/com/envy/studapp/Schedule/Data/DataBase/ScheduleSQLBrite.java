package com.envy.studapp.Schedule.Data.DataBase;


import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import com.envy.studapp.Schedule.Data.Model.BeginningTimeModel;
import com.envy.studapp.Schedule.Data.Model.ClassroomModel;
import com.envy.studapp.Schedule.Data.Model.GroupNumModel;
import com.envy.studapp.Schedule.Data.Model.WeekdayModel;
import com.envy.studapp.Schedule.Domain.ScheduleResponse;
import com.envy.studapp.Schedule.Data.Model.SubjectModel;
import com.envy.studapp.Schedule.Data.Model.TeacherModel;
import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.SqlBrite;

import java.util.List;

import rx.Observable;
import rx.functions.Action1;


public class ScheduleSQLBrite {

    List<SubjectModel> dbSubjectModelList;

    private Cursor cursor;

    BriteDatabase briteDatabase;


    public ScheduleSQLBrite(BriteDatabase briteDatabase){
        this.briteDatabase = briteDatabase;
    }

    private void addTeachers(ScheduleResponse scheduleResponse){
        List<TeacherModel> teacherModelList = scheduleResponse.getTeacherList();

        for (int i = 0; i < teacherModelList.size(); i++) {
            ContentValues contentValues = new ContentValues();
            contentValues.clear();
            contentValues.put(ScheduleContract.TeachersEntry.COLUMN_TEACHER_ID,
                    teacherModelList.get(i).getTeacherId());
            contentValues.put(ScheduleContract.TeachersEntry.COLUMN_TEACHER_NAME,
                    teacherModelList.get(i).getTeacherName());
            briteDatabase.insert(ScheduleContract.TeachersEntry.TABLE_TEACHERS, contentValues);
        }
        Log.d("insert", "insert teachers done");
    }

    private void addSubjects(ScheduleResponse scheduleResponse){
        List<SubjectModel> subjectModelList = scheduleResponse.getSubjectList();
        //TODO provide content values?
        for (int i = 0; i < subjectModelList.size(); i++) {
            ContentValues contentValues = new ContentValues();
            contentValues.clear();
            contentValues.put(ScheduleContract.SubjectEntry.COLUMN_SUBJECT_ID,
                    subjectModelList.get(i).getSubjectId());
            contentValues.put(ScheduleContract.SubjectEntry.COLUMN_SUBJECT_DAY,
                    subjectModelList.get(i).getSubjectDay());
            contentValues.put(ScheduleContract.SubjectEntry.COLUMN_SUBJECT_NAME,
                    subjectModelList.get(i).getSubjectName());
            contentValues.put(ScheduleContract.SubjectEntry.COLUMN_SUBJECT_TEACHER,
                    subjectModelList.get(i).getSubjectTeacher());
            contentValues.put(ScheduleContract.SubjectEntry.COLUMN_SUBJECT_TIME,
                    subjectModelList.get(i).getSubjectTime());
            contentValues.put(ScheduleContract.SubjectEntry.COLUMN_SUBJECT_ROOM,
                    subjectModelList.get(i).getSubjectRoom());
            contentValues.put(ScheduleContract.SubjectEntry.COLUMN_SUBJECT_STUD_GROUP,
                    subjectModelList.get(i).getSubjectStudGroup());
            briteDatabase.insert(ScheduleContract.SubjectEntry.TABLE_SUBJECTS, contentValues);
        }
        Log.d("insert", "insert subjects done");
    }

    private void addGroupNums(ScheduleResponse scheduleResponse){
        List<GroupNumModel> groupNumModelList = scheduleResponse.getGroupNumList();

        for (int i = 0; i < groupNumModelList.size(); i++) {
            ContentValues contentValues = new ContentValues();
            contentValues.clear();
            contentValues.put(ScheduleContract.GroupsEntry.COLUMN_GROUP_ID,
                    groupNumModelList.get(i).getGroupId());
            contentValues.put(ScheduleContract.GroupsEntry.COLUMN_GROUP_NAME,
                    groupNumModelList.get(i).getGroupName());
            briteDatabase.insert(ScheduleContract.GroupsEntry.TABLE_GROUPS, contentValues);
        }
        Log.d("insert", "insert groups done");
    }

    private void addClassrooms(ScheduleResponse scheduleResponse){
        List<ClassroomModel> classroomModelList = scheduleResponse.getClassroomList();

        for (int i = 0; i < classroomModelList.size(); i++) {
            ContentValues contentValues = new ContentValues();
            contentValues.clear();
            contentValues.put(ScheduleContract.ClassroomsEntry.COLUMN_CLASSROOM_ID,
                    classroomModelList.get(i).getRoomId());
            contentValues.put(ScheduleContract.ClassroomsEntry.COLUMN_CLASSROOM_NUM,
                    classroomModelList.get(i).getRoomNum());
            briteDatabase.insert(ScheduleContract.ClassroomsEntry.TABLE_CLASSROOMS, contentValues);
        }
        Log.d("insert", "insert classrooms done");
    }

    private void addBeginningTimes(ScheduleResponse scheduleResponse){
        List<BeginningTimeModel> beginningTimeModelList = scheduleResponse.getBeginningTimeList();

        for (int i = 0; i < beginningTimeModelList.size(); i++) {
            ContentValues contentValues = new ContentValues();
            contentValues.clear();
            contentValues.put(ScheduleContract.BeginningTimesEntry.COLUMN_TIME_ID,
                    beginningTimeModelList.get(i).getTimeId());
            contentValues.put(ScheduleContract.BeginningTimesEntry.COLUMN_TIME,
                    beginningTimeModelList.get(i).getTime());
            briteDatabase.insert(ScheduleContract.BeginningTimesEntry.TABLE_BEGINNING_TIMES, contentValues);
        }
        Log.d("insert", "insert times done");
    }

    private void addWeekdays(ScheduleResponse scheduleResponse){
        List<WeekdayModel> weekdayModelList = scheduleResponse.getWeekdayList();

        for (int i = 0; i < weekdayModelList.size(); i++) {
            ContentValues contentValues = new ContentValues();
            contentValues.clear();
            contentValues.put(ScheduleContract.WeekdaysEntry.COLUMN_WEEKDAY_ID,
                    weekdayModelList.get(i).getWeekdayId());
            contentValues.put(ScheduleContract.WeekdaysEntry.COLUMN_DAY,
                    weekdayModelList.get(i).getDay());
            briteDatabase.insert(ScheduleContract.WeekdaysEntry.TABLE_WEEKDAYS, contentValues);
        }
        Log.d("insert", "insert days done");
    }

    public void getSubjectModelList(){
        //TODO change query
        Observable<SqlBrite.Query> subjects = briteDatabase.createQuery("subjects", "SELECT * FROM subjects INNER JOIN" +
                "teachers ON ");
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
        //return dbSubjectModelList;
    }

    public void getTeacherModelList() {
        //TODO change query
        Observable<SqlBrite.Query> subjects = briteDatabase
                .createQuery("teachers", "SELECT * FROM teachers");
        subjects.subscribe(new Action1<SqlBrite.Query>() {
            @Override
            public void call(SqlBrite.Query query) {
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
        briteDatabase.execute(ScheduleContract.SubjectEntry.SQL_DELETE_SUBJECT_TABLE);
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
            addSubjects(scheduleResponseFromServer);
            addGroupNums(scheduleResponseFromServer);
            addClassrooms(scheduleResponseFromServer);
            addBeginningTimes(scheduleResponseFromServer);
            addWeekdays(scheduleResponseFromServer);
            transaction.markSuccessful();
        } finally {
            transaction.end();
        }
    }

}
