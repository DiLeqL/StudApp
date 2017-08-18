package com.envy.studapp.DataBase;

import android.provider.BaseColumns;


public class ScheduleContract {

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ", ";
    private static final String DOT_SEP = ".";

    public static final class SubjectEntry implements BaseColumns{

        static final String TABLE_SUBJECTS ="subjects";

        static final String COLUMN_SUBJECT_ID = "subjectID";
        static final String COLUMN_SUBJECT_DAY = "subjectDay";
        static final String COLUMN_SUBJECT_NAME = "subjectName";
        static final String COLUMN_SUBJECT_TEACHER = "subjectTeacher";
        static final String COLUMN_SUBJECT_TIME = "subjectTime";
        static final String COLUMN_SUBJECT_ROOM = "subjectRoom";
        static final String COLUMN_SUBJECT_STUD_GROUP = "subjectStudGroup";
        static final String COLUMN_SUBJECT_NUMERATOR = "subjectNumerator";

        static final String SQL_CREATE_SUBJECT_TABLE =
         "CREATE TABLE " + TABLE_SUBJECTS + " (" + _ID + " INTEGER PRIMARY KEY, " +
              COLUMN_SUBJECT_ID + TEXT_TYPE + COMMA_SEP + COLUMN_SUBJECT_DAY +
                 TEXT_TYPE + COMMA_SEP + COLUMN_SUBJECT_NAME + TEXT_TYPE + COMMA_SEP +
                 COLUMN_SUBJECT_TEACHER + TEXT_TYPE + COMMA_SEP + COLUMN_SUBJECT_TIME +
                 TEXT_TYPE + COMMA_SEP + COLUMN_SUBJECT_ROOM + TEXT_TYPE + COMMA_SEP +
                 COLUMN_SUBJECT_STUD_GROUP + TEXT_TYPE + COMMA_SEP + COLUMN_SUBJECT_NUMERATOR
                 + TEXT_TYPE + ")";

        static final String SQL_DELETE_SUBJECT_TABLE =
         "DROP TABLE IF EXISTS " + TABLE_SUBJECTS;


        public static final String SQL_SELECT_NUMERATOR_SUBJECTS = "SELECT " + COLUMN_SUBJECT_ID + COMMA_SEP +
                WeekdaysEntry.COLUMN_DAY + COMMA_SEP + COLUMN_SUBJECT_NAME + COMMA_SEP +
                TeachersEntry.COLUMN_TEACHER_NAME + COMMA_SEP + GroupsEntry.COLUMN_GROUP_NAME +
                COMMA_SEP + ClassroomsEntry.COLUMN_CLASSROOM_NUM + COMMA_SEP +
                BeginningTimesEntry.COLUMN_TIME + COMMA_SEP + COLUMN_SUBJECT_NUMERATOR + " FROM " +
                TABLE_SUBJECTS + " INNER JOIN " +
                TeachersEntry.TABLE_TEACHERS + " ON " + TABLE_SUBJECTS + DOT_SEP +
                COLUMN_SUBJECT_TEACHER + " = " + TeachersEntry.TABLE_TEACHERS + DOT_SEP +
                TeachersEntry.COLUMN_TEACHER_ID + " INNER JOIN " + GroupsEntry.TABLE_GROUPS +
                " ON " + TABLE_SUBJECTS + DOT_SEP + COLUMN_SUBJECT_STUD_GROUP + " = " +
                GroupsEntry.TABLE_GROUPS + DOT_SEP + GroupsEntry.COLUMN_GROUP_ID + " INNER JOIN " +
                ClassroomsEntry.TABLE_CLASSROOMS + " ON " + TABLE_SUBJECTS + DOT_SEP +
                COLUMN_SUBJECT_ROOM + " = " + ClassroomsEntry.TABLE_CLASSROOMS + DOT_SEP +
                ClassroomsEntry.COLUMN_CLASSROOM_ID + " INNER JOIN " +
                BeginningTimesEntry.TABLE_BEGINNING_TIMES + " ON " + TABLE_SUBJECTS + DOT_SEP +
                COLUMN_SUBJECT_TIME + " = " + BeginningTimesEntry.TABLE_BEGINNING_TIMES + DOT_SEP +
                BeginningTimesEntry.COLUMN_TIME_ID + " INNER JOIN " + WeekdaysEntry.TABLE_WEEKDAYS +
                " ON " + TABLE_SUBJECTS + DOT_SEP + COLUMN_SUBJECT_DAY + " = " +
                WeekdaysEntry.TABLE_WEEKDAYS + DOT_SEP + WeekdaysEntry.COLUMN_WEEKDAY_ID +
                " WHERE " + COLUMN_SUBJECT_NUMERATOR + " = " + "\"true\"";

