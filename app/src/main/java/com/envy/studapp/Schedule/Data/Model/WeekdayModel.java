package com.envy.studapp.Schedule.Data.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ENVY on 15.07.2017.
 */

public class WeekdayModel {

    @SerializedName("weekdayId")
    int weekdayId;

    @SerializedName("day")
    String day;

    public int getWeekdayId() {
        return weekdayId;
    }

    public void setWeekdayId(int weekdayId) {
        this.weekdayId = weekdayId;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
