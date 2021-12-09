package com.dateflow;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

public class TransformerFlow {

    ZoneId zoneId;
    Instant instant;
    TimeZone timeZone;
    ZoneId outputZoneId;

    public TransformerFlow(ZoneId zoneId, Instant instant, TimeZone timeZone, ZoneId outputZoneId) {
        this.zoneId = zoneId;
        this.instant = instant;
        this.timeZone = timeZone;
        this.outputZoneId = outputZoneId;
    }

    public Date date() {
        return Date.from(instant.atZone(outputZoneId).toInstant());
    }

    public LocalDate localDate() {
        return LocalDate.ofInstant(instant, outputZoneId);
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