        public static final String SQL_SELECT_NOT_NUMENATOR_SUBJECTSS = "SELECT " + COLUMN_SUBJECT_ID + COMMA_SEP +
                WeekdaysEntry.COLUMN_DAY + COMMA_SEP + COLUMN_SUBJECT_NAME + COMMA_SEP +
                TeachersEntry.COLUMN_TEACHER_NAME + COMMA_SEP + GroupsEntry.COLUMN_GROUP_NAME +
                COMMA_SEP + ClassroomsEntry.COLUMN_CLASSROOM_NUM + COMMA_SEP +
                BeginningTimesEntry.COLUMN_TIME + COMMA_SEP + COLUMN_SUBJECT_NUMERATOR + " FROM " +
                TABLE_SUBJECTS + " INNER JOIN " +
                TeachersEntry.TABLE_TEACHERS + " ON " + TABLE_SUBJECTS + DOT_SEP +
                COLUMN_SUBJECT_TEACHER + " = " + TeachersEntry.TABLE_TEACHERS + DOT_SEP +
                TeachersEntry.COLUMN_TEACHER_ID + " INNER JOIN " + GroupsEntry.TABLE_GROUPS +
                " ON " + TABLE_SUBJECTS + DOT_SEP + COLUMN_SUBJECT_STUD_GROUP + " = " +
                GroupsEntry.TABLE_GROUPS + DOT_SEP + GroupsEntry.COLUMN_GROUP_ID + " INNER JOIN " +
                ClassroomsEntry.TABLE_CLASSROOMS + " ON " + TABLE_SUBJECTS + DOT_SEP +
                COLUMN_SUBJECT_ROOM + " = " + ClassroomsEntry.TABLE_CLASSROOMS + DOT_SEP +
                ClassroomsEntry.COLUMN_CLASSROOM_ID + " INNER JOIN " +
                BeginningTimesEntry.TABLE_BEGINNING_TIMES + " ON " + TABLE_SUBJECTS + DOT_SEP +
                COLUMN_SUBJECT_TIME + " = " + BeginningTimesEntry.TABLE_BEGINNING_TIMES + DOT_SEP +
                BeginningTimesEntry.COLUMN_TIME_ID + " INNER JOIN " + WeekdaysEntry.TABLE_WEEKDAYS +
                " ON " + TABLE_SUBJECTS + DOT_SEP + COLUMN_SUBJECT_DAY + " = " +
                WeekdaysEntry.TABLE_WEEKDAYS + DOT_SEP + WeekdaysEntry.COLUMN_WEEKDAY_ID +
                " WHERE " + COLUMN_SUBJECT_NUMERATOR + " = " + "\"false\"";
    }

    static final class TeachersEntry implements BaseColumns{

        static final String TABLE_TEACHERS = "teachers";

        static final String COLUMN_TEACHER_ID = "teacherID";
        static final String COLUMN_TEACHER_NAME = "name";

        static final String SQL_SELECT_ALL_TEACHERS = "SELECT " + COLUMN_TEACHER_NAME +
         " FROM " + TABLE_TEACHERS;

