package com.dateflow;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.TimeZone;

public class DateFlow {

    private static final String TIME_ZONE = "UTC";

    Instant instant;
    Clock clock;
    TimeZone timeZone;

    private DateFlow() {
        this.clock = Clock.fixed(Instant.now(), ZoneId.of(TIME_ZONE));
        this.timeZone = TimeZone.getTimeZone(TIME_ZONE);
        this.instant = Instant.now(clock);
    }

    public static DateFlow now() {
        return new DateFlow();
    }

    public static DateFlow from(LocalDate localDate) {
        DateFlow dateFlow = new DateFlow();
        dateFlow.instant = localDate
                .atStartOfDay()
                .atZone(dateFlow.clock.getZone())
                .toInstant();
        return dateFlow;
    }

    public static DateFlow from(LocalDateTime localDateTime) {
        DateFlow dateFlow = new DateFlow();
        dateFlow.instant = localDateTime
                .atZone(dateFlow.clock.getZone())
                .toInstant();
        return dateFlow;
    }

    public static DateFlow from(Date date) {
        DateFlow dateFlow = new DateFlow();
        dateFlow.instant = ZonedDateTime
                .ofInstant(date.toInstant(), dateFlow.clock.getZone())
                .toInstant();
        return dateFlow;
    }

    public static DateFlow from(long millis) {
        DateFlow dateFlow = new DateFlow();
        dateFlow.instant = Instant
                .ofEpochMilli(millis)
                .atZone(dateFlow.clock.getZone())
                .toInstant();
        return dateFlow;
    }

    public DateFlow resetMidnightTime() {
        this.instant = this.instant
                .atZone(clock.getZone())
                .truncatedTo(ChronoUnit.DAYS)
                .toInstant();
        return this;
    }

    public DateFlow resetTimeToLastSecondOfDay() {
        this.instant = this.instant.atZone(clock.getZone())
                .withHour(23)
                .withMinute(59)
                .withSecond(59)
                .toInstant();
        return this;
    }

    public DateFlow addMonths(int months) {
        this.instant = this.instant
                .atZone(clock.getZone())
                .plusMonths(months)
                .toInstant();
        return this;
    }

    public DateFlow addDays(int days) {
        this.instant = this.instant
                .atZone(clock.getZone())
                .plusDays(days)
                .toInstant();
        return this;
    }
}
