package com.dateflow;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.TimeZone;

public class DateFlow {

    private static final String TIME_ZONE = "UTC";

    ZoneId zoneId;
    Instant instant;
    TimeZone timeZone;

    private DateFlow() {
        zoneId = ZoneId.of(TIME_ZONE);
        this.timeZone = TimeZone.getTimeZone(TIME_ZONE);
        this.instant = Instant.now().atZone(zoneId).toInstant();
    }

    public static DateFlow now() {
        return new DateFlow();
    }

    public static DateFlow from(LocalDate localDate) {
        DateFlow dateFlow = new DateFlow();
        dateFlow.instant = localDate
                .atStartOfDay()
                .atZone(dateFlow.zoneId)
                .toInstant();
        return dateFlow;
    }

    public static DateFlow from(LocalDateTime localDateTime) {
        DateFlow dateFlow = new DateFlow();
        dateFlow.instant = localDateTime
                .atZone(dateFlow.zoneId)
                .toInstant();
        return dateFlow;
    }

    public static DateFlow from(Date date) {
        DateFlow dateFlow = new DateFlow();
        dateFlow.instant = ZonedDateTime
                .ofInstant(date.toInstant(), dateFlow.zoneId)
                .toInstant();
        return dateFlow;
    }

    public static DateFlow from(long millis) {
        DateFlow dateFlow = new DateFlow();
        dateFlow.instant = Instant
                .ofEpochMilli(millis)
                .atZone(dateFlow.zoneId)
                .toInstant();
        return dateFlow;
    }

    public DateFlow resetMidnightTime() {
        this.instant = this.instant
                .atZone(zoneId)
                .truncatedTo(ChronoUnit.DAYS)
                .toInstant();
        return this;
    }

    public DateFlow resetTimeToLastSecondOfDay() {
        this.instant = this.instant.atZone(zoneId)
                .withHour(23)
                .withMinute(59)
                .withSecond(59)
                .toInstant();
        return this;
    }

    public DateFlow addMonths(int months) {
        this.instant = this.instant
                .atZone(zoneId)
                .plusMonths(months)
                .toInstant();
        return this;
    }

    public DateFlow addDays(int days) {
        this.instant = this.instant
                .atZone(zoneId)
                .plusDays(days)
                .toInstant();
        return this;
    }

    public DateFlow addMinutes(int minutes) {
        this.instant = this.instant
                .atZone(zoneId)
                .plusMinutes(minutes)
                .toInstant();
        return this;
    }

    public DateFlow addHours(int hours) {
        this.instant = this.instant
                .atZone(zoneId)
                .plusHours(hours)
                .toInstant();
        return this;
    }

    public DateFlow addSeconds(int seconds) {
        this.instant = this.instant
                .atZone(zoneId)
                .plusSeconds(seconds)
                .toInstant();
        return this;
    }
}
