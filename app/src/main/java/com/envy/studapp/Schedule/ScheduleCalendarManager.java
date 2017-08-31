package com.envy.studapp.Schedule;

import android.util.Log;

import java.util.Calendar;
import java.util.Date;


public class ScheduleCalendarManager {

    private Calendar calendar = Calendar.getInstance();

    private int getWeekNumber(){
        Date currentDate = new Date();
        calendar.setTime(currentDate);
        Log.d("weeknum", Integer.toString(calendar.get(Calendar.WEEK_OF_YEAR)));
        return calendar.get(Calendar.WEEK_OF_YEAR);
    }

    public boolean isNumerator(){
        return getWeekNumber() % 2 == 1;
    }

    public String getNumeratorAsString(){
        if (isNumerator()){
            return "true";
        }
        return "false";
    }

    public String getCurrentDay(){
        int day = calendar.get(Calendar.DAY_OF_WEEK);

        String currentDay = "";

        switch (day) {
            case Calendar.SUNDAY:
                currentDay = "Sunday";
                break;
            case Calendar.MONDAY:
                currentDay = "Monday";
                break;
            case Calendar.TUESDAY:
                currentDay = "Tuesday";
                break;
            case Calendar.WEDNESDAY:
                currentDay = "Wednesday";
                break;
            case Calendar.THURSDAY:
                currentDay = "Thursday";
                break;
            case Calendar.FRIDAY:
                currentDay = "Friday";
                break;
            case Calendar.SATURDAY:
                currentDay = "Saturday";
                break;
        }
        return currentDay;
    }
}
