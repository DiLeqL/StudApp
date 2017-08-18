package com.envy.studapp.DataBase;


import android.content.ContentValues;
import android.database.Cursor;
import android.support.annotation.NonNull;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class ScheduleSQLBrite {

    // private List<SubjectModel> subjectModelList;

    private ContentValues contentValues;

    private BriteDatabase briteDatabase;

    public ScheduleSQLBrite(BriteDatabase briteDatabase,
                            ContentValues contentValues) {
        this.briteDatabase = briteDatabase;
        this.contentValues = contentValues;
    }

    private void addTeachers(ScheduleResponse scheduleResponse) {
        List<TeacherModel> teacherModelList = scheduleResponse.getTeacherList();

        for (int i = 0; i < teacherModelList.size(); i++) {
            contentValues.clear();
            contentValues.put(ScheduleContract.TeachersEntry.COLUMN_TEACHER_ID,
                    teacherModelList.get(i).getTeacherId());
            contentValues.put(ScheduleContract.TeachersEntry.COLUMN_TEACHER_NAME,
                    teacherModelList.get(i).getTeacherName());
            briteDatabase.insert(ScheduleContract.TeachersEntry.TABLE_TEACHERS, contentValues);
        }
        Log.d("insert", "insert teachers done");
    }

    private void addSubjects(ScheduleResponse scheduleResponse) {
        List<SubjectModel> subjectModelList = scheduleResponse.getSubjectList();
        for (int i = 0; i < subjectModelList.size(); i++) {
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
            contentValues.put(ScheduleContract.SubjectEntry.COLUMN_SUBJECT_NUMERATOR,
                    subjectModelList.get(i).getNumerator());
            briteDatabase.insert(ScheduleContract.SubjectEntry.TABLE_SUBJECTS, contentValues);
        }
        Log.d("insert", "insert subjects done");
    }

    private void addGroupNums(ScheduleResponse scheduleResponse) {
        List<GroupNumModel> groupNumModelList = scheduleResponse.getGroupNumList();

        for (int i = 0; i < groupNumModelList.size(); i++) {
            contentValues.clear();
            contentValues.put(ScheduleContract.GroupsEntry.COLUMN_GROUP_ID,
                    groupNumModelList.get(i).getGroupId());
            contentValues.put(ScheduleContract.GroupsEntry.COLUMN_GROUP_NAME,
                    groupNumModelList.get(i).getGroupName());
            briteDatabase.insert(ScheduleContract.GroupsEntry.TABLE_GROUPS, contentValues);
        }
        Log.d("insert", "insert groups done");
    }

    private void addClassrooms(ScheduleResponse scheduleResponse) {
        List<ClassroomModel> classroomModelList = scheduleResponse.getClassroomList();

        for (int i = 0; i < classroomModelList.size(); i++) {

            contentValues.clear();
            contentValues.put(ScheduleContract.ClassroomsEntry.COLUMN_CLASSROOM_ID,
                    classroomModelList.get(i).getRoomId());
            contentValues.put(ScheduleContract.ClassroomsEntry.COLUMN_CLASSROOM_NUM,
                    classroomModelList.get(i).getRoomNum());
            briteDatabase.insert(ScheduleContract.ClassroomsEntry.TABLE_CLASSROOMS, contentValues);
        }
        Log.d("insert", "insert classrooms done");
    }

    private void addBeginningTimes(ScheduleResponse scheduleResponse) {
        List<BeginningTimeModel> beginningTimeModelList = scheduleResponse.getBeginningTimeList();

        for (int i = 0; i < beginningTimeModelList.size(); i++) {

            contentValues.clear();
            contentValues.put(ScheduleContract.BeginningTimesEntry.COLUMN_TIME_ID,
                    beginningTimeModelList.get(i).getTimeId());
            contentValues.put(ScheduleContract.BeginningTimesEntry.COLUMN_TIME,
                    beginningTimeModelList.get(i).getTime());
            briteDatabase.insert(ScheduleContract.BeginningTimesEntry.TABLE_BEGINNING_TIMES, contentValues);
        }
        Log.d("insert", "insert times done");
    }

    private void addWeekdays(ScheduleResponse scheduleResponse) {
        List<WeekdayModel> weekdayModelList = scheduleResponse.getWeekdayList();

        for (int i = 0; i < weekdayModelList.size(); i++) {

            contentValues.clear();
            contentValues.put(ScheduleContract.WeekdaysEntry.COLUMN_WEEKDAY_ID,
                    weekdayModelList.get(i).getWeekdayId());
            contentValues.put(ScheduleContract.WeekdaysEntry.COLUMN_DAY,
                    weekdayModelList.get(i).getDay());
            briteDatabase.insert(ScheduleContract.WeekdaysEntry.TABLE_WEEKDAYS, contentValues);
        }
        Log.d("insert", "insert days done");
    }

    public Observable<ScheduleResponse> getFromDatabaseObservable(String queryToDb) {
        Observable<SqlBrite.Query> subjects = briteDatabase.createQuery(
                ScheduleContract.SubjectEntry.TABLE_SUBJECTS,
                queryToDb);

        return subjects.map(query -> {
            Cursor cursor = query.run();
            ScheduleResponse scheduleResponse = new ScheduleResponse();
            scheduleResponse.setSubjectListFromDb(getSubjectModelList(cursor));
            return scheduleResponse;
        });
    }

    public Observable<List<String>> getFilterKeyTeacherListObservable(){
        Observable<SqlBrite.Query> teachers = briteDatabase.createQuery(
                ScheduleContract.TeachersEntry.TABLE_TEACHERS,
                ScheduleContract.TeachersEntry.SQL_SELECT_ALL_TEACHERS
        );

        return teachers.map(query -> {
            Cursor cursor = query.run();
            List<String> teacherKeyList = getTeacherKeyList(cursor);
            //filterKey.setTeacherKeyList(getTeacherKeyList(cursor));
            Collections.sort(teacherKeyList);
            return teacherKeyList;
        });
    }

    public Observable<List<String>> getFilterKeyWeekdayListObservable(){
        Observable<SqlBrite.Query> weekdays = briteDatabase.createQuery(
                ScheduleContract.WeekdaysEntry.TABLE_WEEKDAYS,
                ScheduleContract.WeekdaysEntry.SQL_SELECT_ALL_WEEKDAYS
        );

        return weekdays.map(query -> {
            Cursor cursor = query.run();
            return getWeekdayKeyList(cursor);
            //filterKey.setWeekdayKeyList(getWeekdayKeyList(cursor));
            //return weekdayKeyList;
        });
    }

    public Observable<List<String>> getFilterKeyGroupNumListObservable(){
        Observable<SqlBrite.Query> groups = briteDatabase.createQuery(
                ScheduleContract.GroupsEntry.TABLE_GROUPS,
                ScheduleContract.GroupsEntry.SQL_SELECT_ALL_GROUPS
        );

        return groups.map(query -> {
            Cursor cursor = query.run();
            List<String> groupNumKeyList = getGroupNumKeyList(cursor);
            //filterKey.setGroupNumKeyList(getGroupNumKeyList(cursor));
            Collections.sort(groupNumKeyList);
            return groupNumKeyList;
        });
    }


    public boolean checkAvailabilityRecords () {
        Observable<SqlBrite.Query> subjects = briteDatabase.createQuery(
                ScheduleContract.SubjectEntry.TABLE_SUBJECTS,
                ScheduleContract.SubjectEntry.SQL_SELECT_NUMERATOR_SUBJECTS);
        Cursor cursor = subjects.toBlocking().first().run();
        return cursor != null && cursor.getCount() > 0;
    }

    @NonNull
    private static List<String> getGroupNumKeyList(Cursor cursor){
        List<String> groupNumKeyList = new ArrayList<>();

        if (cursor != null) {
            cursor.getCount();
            try {
                if (cursor.moveToFirst()) {
                    do {
                        String groupNum;
                        groupNum = cursor.getString(cursor.getColumnIndex(
                                ScheduleContract.GroupsEntry.COLUMN_GROUP_NAME));
                        groupNumKeyList.add(groupNum);
                    } while (cursor.moveToNext());
                }
            } finally {
                cursor.close();
            }
        } else {
            Log.d("db", "Cursor is null");
        }
        /*ScheduleResponse scheduleResponse = new ScheduleResponse();
        scheduleResponse.setSubjectListFromDb(subjectModelList);
        return scheduleResponse;*/
        return groupNumKeyList;
    }
    @NonNull
    private static List<String> getWeekdayKeyList(Cursor cursor){

        List<String> weekdayKeyList = new ArrayList<>();

        if (cursor != null) {
            cursor.getCount();
            try {
                if (cursor.moveToFirst()) {
                    do {
                        String weekDay;
                        weekDay = cursor.getString(cursor.getColumnIndex(
                                ScheduleContract.WeekdaysEntry.COLUMN_DAY));
                        weekdayKeyList.add(weekDay);
                    } while (cursor.moveToNext());
                }
            } finally {
                cursor.close();
            }
        } else {
            Log.d("db", "Cursor is null");
        }
        /*ScheduleResponse scheduleResponse = new ScheduleResponse();
        scheduleResponse.setSubjectListFromDb(subjectModelList);
        return scheduleResponse;*/
        return weekdayKeyList;
    }

    @NonNull
    private static List<String> getTeacherKeyList(Cursor cursor){

        List<String> teacherKeyList = new ArrayList<>();

        if (cursor != null) {
            cursor.getCount();
            try {
                if (cursor.moveToFirst()) {
                    do {
                        String teacherName;
                        teacherName = cursor.getString(cursor.getColumnIndex(
                                ScheduleContract.TeachersEntry.COLUMN_TEACHER_NAME));
                        teacherKeyList.add(teacherName);
                    } while (cursor.moveToNext());
                }
            } finally {
                cursor.close();
            }
        } else {
            Log.d("db", "Cursor is null");
        }
        /*ScheduleResponse scheduleResponse = new ScheduleResponse();
        scheduleResponse.setSubjectListFromDb(subjectModelList);
        return scheduleResponse;*/
        return teacherKeyList;
    }

    @NonNull
    private static List<SubjectModel> getSubjectModelList(Cursor cursor) {

        List<SubjectModel> subjectModelList = new ArrayList<>();

        if (cursor != null) {
            cursor.getCount();
            try {
                String str = "";
                if (cursor.moveToFirst()) {
                    do {
                        String subjectID = "";
                        String weekDay = "";
                        String subjectName = "";
                        String teacherName = "";
                        String groupName = "";
                        String roomNum = "";
                        String time = "";
                        String numerator = "";

                        for (String columnName : cursor.getColumnNames()) {
                            switch (columnName) {
                                case "subjectID":
                                    subjectID = cursor
                                            .getString(cursor.getColumnIndex(columnName));
                                    break;
                                case "day":
                                    weekDay = cursor
                                            .getString(cursor.getColumnIndex(columnName));
                                    break;
                                case "subjectName":
                                    subjectName = cursor
                                            .getString(cursor.getColumnIndex(columnName));
                                    break;
                                case "name":
                                    teacherName = cursor
                                            .getString(cursor.getColumnIndex(columnName));
                                    break;
                                case "groupName":
                                    groupName = cursor
                                            .getString(cursor.getColumnIndex(columnName));
                                    break;
                                case "roomNum":
                                    roomNum = cursor
                                            .getString(cursor.getColumnIndex(columnName));
                                    break;
                                case "time":
                                    time = cursor
                                            .getString(cursor.getColumnIndex(columnName));
                                    break;
                                case "subjectNumerator":
                                    numerator = cursor.getString(cursor.getColumnIndex(columnName));
                            }
                            str = str.concat(columnName + " = " +
                                    cursor.getString(cursor.getColumnIndex(columnName)) + "; ");
                        }
                        subjectModelList.add(new SubjectModel(subjectID, weekDay,
                                subjectName, teacherName, groupName, roomNum, time, numerator));
                        str = str.concat("\n");
                    } while (cursor.moveToNext());
                }
            } finally {
                cursor.close();
            }
        } else {
            Log.d("db", "Cursor is null");
        }
        /*ScheduleResponse scheduleResponse = new ScheduleResponse();
        scheduleResponse.setSubjectListFromDb(subjectModelList);
        return scheduleResponse;*/
        return subjectModelList;
    }


    private void resetDB() {
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


    public void updateScheduleDB(ScheduleResponse scheduleResponse) {
        Observable<ScheduleResponse> scheduleResponseObservable = Observable.just(scheduleResponse);
        scheduleResponseObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(scheduleResponseFromServer -> {
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
                });
    }

}
