package com.dateflow;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

import static com.dateflow.Constants.DATE_FORMAT;
import static com.dateflow.Constants.TIME_ZONE;

public class TransformerFlow {

    ZoneId zoneId;
    Instant instant;
    TimeZone timeZone;
    ZoneId outputZoneId;

    public TransformerFlow(ZoneId zoneId, Instant instant, TimeZone timeZone) {
        this.zoneId = zoneId;
        this.instant = instant;
        this.timeZone = timeZone;
        this.outputZoneId = ZoneId.of(TIME_ZONE);
    }

    public TransformerFlow zoneId(ZoneId newZoneId) {
        outputZoneId = newZoneId;
        return this;
    }

    public Date date() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);

        SimpleDateFormat timeZoneFormatter = new SimpleDateFormat(DATE_FORMAT);
        TimeZone tzInAmerica = TimeZone.getTimeZone(outputZoneId);
        timeZoneFormatter.setTimeZone(tzInAmerica);

        Date result = Date.from(instant);
        String stringDate = timeZoneFormatter.format(result);
        return formatter.parse(stringDate);
    }

    public LocalDate localDate() {
        return instant.atZone(outputZoneId).toLocalDate();
    }

    public LocalDateTime localDateTime() {
        return LocalDateTime.ofInstant(instant, outputZoneId);
    }

    public String string(String format) {
        DateTimeFormatter formatter = DateTimeFormatter
                .ofPattern(format)
                .withZone(outputZoneId);
        return formatter.format(instant);
    }

    public Instant instant() {
        return instant;
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
