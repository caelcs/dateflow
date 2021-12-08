package com.dateflow;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.TimeZone;

public class DateFlow {

    static final String TIME_ZONE = "UTC";
    static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";

    ZoneId zoneId;
    Instant instant;
    TimeZone timeZone;
    ZoneId outputZoneId;

    private DateFlow() {
        this.zoneId = ZoneId.of(TIME_ZONE);
        this.timeZone = TimeZone.getTimeZone(zoneId);
        this.outputZoneId = ZoneId.of(TIME_ZONE);
        this.instant = Instant.now().atZone(zoneId).toInstant();
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
        instant = instant
                .atZone(zoneId)
                .truncatedTo(ChronoUnit.DAYS)
                .toInstant();
        return this;
    }

    public DateFlow resetTimeToLastSecondOfDay() {
        instant = instant.atZone(zoneId)
                .withHour(23)
                .withMinute(59)
                .withSecond(59)
                .toInstant();
        return this;
    }

    public DateFlow plusMonths(int months) {
        instant = instant
                .atZone(zoneId)
                .plusMonths(months)
                .toInstant();
        return this;
    }

    public DateFlow plusDays(int days) {
        instant = instant
                .atZone(zoneId)
                .plusDays(days)
                .toInstant();
        return this;
    }

    public DateFlow plusMinutes(int minutes) {
        instant = instant
                .atZone(zoneId)
                .plusMinutes(minutes)
                .toInstant();
        return this;
    }

    public DateFlow plusHours(int hours) {
        instant = instant
                .atZone(zoneId)
                .plusHours(hours)
                .toInstant();
        return this;
    }

    public DateFlow plusSeconds(int seconds) {
        instant = instant
                .atZone(zoneId)
                .plusSeconds(seconds)
                .toInstant();
        return this;
    }

    public DateFlow minusSeconds(int seconds) {
        instant = instant
                .atZone(zoneId)
                .minusSeconds(seconds)
                .toInstant();
        return this;
    }

    public DateFlow minusMinutes(int minutes) {
        instant = instant
                .atZone(zoneId)
                .minusMinutes(minutes)
                .toInstant();
        return this;
    }

    public DateFlow minusHours(int hours) {
        instant = instant
                .atZone(zoneId)
                .minusHours(hours)
                .toInstant();
        return this;
    }

    public DateFlow minusDays(int days) {
        instant = instant
                .atZone(zoneId)
                .minusDays(days)
                .toInstant();
        return this;
    }

    public DateFlow minusMonths(int months) {
        instant = instant
                .atZone(zoneId)
                .minusMonths(months)
                .toInstant();
        return this;
    }

    public Date asDate() {
        return Date.from(instant.atZone(outputZoneId).toInstant());
    }

    public LocalDate asLocalDate() {
        return LocalDate.ofInstant(instant, outputZoneId);
    }

    public LocalDateTime asLocalDateTime() {
        return LocalDateTime.ofInstant(instant, outputZoneId);
    }

    public String asString(String format) {
        DateTimeFormatter formatter = DateTimeFormatter
                .ofPattern(format)
                .withZone(outputZoneId);
        return formatter.format(instant);
    }

    public DateFlow zoneId(ZoneId newZoneId) {
        outputZoneId = newZoneId;
        return this;
    }

    public DateFlow zoneIdUTC() {
        outputZoneId = zoneId;
        return this;
    }

    public int day() {
        return instant.atZone(outputZoneId).getDayOfMonth();
    }

    public int month() {
        return instant.atZone(outputZoneId).getMonthValue();
    }

    public int year() {
        return instant.atZone(outputZoneId).getYear();
    }

    public int hour() {
        return instant.atZone(outputZoneId).getHour();
    }

    public int minutes() {
        return instant.atZone(outputZoneId).getMinute();
    }

    public int seconds() {
        return instant.atZone(outputZoneId).getSecond();
    }
}
