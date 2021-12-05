package com.dateflow;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.TimeZone;

public class DateFlow {

    static final String TIME_ZONE = "UTC";
    static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    static final String DATE_FORMAT_WITH_MILLIS = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    ZoneId zoneId;
    Instant instant;
    TimeZone timeZone;
    String dateFormat;

    private DateFlow() {
        this.zoneId = ZoneId.of(TIME_ZONE);
        this.timeZone = TimeZone.getTimeZone(zoneId);
        this.instant = Instant.now().atZone(zoneId).toInstant();
        this.dateFormat = DATE_FORMAT;
    }

    public static DateFlow now() {
        return new DateFlow();
    }

    public static DateFlow from(LocalDate localDate) {
        var dateFlow = new DateFlow();
        dateFlow.instant = localDate
                .atStartOfDay()
                .atZone(dateFlow.zoneId)
                .toInstant();
        return dateFlow;
    }

    public static DateFlow from(LocalDateTime localDateTime) {
        var dateFlow = new DateFlow();
        dateFlow.instant = localDateTime
                .atZone(dateFlow.zoneId)
                .toInstant();
        return dateFlow;
    }

    public static DateFlow from(Date date) {
        var dateFlow = new DateFlow();
        dateFlow.instant = ZonedDateTime
                .ofInstant(date.toInstant(), dateFlow.zoneId)
                .toInstant();
        return dateFlow;
    }

    public static DateFlow from(long millis) {
        var dateFlow = new DateFlow();
        dateFlow.instant = Instant
                .ofEpochMilli(millis)
                .atZone(dateFlow.zoneId)
                .toInstant();
        return dateFlow;
    }

    public static DateFlow from(Instant instant) {
        var dateFlow = new DateFlow();
        dateFlow.instant = instant
                .atZone(dateFlow.zoneId)
                .toInstant();
        return dateFlow;
    }

    public static DateFlow from(String date) throws ParseException {
        return from(date, DATE_FORMAT);
    }

    public static DateFlow from(String date, String dateFormat) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat(dateFormat);
        format.setTimeZone(TimeZone.getTimeZone(TIME_ZONE));
        format.setLenient(false);
        Date dateParsed = format.parse(date);
        return from(dateParsed);
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

    public DateFlow plusMonths(int months) {
        this.instant = this.instant
                .atZone(zoneId)
                .plusMonths(months)
                .toInstant();
        return this;
    }

    public DateFlow plusDays(int days) {
        this.instant = this.instant
                .atZone(zoneId)
                .plusDays(days)
                .toInstant();
        return this;
    }

    public DateFlow plusMinutes(int minutes) {
        this.instant = this.instant
                .atZone(zoneId)
                .plusMinutes(minutes)
                .toInstant();
        return this;
    }

    public DateFlow plusHours(int hours) {
        this.instant = this.instant
                .atZone(zoneId)
                .plusHours(hours)
                .toInstant();
        return this;
    }

    public DateFlow plusSeconds(int seconds) {
        this.instant = this.instant
                .atZone(zoneId)
                .plusSeconds(seconds)
                .toInstant();
        return this;
    }

    public DateFlow minusSeconds(int seconds) {
        this.instant = this.instant
                .atZone(zoneId)
                .minusSeconds(seconds)
                .toInstant();
        return this;
    }

    public DateFlow minusMinutes(int minutes) {
        this.instant = this.instant
                .atZone(zoneId)
                .minusMinutes(minutes)
                .toInstant();
        return this;
    }

    public DateFlow minusHours(int hours) {
        this.instant = this.instant
                .atZone(zoneId)
                .minusHours(hours)
                .toInstant();
        return this;
    }

    public DateFlow minusDays(int days) {
        this.instant = this.instant
                .atZone(zoneId)
                .minusDays(days)
                .toInstant();
        return this;
    }

    public DateFlow minusMonths(int months) {
        this.instant = this.instant
                .atZone(zoneId)
                .minusMonths(months)
                .toInstant();
        return this;
    }

    public Date toDate() {
        return Date.from(instant);
    }

    public LocalDate toLocalDate() {
        return LocalDate.ofInstant(instant, zoneId);
    }

    public LocalDateTime toLocalDateTime() {
        return LocalDateTime.ofInstant(instant, zoneId);
    }
}
