package com.envy.studapp.Schedule.Presentation;

import com.envy.studapp.Schedule.Data.ScheduleResponse;

/**
 * Created by ENVY on 14.06.2017.
 */

public interface ScheduleView {

    void showError();

    void showResult();

    void updateSchedule(ScheduleResponse scheduleResponse);
}