        static final String SQL_CREATE_TEACHER_TABLE =
         "CREATE TABLE " + TABLE_TEACHERS + " (" + _ID + " INTEGER PRIMARY KEY, " +
                 COLUMN_TEACHER_ID + TEXT_TYPE + COMMA_SEP + COLUMN_TEACHER_NAME +
                 TEXT_TYPE + ")";

        static final String SQL_DELETE_TEACHER_TABLE =
                "DROP TABLE IF EXISTS " + TABLE_TEACHERS;
    }

    static final class GroupsEntry implements BaseColumns{

        static final String TABLE_GROUPS = "groups";

        static final String COLUMN_GROUP_ID = "groupID";
        static final String COLUMN_GROUP_NAME = "groupName";

        static final String SQL_SELECT_ALL_GROUPS = "SELECT " + COLUMN_GROUP_NAME +
         " FROM " + TABLE_GROUPS;

        static final String SQL_CREATE_GROUP_TABLE =
         "CREATE TABLE " + TABLE_GROUPS + " (" + _ID + " INTEGER PRIMARY KEY, " +
                 COLUMN_GROUP_ID + TEXT_TYPE + COMMA_SEP + COLUMN_GROUP_NAME +
                 TEXT_TYPE + ")";

        static final String SQL_DELETE_GROUP_TABLE =
                "DROP TABLE IF EXISTS " + TABLE_GROUPS;
    }

    static final class ClassroomsEntry implements BaseColumns{

        static final String TABLE_CLASSROOMS = "classrooms";

        static final String COLUMN_CLASSROOM_ID = "roomID";
        static final String COLUMN_CLASSROOM_NUM = "roomNum";

        static final String SQL_CREATE_CLASSROOM_TABLE =
         "CREATE TABLE " + TABLE_CLASSROOMS + " (" + _ID + " INTEGER PRIMARY KEY, " +
                 COLUMN_CLASSROOM_ID + TEXT_TYPE + COMMA_SEP + COLUMN_CLASSROOM_NUM +
                 TEXT_TYPE + ")";

        static final String SQL_DELETE_CLASSROOM_TABLE =
                "DROP TABLE IF EXISTS " + TABLE_CLASSROOMS;
    }

    static final class BeginningTimesEntry implements BaseColumns{

        static final String TABLE_BEGINNING_TIMES = "beginningTimes";

        static final String COLUMN_TIME_ID = "timeID";
        static final String COLUMN_TIME = "time";

        static final String SQL_CREATE_BEGINNIG_TIMES_TABLE =
         "CREATE TABLE " + TABLE_BEGINNING_TIMES + " (" + _ID + " INTEGER PRIMARY KEY, " +
                 COLUMN_TIME_ID + TEXT_TYPE + COMMA_SEP + COLUMN_TIME +
                 TEXT_TYPE + ")";

        static final String SQL_DELETE_BEGINNING_TIMES_TABLE =
                "DROP TABLE IF EXISTS " + TABLE_BEGINNING_TIMES;
    }

    static final class WeekdaysEntry implements BaseColumns{

        static final String TABLE_WEEKDAYS = "weekdays";

        static final String COLUMN_WEEKDAY_ID = "weekdayID";
        static final String COLUMN_DAY = "day";

        static final String SQL_SELECT_ALL_WEEKDAYS = "SELECT " + WeekdaysEntry.COLUMN_DAY + " FROM " + WeekdaysEntry.TABLE_WEEKDAYS;

        static final String SQL_CREATE_WEEKDAYS_TABLE = "CREATE TABLE " + TABLE_WEEKDAYS + " (" + _ID + " INTEGER PRIMARY KEY, " +
                COLUMN_WEEKDAY_ID + TEXT_TYPE + COMMA_SEP + COLUMN_DAY +
                TEXT_TYPE + ")";

        static final String SQL_DELETE_WEEKDAYS_TABLE =
                "DROP TABLE IF EXISTS " + TABLE_WEEKDAYS;
    }

}
