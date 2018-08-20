package com.learn.chapter.twelve;

import java.time.DayOfWeek;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalField;

public class SelfInterface implements TemportalAdjuster {
    @Override
    public Temporal adjustInto(Temporal temporal) {
        DayOfWeek dayOfWeek = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
        int today = 1;
        if(dayOfWeek == DayOfWeek.FRIDAY) today=3;
        else if(dayOfWeek == DayOfWeek.SATURDAY) today=2;
        return temporal.plus(today, ChronoUnit.DAYS);
    }
}
