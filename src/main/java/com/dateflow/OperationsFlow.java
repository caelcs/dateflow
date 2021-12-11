package com.dateflow;

import java.time.Instant;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.TimeZone;

import static com.dateflow.Constants.TIME_ZONE;

public class OperationsFlow {

    ZoneId zoneId;
    Instant instant;
    TimeZone timeZone;

    public OperationsFlow() {
        this.zoneId = ZoneId.of(TIME_ZONE);
        this.timeZone = TimeZone.getTimeZone(zoneId);
        this.instant = Instant.now().atZone(zoneId).toInstant();
    }

    public OperationsFlow resetMidnightTime() {
        instant = instant
                .atZone(zoneId)
                .truncatedTo(ChronoUnit.DAYS)
                .toInstant();
        return this;
    }

    public OperationsFlow resetTimeToLastSecondOfDay() {
        instant = instant.atZone(zoneId)
                .withHour(23)
                .withMinute(59)
                .withSecond(59)
                .toInstant();
        return this;
    }

    public OperationsFlow plusMonths(int months) {
        instant = instant
                .atZone(zoneId)
                .plusMonths(months)
                .toInstant();
        return this;
    }

    public OperationsFlow plusDays(int days) {
        instant = instant
                .atZone(zoneId)
                .plusDays(days)
                .toInstant();
        return this;
    }

    public OperationsFlow plusMinutes(int minutes) {
        instant = instant
                .atZone(zoneId)
                .plusMinutes(minutes)
                .toInstant();
        return this;
    }

    public OperationsFlow plusHours(int hours) {
        instant = instant
                .atZone(zoneId)
                .plusHours(hours)
                .toInstant();
        return this;
    }

    public OperationsFlow plusSeconds(int seconds) {
        instant = instant
                .atZone(zoneId)
                .plusSeconds(seconds)
                .toInstant();
        return this;
    }

    public OperationsFlow minusSeconds(int seconds) {
        instant = instant
                .atZone(zoneId)
                .minusSeconds(seconds)
                .toInstant();
        return this;
    }

    public OperationsFlow minusMinutes(int minutes) {
        instant = instant
                .atZone(zoneId)
                .minusMinutes(minutes)
                .toInstant();
        return this;
    }

    public OperationsFlow minusHours(int hours) {
        instant = instant
                .atZone(zoneId)
                .minusHours(hours)
                .toInstant();
        return this;
    }

    public OperationsFlow minusDays(int days) {
        instant = instant
                .atZone(zoneId)
                .minusDays(days)
                .toInstant();
        return this;
    }

    public OperationsFlow minusMonths(int months) {
        instant = instant
                .atZone(zoneId)
                .minusMonths(months)
                .toInstant();
        return this;
    }

    public TransformerFlow as() {
        return new TransformerFlow(zoneId, instant, timeZone);
    }
}
