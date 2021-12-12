package com.dateflow;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Date;
import java.util.TimeZone;

public class DateFlow {

    private DateFlow() {
    }

    public static OperationsFlow now() {
        return new OperationsFlow();
    }

    public static OperationsFlow from(LocalDateTime localDateTime, ZoneId zoneId) {
        var operationsFlow = new OperationsFlow();
        operationsFlow.instant = localDateTime
                .atZone(zoneId)
                .toInstant()
                .atZone(operationsFlow.zoneId)
                .toInstant();
        return operationsFlow;
    }

    public static OperationsFlow from(String date, String dateFormat, ZoneId zoneId) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat(dateFormat);
        format.setTimeZone(TimeZone.getTimeZone(zoneId.getId()));
        format.setLenient(false);
        Date dateParsed = format.parse(date);
        return from(dateParsed);
    }

    public static OperationsFlow from(LocalDateTime localDateTime) {
        return from(localDateTime, ZoneId.systemDefault());
    }

    public static OperationsFlow from(String date, String dateFormat) throws ParseException {
        return from(date, dateFormat, ZoneId.systemDefault());
    }

    public static OperationsFlow from(LocalDate localDate) {
        var operationsFlow = new OperationsFlow();
        operationsFlow.instant = localDate
                .atStartOfDay()
                .atZone(operationsFlow.zoneId)
                .toInstant();
        return operationsFlow;
    }

    public static OperationsFlow from(long millis) {
        var operationsFlow = new OperationsFlow();
        operationsFlow.instant = Instant
                .ofEpochMilli(millis)
                .atZone(operationsFlow.zoneId)
                .toInstant();
        return operationsFlow;
    }

    public static OperationsFlow from(Instant instant) {
        var operationsFlow = new OperationsFlow();
        operationsFlow.instant = instant
                .atZone(operationsFlow.zoneId)
                .toInstant();
        return operationsFlow;
    }

    public static OperationsFlow from(Date date) {
        var operationsFlow = new OperationsFlow();
        operationsFlow.instant = ZonedDateTime
                .ofInstant(date.toInstant(), operationsFlow.zoneId)
                .toInstant();
        return operationsFlow;
    }
}
