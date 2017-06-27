package com.envy.studapp.Schedule.Data.DataBase;

import android.provider.BaseColumns;

/**
 * Created by ENVY on 27.06.2017.
 */

public class ScheduleContract {

    public static final class SubjectEntry {

        public static final String TABLE_SCHEDULE ="schedule";

        public static final String COLUMN_SUBJECT_ID ="subjectID";
        public static final String COLUMN_SUBJECT_NAME ="subjectName";
        public static final String COLUMN_SUBJECT_TEACHER = "subjectTeacher";
        public static final String COLUMN_SUBJECT_TIME = "subjectTime";
        public static final String COLUMN_SUBJECT_ROOM = "subjectRoom";
        public static final String COLUMN_SUBJECT_STUD_GROUP = "subjectStudGroup";
    }

    public static final class TeachersEntry {
        
    }


}
